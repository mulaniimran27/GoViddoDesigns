package blockchainvideoapp.com.goviddo.goviddo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.RecyclerModel;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<RecyclerModel> recyclerModels; // this data structure carries our title and description

    int mPosition;

    public RecyclerAdapter(ArrayList<RecyclerModel> recyclerModels) {
        this.recyclerModels = recyclerModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_layout_reviews, parent, false);

        final MyViewHolder mViewHolder = new MyViewHolder(view);


        mViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mViewHolder.context, recyclerModels.get(mViewHolder.getPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        // inflate your custom row layout here
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        mPosition = position;

    }


    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // view this our custom row layout, so intialize your variables here

        LinearLayout cardView;
        ImageView roundedImageView;

        Context context;

        MyViewHolder(View view) {
            super(view);


            roundedImageView = view.findViewById(R.id.title);
            context = view.getContext();
            cardView = view.findViewById(R.id.cardViewSingleItem);


        }


    }
}


