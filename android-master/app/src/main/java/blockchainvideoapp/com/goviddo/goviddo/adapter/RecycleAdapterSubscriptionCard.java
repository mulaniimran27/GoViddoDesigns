package blockchainvideoapp.com.goviddo.goviddo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.Recycler_Subscription;

public class RecycleAdapterSubscriptionCard extends RecyclerView.Adapter<RecycleAdapterSubscriptionCard.MyViewHolder> {

    private ArrayList<Recycler_Subscription> recyclerModels; // this data structure carries our title and description
    private int mPosition;
    private Context context;

    public RecycleAdapterSubscriptionCard(Context activity, ArrayList<Recycler_Subscription> recyclerModels) {
        context = activity;
        this.recyclerModels = recyclerModels;
    }

    @Override
    public RecycleAdapterSubscriptionCard.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_subscribe_cardvideo, parent, false);
        final RecycleAdapterSubscriptionCard.MyViewHolder mViewHolder = new RecycleAdapterSubscriptionCard.MyViewHolder(view);

        mViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mViewHolder.context, recyclerModels.get(mViewHolder.getPosition()).getmTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        // inflate your custom row layout here
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecycleAdapterSubscriptionCard.MyViewHolder holder, int position) {

        mPosition = position;

        holder.title.setText(recyclerModels.get(mPosition).getmTitle());
        Picasso.with(context)
                .load("https://goviddo.com/VideoContentsImages/DHdxjD7xx7RwF.480.jpeg")
                .fit().centerCrop()
                .noFade()
                .into(holder.imageView);
        // holder.imageView.setImageURI(Uri.parse(""));

    }


    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // view this our custom row layout, so intialize your variables here
        TextView title;
        TextView Description;
        ImageView imageView;
        CardView cardView;
        Context context;

        MyViewHolder(View view) {
            super(view);


            imageView = view.findViewById(R.id.VideoLikedToPlay);
            context = view.getContext();
            title = view.findViewById(R.id.txtShowTitle);
            Description = view.findViewById(R.id.txtShowDesc);
            cardView = view.findViewById(R.id.cardview_Subscription);

        }


    }
}


