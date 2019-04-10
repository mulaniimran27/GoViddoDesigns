package blockchainvideoapp.com.goviddo.goviddo.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.adapter.Home_RecyclerAdapter_Cardview;
import blockchainvideoapp.com.goviddo.goviddo.adapter.RecyclerAdapter;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.EndlessRecyclerViewScrollListner;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.RecyclerCardViewModel;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.RecyclerModel;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    // we will be loading 15 items per page or per load
    // you can change this to fit your specifications.
    // When you change this, there will be no need to update your php page,
    // as php will be ordered what to load and limit by android java
    private static final int LOAD_LIMIT = 15;

    // last id to be loaded from php page,
    // we will need to keep track or database id field to know which id was loaded last
    // and where to begin loading
    private String lastId = "0"; // this will issued to php page, so no harm make it string

    // we need this variable to lock and unlock loading more
    // e.g we should not load more when volley is already loading,
    // loading will be activated when volley completes loading
    private boolean itShouldLoadMore = true;
    private SliderLayout mDemoSlider;
    private RecyclerView mRecyclerViewPreview;
    private RecyclerAdapter mRecyclerAdapterPreview;
    private ArrayList<RecyclerModel> mRecyclerModelsPreview;
    private ProgressWheel mProgressWheelPreview;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerCardview;
    public static Home_RecyclerAdapter_Cardview mRecyclerAdapterCardview;
    private ArrayList<RecyclerCardViewModel> mRecyclerModelsCardview;
    private ProgressWheel mProgressWheelCardview;
    private LinearLayoutManager mLayoutManagerCardview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        mDemoSlider = view.findViewById(R.id.slider);
        mRecyclerModelsPreview = new ArrayList<>();
        mRecyclerAdapterPreview = new RecyclerAdapter(mRecyclerModelsPreview);
        mRecyclerViewPreview = view.findViewById(R.id.loadmore_recycler_view);
        mProgressWheelPreview = view.findViewById(R.id.progress_wheel);
        //Initialization  For CardView Below Round Img
        mRecyclerModelsCardview = new ArrayList<>();
        mRecyclerAdapterCardview = new Home_RecyclerAdapter_Cardview(getActivity(),mRecyclerModelsCardview);
        mRecyclerCardview = view.findViewById(R.id.home_video_recycler);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("INCREDIBLES 2", "http://www.movienewsletters.net/media/slider/1200x444/219672.jpg");
        url_maps.put("AVENGERS INFINITY WAR", "https://latimeshighschool.files.wordpress.com/2018/06/share-1.jpg");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "https://gamesofthronesfact.files.wordpress.com/2014/04/game-of-thrones-season-4.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerViewPreview.setLayoutManager(mLayoutManager);
        mRecyclerViewPreview.setHasFixedSize(true);
        //we can now set adapter to recyclerView;
        mRecyclerViewPreview.setAdapter(mRecyclerAdapterPreview);
        firstLoadData();

        mRecyclerViewPreview.addOnScrollListener(new EndlessRecyclerViewScrollListner(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //  Toast.makeText(getActivity(),"LAst",Toast.LENGTH_LONG).show();
                loadMore();
            }
        });


        //CardView Adapter Code

        String configuration_url = "http://178.128.173.51:3000/config";


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://178.128.173.51:3000/config", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String bannerIimageCount = response.getString("bannerImgCount");
                    String categories = response.getString("categories");
                    String[] aa = categories.split("\\:", -1);
                    ArrayList<String> pushdata = new ArrayList<>();
                    for (int i = 1; i < aa.length; i++) {

                        if (i % 2 == 1) {
                            String[] namearr = aa[i].split(",", -1);
                            String namedisp = namearr[0].replaceAll("\\'", "");

                            //System.out.println(namedisp);
                            pushdata.add(namedisp);
                        } else {
                            String[] countarr = aa[i].split("\\}", -1);
                            String countdisp = countarr[0];
                            //System.out.println(countdisp);
                            pushdata.add(countdisp);
                        }

                    }

                    for (int i = 0; i < pushdata.size(); i++) {
                        if (i % 2 == 0) {
                            mRecyclerModelsCardview.add(new RecyclerCardViewModel(pushdata.get(i), Integer.parseInt(pushdata.get(i + 1))));
                        }
                    }


                    mRecyclerAdapterCardview = new Home_RecyclerAdapter_Cardview(getActivity(), mRecyclerModelsCardview);


                    mLayoutManagerCardview = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                    mRecyclerCardview.setLayoutManager(mLayoutManagerCardview);

                    //we can now set adapter to recyclerView;
                    mRecyclerCardview.setAdapter(mRecyclerAdapterCardview);
                    mRecyclerAdapterCardview.notifyDataSetChanged();


                    //System.out.println(categories.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Network Error Home Fragment", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);

        // mRecyclerModelsCardview.add( new RecyclerCardViewModel( "Popular on GoViddo",url,5 ) );
        // mRecyclerModelsCardview.add( new RecyclerCardViewModel("Horror",5 ) );


        return view;
    }


    @Override
    public void onDestroy() {
        mDemoSlider.stopAutoCycle();
        super.onDestroy();
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
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


    private void loadMore() {


        String url = "http://goviddo.tech/goviddo_lazyloader/loadmoreaction.php?lastId=" + lastId + "&limit=" + LOAD_LIMIT;

        // our php page starts loading from 250 to 1, because we have [ORDER BY id DESC]
        // So until you clearly understand everything, for this tutorial use ORDER BY ID DESC
        // so we will do something like this to the php page
        //==============================================
        // $limit = $_GET['limit']
        // $lastId = $_GET['lastId']
        // then [SELECT * FROM table_name WHERE id < $lastId ORDER_BY id DESC LIMIT $limit ]
        // here we shall load 15 items from table where lastId id less than last loaded id

        // if you are using [ASC] in sql, your query might change to tis
        // then [SELECT * FROM table_name WHERE id > $lastId ORDER_BY id DESC LIMIT $limit ]
        // for this tutorial let's stick to [DESC]


        itShouldLoadMore = false; // lock this until volley completes processing

        // progressWheel is just a loading spinner, please see the content_main.xml

        // mProgressWheelPreview.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // mProgressWheelPreview.setVisibility(View.GONE);

                // since volley has completed and it has our response, now let's update
                // itShouldLoadMore

                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    // we need to check this, to make sure, our dataStructure JSonArray contains
                    // something
                    Toast.makeText(getActivity(), "no data available", Toast.LENGTH_SHORT).show();
                    return; // return will end the program at this point
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        // please note how we have updated the lastId variable
                        // if there are 4 items for example, and we are ordering in descending order,
                        // then last id will be 1. This is because outside a loop, we will get the last
                        // value

                        lastId = jsonObject.getString("id");
                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");

                        mRecyclerModelsPreview.add(new RecyclerModel(title, description));
                        mRecyclerAdapterPreview.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressWheelPreview.setVisibility(View.GONE);
                // volley finished and returned network error, update and unlock  itShouldLoadMore
                itShouldLoadMore = true;
                // Toast.makeText(getActivity(), "Failed to load more, network error", Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);

    }


    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }


}