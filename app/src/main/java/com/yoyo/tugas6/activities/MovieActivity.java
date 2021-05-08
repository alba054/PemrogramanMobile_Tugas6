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
import com.yoyo.tugas6.movie.MovieDetail;
import com.yoyo.tugas6.tv.TVShowDetail;
import com.yoyo.tugas6.utils.Consts;
import com.yoyo.tugas6.utils.MovieService;
import com.yoyo.tugas6.utils.SingleRetrofit;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {
    ImageView ivBackdrop, ivPoster;
    TextView tvTitle, tvReleaseDate, tvGenres, tvOverview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ivBackdrop = findViewById(R.id.iv_backdrop);
        ivPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvGenres = findViewById(R.id.tv_genres);
        tvOverview = findViewById(R.id.tv_overview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Integer movieId = getIntent().getIntExtra("MOVIE ID", 0);

        load(movieId);
    }

    private void load(Integer id) {
        MovieService service = SingleRetrofit.getInstance();

        Map<String, String> params = new HashMap<>();
        params.put("api_key", Consts.APIKEY);
        params.put("language", Consts.LANGUAGE);
        Call<MovieDetail> call = service.movieDetail(id, params);

        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if(response.isSuccessful() && response.body() != null) {
                    MovieDetail movieDetail = response.body();
                    tvTitle.setText(movieDetail.getTitle());
                    String genres = "";
                    for(Genre genre : movieDetail.getGenres()) {
                        genres += genre.getName() + ", ";
                    }

                    tvOverview.setText(movieDetail.getOverview());
                    tvReleaseDate.setText(movieDetail.getReleaseDate());
                    tvGenres.setText(genres);
                    String uri = Consts.IMAGEBASEURL + movieDetail.getBackdropImage();
                    String uri2 = Consts.IMAGEBASEURL + movieDetail.getPosterImage();
                    Glide.with(MovieActivity.this).load(uri).into(ivBackdrop);
                    Glide.with(MovieActivity.this).load(uri2).into(ivPoster);
                    Log.d("DEBUG GENRE", movieDetail.getGenres().get(0).getName());
                }
                else {
                    Log.d(Consts.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
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