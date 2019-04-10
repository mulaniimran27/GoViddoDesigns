package blockchainvideoapp.com.goviddo.goviddo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.Recycler_Subscription;


public class RecycleAdapterSubscription extends RecyclerView.Adapter<RecycleAdapterSubscription.MyViewHolder> {

    private ArrayList<Recycler_Subscription> recyclerModels; // this data structure carries our title and description
    private int mPosition;

    public RecycleAdapterSubscription(ArrayList<Recycler_Subscription> recyclerModels) {
        this.recyclerModels = recyclerModels;
    }

    @Override
    public RecycleAdapterSubscription.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_subscribe_roundimg, parent, false);
        final RecycleAdapterSubscription.MyViewHolder mViewHolder = new RecycleAdapterSubscription.MyViewHolder(view);
        mViewHolder.roundedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mViewHolder.context, recyclerModels.get(mViewHolder.getPosition()).getmTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        // inflate your custom row layout here
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecycleAdapterSubscription.MyViewHolder holder, int position) {
        mPosition = position;
        holder.title.setText(recyclerModels.get(mPosition).getmTitle());
    }


    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // view this our custom row layout, so intialize your variables here
        TextView title;
        ImageView roundedImageView;
        //        CardView cardView;
        Context context;

        MyViewHolder(View view) {
            super(view);
            roundedImageView = view.findViewById(R.id.subscribe_img);
            context = view.getContext();
            title = view.findViewById(R.id.txtSubscribeTitle);
//            cardView = view.findViewById(R.id.Subscription_card);
        }
    }
}




