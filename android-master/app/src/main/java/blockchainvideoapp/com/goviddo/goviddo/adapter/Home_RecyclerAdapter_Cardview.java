package blockchainvideoapp.com.goviddo.goviddo.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.EndlessRecyclerViewScrollListner;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.RecyclerCardViewModel;

public class Home_RecyclerAdapter_Cardview extends RecyclerView.Adapter<Home_RecyclerAdapter_Cardview.MyViewHolder> {


    private ArrayList<RecyclerCardViewModel> recyclerModels; // this data structure carries our title and description

    int mPosition;
    private boolean itShouldLoadMore = true;


    private Home_Video_Adapter mRecyclerAdapterVideo;
    private ArrayList<RecyclerCardViewModel> mRecyclerModelsVideo;
    LinearLayoutManager mLinearLayoutManagerVideo;

    String url1 = "https://pngimage.net/genie-aladdin-png-6/";
    private Context context;

    public Home_RecyclerAdapter_Cardview(FragmentActivity activity, ArrayList<RecyclerCardViewModel> recyclerModels) {
        context = activity;
        this.recyclerModels = recyclerModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_video, parent, false);

        final MyViewHolder mViewHolder = new MyViewHolder(view);

        mViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mViewHolder.context, recyclerModels.get(mViewHolder.getPosition()).getHeading(), Toast.LENGTH_SHORT).show();
            }
        });


        // inflate your custom row layout here
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        mPosition = position;

        mRecyclerModelsVideo = new ArrayList<>();

        holder.textView.setText(recyclerModels.get(mPosition).getHeading());

        mRecyclerAdapterVideo = new Home_Video_Adapter(mRecyclerModelsVideo, holder.context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(holder.context, LinearLayoutManager.HORIZONTAL, false);

        holder.recyclerView.setLayoutManager(mLayoutManager);
        holder.recyclerView.setHasFixedSize(true);

        holder.recyclerView.setAdapter(mRecyclerAdapterVideo);

        firstLoadData(holder.context, recyclerModels.get(mPosition).getHeading(), recyclerModels.get(mPosition).getCount(), 0, mRecyclerModelsVideo, mRecyclerAdapterVideo);

        holder.recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListner((LinearLayoutManager) mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //  Toast.makeText(getActivity(),"LAst",Toast.LENGTH_LONG).show();
                firstLoadData(holder.context, recyclerModels.get(mPosition).getHeading(), recyclerModels.get(mPosition).getCount(), 17, mRecyclerModelsVideo, mRecyclerAdapterVideo);
            }
        });


        mRecyclerAdapterVideo.notifyDataSetChanged();

        mLinearLayoutManagerVideo = new LinearLayoutManager(holder.context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(mLinearLayoutManagerVideo);
        holder.recyclerView.setHasFixedSize(true);

        //we can now set adapter to recyclerView;
        //Toast.makeText(holder.context, "size" + recyclerModels.size(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // view this our custom row layout, so intialize your variables here

        RecyclerView recyclerView;
        TextView textView;
        Context context;

        MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.card_text);
            context = view.getContext();
            recyclerView = view.findViewById(R.id.video_recycler);
        }
    }


    // this function will load 15 items as indicated in the LOAD_LIMIT variable field
    private void firstLoadData(final Context context, String videoGenere, int videoLimit, int videoLastId, final ArrayList<RecyclerCardViewModel> recyclerCardViewModel, final Home_Video_Adapter home_video_adapter) {


        String url = "http://178.128.173.51:3000/getVideoData";
        System.out.println(url);

        JSONObject params = new JSONObject();
        try {
            params.put("videoGenere", videoGenere);
            params.put("videoLimit", videoLimit);
            params.put("videoLastId", videoLastId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(params.toString());

        itShouldLoadMore = false; // lock this guy,(itShouldLoadMore) to make sure,
        // user will not load more when volley is processing another request
        // only load more when  volley is free

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                progressDialog.dismiss();
                // remember here we are in the main thread, that means,
                //volley has finished processing request, and we have our response.
                // What else are you waiting for? update itShouldLoadMore = true;
                itShouldLoadMore = true;
                try {
                    // please note this last id how we have updated it
                    // if there are 4 items for example, and we are ordering in descending order,
                    // then last id will be 1. This is because outside a loop, we will get the last
                    // value [Thanks to JAVA]

                    String msg = response.getString("message");

                    if (msg.equalsIgnoreCase("success")) {
                        JSONArray jsonArray = response.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String home_image_url = jsonObject1.getString("home_image");
                            String shorten_test = jsonObject1.getString("shorten_text");
                            String video_cipher_id = jsonObject1.getString("vdo_cipher_id");
                            int video_id = jsonObject1.getInt("video_id");
                            recyclerCardViewModel.add(new RecyclerCardViewModel(home_image_url, shorten_test, video_cipher_id, video_id));
                        }
                    }
                    home_video_adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // please note how we have updated our last id variable which is initially 0 (String)
                // outside the loop, java will return the last value, so here it will
                // certainly give us lastId that we need
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // also here, volley is not processing, unlock it should load more
                itShouldLoadMore = true;
                progressDialog.dismiss();
                Toast.makeText(context, "network error!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }
}

