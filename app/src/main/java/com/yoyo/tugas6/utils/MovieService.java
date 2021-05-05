package com.yoyo.tugas6.utils;

import com.yoyo.tugas6.movie.MovieDetail;
import com.yoyo.tugas6.movie.NowPlaying;
import com.yoyo.tugas6.movie.NowPlayingResult;
import com.yoyo.tugas6.tv.TVResult;
import com.yoyo.tugas6.tv.TVShow;
import com.yoyo.tugas6.tv.TVShowDetail;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MovieService {
    @GET("movie/now_playing")
    Call<NowPlayingResult> nowPlayingMovies(@QueryMap Map<String, String> options);

    @GET("movie/{movie_id}")
    Call<MovieDetail> movieDetail(@Path ("movie_id") int movieId, @QueryMap Map<String, String> options);

    @GET("tv/airing_today")
    Call<TVResult> tvAiringToday(@QueryMap Map<String, String> options);

    @GET("tv/{tv_id}")
    Call<TVShowDetail> tvDetail(@Path ("tv_id") int movieId, @QueryMap Map<String, String> options);
}
