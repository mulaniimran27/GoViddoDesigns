package blockchainvideoapp.com.goviddo.goviddo.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.adapter.RecycleAdapterSubscription;
import blockchainvideoapp.com.goviddo.goviddo.adapter.RecycleAdapterSubscriptionCard;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.Recycler_Subscription;

public class SubscriptionFragment extends Fragment {
    public SubscriptionFragment() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerViewPreview;
    private RecycleAdapterSubscription mRecyclerAdapterPreview;
    private ArrayList<Recycler_Subscription> mRecyclerModelsPreview;
    private LinearLayoutManager mLayoutManager;

    private RecyclerView mRecyclerView;
    private RecycleAdapterSubscriptionCard mRecyclerAdapter;
    private ArrayList<Recycler_Subscription> mRecyclerModels;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.subscription_fragment, container, false);
        String url = "https://pngimage.net/genie-aladdin-png-6/";
        mRecyclerModelsPreview = new ArrayList<Recycler_Subscription>();
        mRecyclerModelsPreview.add(new Recycler_Subscription(url, "PS Films"));
        mRecyclerModelsPreview.add(new Recycler_Subscription(url, "PS Films"));
        mRecyclerModelsPreview.add(new Recycler_Subscription(url, "PS Films"));
        mRecyclerModelsPreview.add(new Recycler_Subscription(url, "PS Films"));
        mRecyclerModelsPreview.add(new Recycler_Subscription(url, "PS Films"));
        mRecyclerModelsPreview.add(new Recycler_Subscription(url, "PS Films"));
        mRecyclerModelsPreview.add(new Recycler_Subscription(url, "PS Films"));
        mRecyclerModelsPreview.add(new Recycler_Subscription(url, "PS Films"));
        mRecyclerAdapterPreview = new RecycleAdapterSubscription(mRecyclerModelsPreview);
        mRecyclerViewPreview = view.findViewById(R.id.recycle_subscribe_roundimg);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerViewPreview.setLayoutManager(mLayoutManager);
        mRecyclerViewPreview.setHasFixedSize(true);

        //we can now set adapter to recyclerView;
        mRecyclerViewPreview.setAdapter(mRecyclerAdapterPreview);

        //The Code for CardView in Subscription Tab will be as Below
        String urlcard = "https://goviddo.com/VideoContentsImages/DHdxjD7xx7RwF.480.jpeg";

        mRecyclerModels = new ArrayList<Recycler_Subscription>();

        mRecyclerModels.add(new Recycler_Subscription(urlcard, "Happy Season 01 Ep01", "The Goal is Near"));
        mRecyclerModels.add(new Recycler_Subscription(urlcard, "Happy Season 01 Ep01", "The Goal is Near"));
        mRecyclerModels.add(new Recycler_Subscription(urlcard, "Happy Season 01 Ep01", "The Goal is Near"));
        mRecyclerModels.add(new Recycler_Subscription(urlcard, "Happy Season 01 Ep01", "The Goal is Near"));
        mRecyclerModels.add(new Recycler_Subscription(urlcard, "Happy Season 01 Ep01", "The Goal is Near"));
        mRecyclerModels.add(new Recycler_Subscription(urlcard, "Happy Season 01 Ep01", "The Goal is Near"));
        mRecyclerModels.add(new Recycler_Subscription(urlcard, "Happy Season 01 Ep01", "The Goal is Near"));
        mRecyclerModels.add(new Recycler_Subscription(urlcard, "Happy Season 01 Ep01", "The Goal is Near"));
        mRecyclerModels.add(new Recycler_Subscription(urlcard, "Happy Season 01 Ep01", "The Goal is Near"));
        mRecyclerAdapter = new RecycleAdapterSubscriptionCard(getActivity(),mRecyclerModels);

        mRecyclerView = view.findViewById(R.id.recycle_subscribe_cardvideo);

        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        //we can now set adapter to recyclerView;
        mRecyclerView.setAdapter(mRecyclerAdapter);


        return view;

    }
}
