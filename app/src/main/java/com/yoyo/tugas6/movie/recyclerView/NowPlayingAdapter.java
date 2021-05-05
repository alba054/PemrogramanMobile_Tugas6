package com.yoyo.tugas6.movie.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yoyo.tugas6.R;
import com.yoyo.tugas6.movie.NowPlaying;
import com.yoyo.tugas6.utils.Consts;

import java.util.List;

public class NowPlayingAdapter extends
        RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {

    private List<NowPlaying> nowPlayingList;


    public void setNowPlayingList(List<NowPlaying> nowPlayingList) {
        this.nowPlayingList = nowPlayingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.rc_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(nowPlayingList.get(position));
    }

    @Override
    public int getItemCount() {
        return nowPlayingList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        NowPlaying nowPlaying;
        TextView tvTitle;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivPoster = itemView.findViewById(R.id.iv_poster);
        }

        public void onBind(NowPlaying nowPlaying) {
            String uri = Consts.IMAGEBASEURL + nowPlaying.getPosterImage();
            this.nowPlaying = nowPlaying;
            tvTitle.setText(nowPlaying.getTitle());
            Glide.with(this.itemView.getContext()).load(uri).into(ivPoster);
        }
    }
}
