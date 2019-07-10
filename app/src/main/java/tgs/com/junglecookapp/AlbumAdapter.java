package tgs.com.junglecookapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;
    public AlbumAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.MyViewHolder holder, int position) {
        Album album = albumList.get(position);

        holder.title.setText(album.getName());
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
        /*if(position == 0) {
            holder.relative.setBackgroundColor(Color.parseColor("#ff9999"));
            //holder.layoutColor.setBackgroundColor(Color.parseColor("#027474"));
        }else if(position == 1) {
            holder.relative.setBackgroundColor(Color.parseColor("#ffb366"));
           // holder.layoutColor.setBackgroundColor(Color.parseColor("#23527c"));
        }else if(position == 2) {
            holder.relative.setBackgroundColor(Color.parseColor("#b366ff"));
            //holder.layoutColor.setBackgroundColor(Color.parseColor("#BD8008"));
        }else if(position == 3) {
            holder.relative.setBackgroundColor(Color.parseColor("#f9e0e0"));
            //holder.layoutColor.setBackgroundColor(Color.parseColor("#B11003"));
        }else if(position == 4) {
            holder.relative.setBackgroundColor(Color.parseColor("#f9e0e0"));
            //holder.layoutColor.setBackgroundColor(Color.parseColor("#91248B"));
        }else if(position == 5) {
            holder.relative.setBackgroundColor(Color.parseColor("#f9e0e0"));
            //holder.layoutColor.setBackgroundColor(Color.parseColor("#C65F04"));
        }*/
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        CardView layoutbg,layoutColor;
        RelativeLayout relative;
        public MyViewHolder(@NonNull View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.label);
            // count = (TextView) view.findViewById(R.id.dash_count);
            thumbnail = (ImageView) view.findViewById(R.id.icon);
          layoutbg = (CardView) view.findViewById(R.id.layout);
         // relative = (RelativeLayout) view.findViewById(R.id.relative);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callActivity(mContext, getAdapterPosition());
                }
            });
        }
    }

    private void callActivity(Context mContext, int adapterPosition) {
        /*if(adapterPosition==1){
            Toast.makeText(mContext,"Will be update soon...",Toast.LENGTH_LONG).show();
        }else{*/
        Intent intent = new Intent(mContext, Dashboard_Main.class);
        intent.putExtra("position", adapterPosition);
        mContext.startActivity(intent);
    }
}

