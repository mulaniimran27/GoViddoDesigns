package blockchainvideoapp.com.goviddo.goviddo.Fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.activity.FirstActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayVideoFragment extends Fragment implements View.OnClickListener {

    private TextView txtShare, txtLike, txtDislike, txtAddToList;
    private View mView;
    private Context mContext;
    private LinearLayout channelView;
    private Button btnInvest;

    public PlayVideoFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public PlayVideoFragment(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_play_video, container, false);
        txtShare = mView.findViewById(R.id.txt_share);
        txtLike = mView.findViewById(R.id.txt_like);
        txtDislike = mView.findViewById(R.id.txt_dis_like);
        txtAddToList = mView.findViewById(R.id.txt_add_to_list);
        channelView = mView.findViewById(R.id.channel_view);
        btnInvest = mView.findViewById(R.id.btn_invest);

        btnInvest.setOnClickListener(this);
        channelView.setOnClickListener(this);
        txtShare.setOnClickListener(this);
        txtLike.setOnClickListener(this);
        txtDislike.setOnClickListener(this);
        txtAddToList.setOnClickListener(this);

        return mView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_invest:
                openInvestDialog();
                break;
            case R.id.txt_like:
                Toast.makeText(getContext(), "Like", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt_dis_like:
                Toast.makeText(getContext(), "dislike", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt_share:
                ((FirstActivity) mContext).loadFragment(new ShareFragment(mContext), 0);
                break;
            case R.id.txt_add_to_list:
                Toast.makeText(getContext(), "Add to list", Toast.LENGTH_SHORT).show();
                break;

            case R.id.channel_view:
                ((FirstActivity) getActivity()).loadFragment(new ChannelFragment(), 1);
                break;
        }
    }

    private void openInvestDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_invest);
        Button buttonInvest = dialog.findViewById(R.id.btn_invest);
        TextView dialogButton = dialog.findViewById(R.id.btn_cancel);
        buttonInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Invest click", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
