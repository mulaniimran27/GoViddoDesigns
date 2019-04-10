package blockchainvideoapp.com.goviddo.goviddo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.coreclass.RecyclerRecent;

public class RecyclerAdaptorRecent extends RecyclerView.Adapter<RecyclerAdaptorRecent.MyViewHolder> {
    private ArrayList<RecyclerRecent> recyclerModels; // this data structure carries our title and description
    int mPosition;

    public RecyclerAdaptorRecent(ArrayList<RecyclerRecent> recyclerModels) {
        this.recyclerModels = recyclerModels;
    }


    @Override
    public RecyclerAdaptorRecent.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_image, parent, false);

        final RecyclerAdaptorRecent.MyViewHolder mViewHolder = new RecyclerAdaptorRecent.MyViewHolder(view);

        // mViewHolder.textView.setText( recyclerModels.get( mPosition ).getmText() );

        mViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mViewHolder.context, recyclerModels.get(mViewHolder.getPosition()).getmTextFirst(), Toast.LENGTH_SHORT).show();
            }
        });


        // inflate your custom row layout here
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdaptorRecent.MyViewHolder holder, int position) {
        mPosition = position;
        // holder.textView.setText(recyclerModels.get( mPosition ).getmText()  );

    }


    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // view this our others_card layout, so intialize your variables here

        CardView cardView;
        ImageView imageView;

        Context context;


        MyViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.image_view_image);
            context = view.getContext();
            cardView = view.findViewById(R.id.image_card_view);

        }


    }
}


