package com.yoyo.tugas6.tv.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yoyo.tugas6.R;
import com.yoyo.tugas6.tv.TVShow;
import com.yoyo.tugas6.utils.Consts;

import java.util.List;

public class TVShowAdapter extends
        RecyclerView.Adapter<TVShowAdapter.ViewHolder> {

    private List<TVShow> tvShowList;

    public void setTvShowList(List<TVShow> tvShowList) {
        this.tvShowList = tvShowList;
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
        holder.onBind(tvShowList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TVShow tvShow;
        TextView tvTitle;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivPoster = itemView.findViewById(R.id.iv_poster);
        }

        public void onBind(TVShow tvShow) {
            String uri = Consts.IMAGEBASEURL + tvShow.getPosterImage();
            this.tvShow = tvShow;
            tvTitle.setText(tvShow.getName());
            Glide.with(this.itemView.getContext()).load(uri).into(ivPoster);
        }
    }
}

