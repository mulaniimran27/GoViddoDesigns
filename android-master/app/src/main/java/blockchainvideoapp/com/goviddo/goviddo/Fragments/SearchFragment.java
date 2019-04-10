package blockchainvideoapp.com.goviddo.goviddo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.activity.FirstActivity;

public class SearchFragment extends Fragment {
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FirstActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((FirstActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
