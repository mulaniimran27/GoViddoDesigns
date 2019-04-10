package blockchainvideoapp.com.goviddo.goviddo.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.adapter.GridShareAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {

    public ShareFragment() {
        // Required empty public constructor
    }

    private View mView;

    @SuppressLint("ValidFragment")
    public ShareFragment(Context mContext) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_share, container, false);
        GridView gridview = (GridView) mView.findViewById(R.id.mGridView);
        gridview.setAdapter(new GridShareAdapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                shareWithPosition(position);
            }
        });
        return mView;
    }

    private void shareWithPosition(int position) {
        switch (position) {
            case 0:
                Toast.makeText(getContext(), "Whatsup" + position, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getContext(), "Instagram" + position, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "facebook" + position, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getContext(), "Tweeter" + position, Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getContext(), "Google+" + position, Toast.LENGTH_SHORT).show();
                break;

        }

    }

}
