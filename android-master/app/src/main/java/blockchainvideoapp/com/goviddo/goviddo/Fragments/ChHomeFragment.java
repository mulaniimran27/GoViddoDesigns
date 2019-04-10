package blockchainvideoapp.com.goviddo.goviddo.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.adapter.RecyclerAdapter;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.RecyclerModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChHomeFragment extends Fragment {
    private TextView videoChannelName;
    private RecyclerView listHorizontal;
    private LinearLayoutManager mLayoutManager;
    private RecyclerAdapter mRecyclerAdapterPreview;
    private ArrayList<RecyclerModel> mRecyclerModelsPreview;
    private boolean itShouldLoadMore = true;
    private static final int LOAD_LIMIT = 10;
    private String lastId = "0"; // this will issued to php page, so no harm make it string

    private RecyclerView listVertical;
    private View mView;

    public ChHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_ch_home, container, false);
        videoChannelName = mView.findViewById(R.id.hr_list_title);
        listHorizontal = mView.findViewById(R.id.recycle_hr);
        mRecyclerModelsPreview = new ArrayList<>();
        mRecyclerAdapterPreview = new RecyclerAdapter(mRecyclerModelsPreview);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        listHorizontal.setLayoutManager(mLayoutManager);
        listHorizontal.setHasFixedSize(true);
        //we can now set adapter to recyclerView;
        listHorizontal.setAdapter(mRecyclerAdapterPreview);
        firstLoadData();

        listVertical = mView.findViewById(R.id.recycler_vertical);


        return mView;
    }

    // this function will load 15 items as indicated in the LOAD_LIMIT variable field
    private void firstLoadData() {

        String url = "http://goviddo.tech/goviddo_lazyloader/loadmore.php?limit=" + LOAD_LIMIT;

        itShouldLoadMore = false; // lock this guy,(itShouldLoadMore) to make sure,
        // user will not load more when volley is processing another request
        // only load more when  volley is free

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                // remember here we are in the main thread, that means,
                //volley has finished processing request, and we have our response.
                // What else are you waiting for? update itShouldLoadMore = true;
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    // no data available
                    Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();

                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        // please note this last id how we have updated it
                        // if there are 4 items for example, and we are ordering in descending order,
                        // then last id will be 1. This is because outside a loop, we will get the last
                        // value [Thanks to JAVA]

                        lastId = jsonObject.getString("id");
                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");

                        mRecyclerModelsPreview.add(new RecyclerModel(title, description));
                        mRecyclerAdapterPreview.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                Toast.makeText(getActivity(), "network error!", Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);
    }
}
