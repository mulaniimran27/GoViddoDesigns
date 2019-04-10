package blockchainvideoapp.com.goviddo.goviddo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.activity.MainActivity;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.LoginUserDetails;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.Other_recycler;


public class RecyleAdapterOthers extends RecyclerView.Adapter<RecyleAdapterOthers.MyViewHolder> {
private ArrayList<Other_recycler> recyclerModels; // this data structure carries our title and description

    int mPosition;

    public RecyleAdapterOthers(ArrayList<Other_recycler> recyclerModels) {
        this.recyclerModels = recyclerModels;

    }

    @Override
    public RecyleAdapterOthers.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.others_card, parent, false);

        final RecyleAdapterOthers.MyViewHolder mViewHolder = new RecyleAdapterOthers.MyViewHolder(view);

        mViewHolder.textView.setText( recyclerModels.get( mPosition ).getTitle() );

        mViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (recyclerModels.get(mViewHolder.getPosition()).getTitle().equalsIgnoreCase("sign out"))
                {
                    LoginUserDetails loginUserDetails = new LoginUserDetails(mViewHolder.context);
                    loginUserDetails.removeUserInfo();

                    Intent intent = new Intent(mViewHolder.context, MainActivity.class);
                    mViewHolder.context.startActivity(intent);
                    ((Activity)mViewHolder.context).finish();

                }

                Toast.makeText(mViewHolder.context, recyclerModels.get(mViewHolder.getPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });






        // inflate your custom row layout here
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyleAdapterOthers.MyViewHolder holder, int position) {
        mPosition = position;
        holder.textView.setText(recyclerModels.get( mPosition ).getTitle()  );

    }


    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // view this our others_card layout, so intialize your variables here

        CardView cardView;
        TextView textView;

        Context context;


        MyViewHolder(View view) {
            super(view);

            textView=view.findViewById( R.id.txtTitle );
            context = view.getContext();
            cardView = view.findViewById(R.id.othersCard);

        }


    }
    }





