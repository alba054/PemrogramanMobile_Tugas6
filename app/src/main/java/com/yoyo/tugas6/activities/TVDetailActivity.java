package com.yoyo.tugas6.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yoyo.tugas6.R;
import com.yoyo.tugas6.misc.Genre;
import com.yoyo.tugas6.movie.NowPlayingResult;
import com.yoyo.tugas6.movie.recyclerView.NowPlayingAdapter;
import com.yoyo.tugas6.tv.TVShowDetail;
import com.yoyo.tugas6.utils.Consts;
import com.yoyo.tugas6.utils.MovieService;
import com.yoyo.tugas6.utils.SingleRetrofit;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVDetailActivity extends AppCompatActivity {
    ImageView ivBackdrop, ivPoster;
    TextView tvTitle, tvNumOfEpisodes, tvGenres, tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_v_detail);

        ivBackdrop = findViewById(R.id.iv_backdrop);
        ivPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        tvNumOfEpisodes = findViewById(R.id.tv_episodes_seasons);
        tvGenres = findViewById(R.id.tv_genres);
        tvOverview = findViewById(R.id.tv_overview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Integer tvId = getIntent().getIntExtra("TV ID", 0);
        load(tvId);

    }

    private void load(Integer id) {
        MovieService service = SingleRetrofit.getInstance();

        Map<String, String> params = new HashMap<>();
        params.put("api_key", Consts.APIKEY);
        params.put("language", Consts.LANGUAGE);
        Call<TVShowDetail> call = service.tvDetail(id, params);

        call.enqueue(new Callback<TVShowDetail>() {
            @Override
            public void onResponse(Call<TVShowDetail> call, Response<TVShowDetail> response) {
                if(response.isSuccessful() && response.body() != null) {
                    TVShowDetail tvShowDetail = response.body();
                    tvTitle.setText(tvShowDetail.getTitle());
                    String episodesNSeasons = tvShowDetail.getEpisodes() + " Episodes"
                            + " | "
                            + tvShowDetail.getSeasons() + " Seasons";
                    String genres = "";
                    for(Genre genre : tvShowDetail.getGenres()) {
                        genres += genre.getName() + ", ";
                    }

                    tvOverview.setText(tvShowDetail.getOverview());
                    tvNumOfEpisodes.setText(episodesNSeasons);
                    tvGenres.setText(genres);
                    String uri = Consts.IMAGEBASEURL + tvShowDetail.getBackdropImage();
                    String uri2 = Consts.IMAGEBASEURL + tvShowDetail.getPosterImage();
                    Glide.with(TVDetailActivity.this).load(uri).into(ivBackdrop);
                    Glide.with(TVDetailActivity.this).load(uri2).into(ivPoster);
                    Log.d("DEBUG GENRE", tvShowDetail.getGenres().get(0).getName());
                }
                else {
                    Log.d(Consts.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<TVShowDetail> call, Throwable t) {
                Log.d(Consts.APIERROR, t.getMessage());
            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}