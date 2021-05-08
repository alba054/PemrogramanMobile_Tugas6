package com.yoyo.tugas6.movie;

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
import com.yoyo.tugas6.misc.OnItemClickListener;
import com.yoyo.tugas6.movie.recyclerView.NowPlayingAdapter;
import com.yoyo.tugas6.utils.Consts;
import com.yoyo.tugas6.utils.MovieService;
import com.yoyo.tugas6.utils.SingleRetrofit;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment {
    private RecyclerView rcNowPlaying;
    private NowPlayingAdapter nowPlayingAdapter;
//    private Database nowPlayingDatabase;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    public static NowPlayingFragment newInstance() {
        NowPlayingFragment fragment = new NowPlayingFragment();
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

        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        rcNowPlaying = view.findViewById(R.id.rc_now_playing);
        rcNowPlaying.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rcNowPlaying.setHasFixedSize(true);
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
        Call<NowPlayingResult> call = service.nowPlayingMovies(params);

        call.enqueue(new Callback<NowPlayingResult>() {
            @Override
            public void onResponse(Call<NowPlayingResult> call, Response<NowPlayingResult> response) {
                if(response.isSuccessful() && response.body().getNowPlayingList() != null) {
                    nowPlayingAdapter = new NowPlayingAdapter();
                    nowPlayingAdapter.setNowPlayingList(response.body().getNowPlayingList());
                    rcNowPlaying.setAdapter(nowPlayingAdapter);
                }
                else {
                    Log.d(Consts.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResult> call, Throwable t) {
                Log.d(Consts.APIERROR, t.getMessage());
            }

        });
    }


//    @Override
//    public void onClick(Integer integer) {
//        Intent intent = new Intent(getActivity(), )
//    }
}