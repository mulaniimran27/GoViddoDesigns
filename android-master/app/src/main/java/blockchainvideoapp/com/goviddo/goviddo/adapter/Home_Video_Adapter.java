package blockchainvideoapp.com.goviddo.goviddo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import blockchainvideoapp.com.goviddo.goviddo.Fragments.PlayVideoFragment;
import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.activity.FirstActivity;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.RecyclerCardViewModel;

public class Home_Video_Adapter extends RecyclerView.Adapter<Home_Video_Adapter.MyViewHolder> {
    private ArrayList<RecyclerCardViewModel> recyclerModels; // this data structure carries our title and description
    private int mPosition;
    private Context mContext;

    public Home_Video_Adapter(ArrayList<RecyclerCardViewModel> recyclerModels, Context context) {
        this.recyclerModels = recyclerModels;
        mContext = context;
    }

    @Override
    public Home_Video_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_image_recycler, parent, false);
        final Home_Video_Adapter.MyViewHolder mViewHolder = new Home_Video_Adapter.MyViewHolder(view);
        mViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFragment(recyclerModels.get(mViewHolder.getPosition()).getVdo_cipherid());
                //Toast.makeText(mContext, recyclerModels.get(mViewHolder.getPosition()).getVdo_cipherid(), Toast.LENGTH_SHORT).show();
            }
        });


        // inflate your custom row layout here
        return mViewHolder;
    }

    private void gotoFragment(String heading) {
        Bundle bundle = new Bundle();
        Log.d("test heading ", heading);
        bundle.putString("heading", heading);
        Fragment frag = new PlayVideoFragment(mContext);
        frag.setArguments(bundle);
        ((FirstActivity) mContext).loadFragment(frag, 0);
    }

    @Override
    public void onBindViewHolder(final Home_Video_Adapter.MyViewHolder holder, int position) {
        mPosition = position;
        Log.d("test0", " url " + recyclerModels.get(position).getUrl());
        Picasso.with(mContext).load(recyclerModels.get(position).getUrl()).fit().centerCrop().into(holder.ImageView);
    }


    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        // view this our custom row layout, so intialize your variables here
        CardView cardView;
        ImageView ImageView;

        MyViewHolder(View view) {
            super(view);
            ImageView = view.findViewById(R.id.video_image);
            cardView = view.findViewById(R.id.video_image_card_view);
        }
    }
}
