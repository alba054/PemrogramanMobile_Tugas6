package com.yoyo.tugas6.tv;

import com.google.gson.annotations.SerializedName;
import com.yoyo.tugas6.misc.Genre;

import java.util.List;

public class TVShowDetail {

    @SerializedName("name")
    private String title;

    @SerializedName("number_of_episodes")
    private String episodes;

    @SerializedName("number_of_seasons")
    private String seasons;

    private int id;

    @SerializedName("backdrop_path")
    private String backdropImage;

    @SerializedName("poster_path")
    private String posterImage;

    public String getSeasons() {
        return seasons;
    }

    @SerializedName("genres")
    private List<Genre> genres;

    private String overview;

    public String getTitle() {
        return title;
    }

    public String getEpisodes() {
        return episodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdropImage() {
        return backdropImage;
    }


    public String getPosterImage() {
        return posterImage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getOverview() {
        return overview;
    }

}
