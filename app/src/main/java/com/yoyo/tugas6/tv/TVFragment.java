package com.yoyo.tugas6.tv;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoyo.tugas6.R;
import com.yoyo.tugas6.activities.TVDetailActivity;
import com.yoyo.tugas6.misc.OnItemClickListener;
import com.yoyo.tugas6.tv.recyclerView.TVShowAdapter;
import com.yoyo.tugas6.utils.Consts;
import com.yoyo.tugas6.utils.MovieService;
import com.yoyo.tugas6.utils.SingleRetrofit;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVFragment extends Fragment implements OnItemClickListener<Integer> {
    private RecyclerView rcTvShow;
    private TVShowAdapter tvShowAdapter;
//    private Database nowPlayingDatabase;

    public TVFragment() {
        // Required empty public constructor
    }

    public static TVFragment newInstance() {
        TVFragment fragment = new TVFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_t_v, container, false);

        rcTvShow = view.findViewById(R.id.rc_tv);
        rcTvShow.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rcTvShow.setHasFixedSize(true);
        tvShowAdapter = new TVShowAdapter();
        tvShowAdapter.setClickListener(this);
//        nowPlayingDatabase = new Database();
//        nowPlayingAdapter = new NowPlayingAdapter();
//        nowPlayingAdapter.setNowPlayingList(nowPlayingDatabase.getNowPlayingList());
        load();
//        rcNowPlaying.setAdapter(nowPlayingAdapter);

        return view;
    }

    private void load() {
        MovieService service = SingleRetrofit.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("api_key", Consts.APIKEY);
        params.put("language", Consts.LANGUAGE);
        params.put("page", Consts.PAGE);
        Call<TVResult> call = service.tvAiringToday(params);

        call.enqueue(new Callback<TVResult>() {
            @Override
            public void onResponse(Call<TVResult> call, Response<TVResult> response) {
                if(response.isSuccessful() && response.body().getTvShowList() != null) {
                    tvShowAdapter.setTvShowList(response.body().getTvShowList());
                    rcTvShow.setAdapter(tvShowAdapter);
                }
                else {
                    Log.d(Consts.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<TVResult> call, Throwable t) {
                Log.d(Consts.APIERROR, t.getMessage());
            }

        });

    }


    @Override
    public void onClick(Integer id) {
        Intent intent = new Intent(getActivity(), TVDetailActivity.class);

        if(id != null) {
            intent.putExtra("TV ID", id);
            startActivity(intent);
        }
    }
}