package blockchainvideoapp.com.goviddo.goviddo.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.activity.FirstActivity;
import blockchainvideoapp.com.goviddo.goviddo.adapter.RecyclerAdaptorList;
import blockchainvideoapp.com.goviddo.goviddo.adapter.RecyclerAdaptorRecent;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.RecyclerRecent;

public class MoreDetailsFragment extends Fragment {
    private FirstActivity context;

    public MoreDetailsFragment() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerViewRecentImage;
    private RecyclerAdaptorRecent mRecyclerAdapterRecentImage;
    private ArrayList<RecyclerRecent> mRecyclerModelsRecentImage;
    private LinearLayoutManager mLinearLayoutManagerRecentImage;

    private RecyclerView mRecyclerViewRecentList;
    private RecyclerAdaptorList mRecyclerAdapterRecentList;
    private ArrayList<RecyclerRecent> mRecyclerModelsRecentList;
    private LinearLayoutManager mLinearLayoutManagerRecentList;

    @SuppressLint("ValidFragment")
    public MoreDetailsFragment(FirstActivity firstActivity) {
        context = firstActivity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recent_fragment, container, false);

        String url = "https://pngimage.net/genie-aladdin-png-6/";
        mRecyclerModelsRecentImage = new ArrayList<>();
        mRecyclerModelsRecentImage.add(new RecyclerRecent(url, "PS Films"));
        mRecyclerModelsRecentImage.add(new RecyclerRecent(url, "PS Films"));
        mRecyclerModelsRecentImage.add(new RecyclerRecent(url, "PS Films"));
        mRecyclerModelsRecentImage.add(new RecyclerRecent(url, "PS Films"));
        mRecyclerModelsRecentImage.add(new RecyclerRecent(url, "PS Films"));
        mRecyclerModelsRecentImage.add(new RecyclerRecent(url, "PS Films"));
        mRecyclerAdapterRecentImage = new RecyclerAdaptorRecent(mRecyclerModelsRecentImage);


        mRecyclerViewRecentImage = view.findViewById(R.id.image_recyler_view);

        mLinearLayoutManagerRecentImage = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerViewRecentImage.setLayoutManager(mLinearLayoutManagerRecentImage);
        mRecyclerViewRecentImage.setHasFixedSize(true);

        //we can now set adapter to recyclerView;
        mRecyclerViewRecentImage.setAdapter(mRecyclerAdapterRecentImage);

        //The Code for CardView in recent

        mRecyclerModelsRecentList = new ArrayList<>();
        mRecyclerModelsRecentList.add(new RecyclerRecent("History"));
        mRecyclerModelsRecentList.add(new RecyclerRecent("Privacy"));
        mRecyclerModelsRecentList.add(new RecyclerRecent("Account"));
        mRecyclerModelsRecentList.add(new RecyclerRecent("Transactions"));
        mRecyclerModelsRecentList.add(new RecyclerRecent("Watch Later"));


        mRecyclerAdapterRecentList = new RecyclerAdaptorList(context,mRecyclerModelsRecentList);

        mRecyclerViewRecentList = view.findViewById(R.id.list_recyler_view);
        mLinearLayoutManagerRecentList = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerViewRecentList.setLayoutManager(mLinearLayoutManagerRecentList);

        //we can now set adapter to recyclerView;
        mRecyclerViewRecentList.setAdapter(mRecyclerAdapterRecentList);

        return view;


    }
}
