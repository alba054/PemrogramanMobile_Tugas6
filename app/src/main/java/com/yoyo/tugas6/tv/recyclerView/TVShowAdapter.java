package com.yoyo.tugas6.tv.recyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yoyo.tugas6.R;
import com.yoyo.tugas6.misc.OnItemClickListener;
import com.yoyo.tugas6.tv.TVShow;
import com.yoyo.tugas6.utils.Consts;

import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.ViewHolder> {
    private List<TVShow> tvShowList;
    private OnItemClickListener<Integer> clickListener;

    public void setClickListener(OnItemClickListener<Integer> clickListener) {
        this.clickListener = clickListener;
    }

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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TVShow tvShow;
        TextView tvTitle;
        ImageView ivPoster;
        Integer tvId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivPoster = itemView.findViewById(R.id.iv_poster);
        }

        public void onBind(TVShow tvShow) {
            String uri = Consts.IMAGEBASEURL + tvShow.getPosterImage();
            this.tvShow = tvShow;
            tvId = tvShow.getId();
            tvTitle.setText(tvShow.getName());
            Glide.with(this.itemView.getContext()).load(uri).into(ivPoster);
        }

        @Override
        public void onClick(View v) {
            Log.d("DEBUG", tvId.toString());
            clickListener.onClick(tvId);
        }
    }
}

