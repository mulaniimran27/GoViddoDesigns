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
import blockchainvideoapp.com.goviddo.goviddo.adapter.RecyleAdapterOthers;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.Other_recycler;


public class AccountFragment extends Fragment {


    public AccountFragment() {
        //Required empty public constructor
    }

    RecyclerView mRecyclerViewPreview;

    private RecyleAdapterOthers mRecyclerAdapterPreview;
    private ArrayList<Other_recycler> mRecyclerModelsPreview;

    LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.others_fragment, container, false);

        mRecyclerModelsPreview = new ArrayList<>();
        mRecyclerModelsPreview.add(new Other_recycler("Setting"));
        mRecyclerModelsPreview.add(new Other_recycler("Sign Out"));


        mRecyclerAdapterPreview = new RecyleAdapterOthers(mRecyclerModelsPreview);

        mRecyclerViewPreview = view.findViewById(R.id.recycle);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerViewPreview.setLayoutManager(mLayoutManager);

        //we can now set adapter to recyclerView;
        mRecyclerViewPreview.setAdapter(mRecyclerAdapterPreview);

        return view;
    }


}