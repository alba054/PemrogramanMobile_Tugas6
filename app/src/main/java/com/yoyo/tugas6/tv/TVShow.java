package com.yoyo.tugas6.tv;

import com.google.gson.annotations.SerializedName;

public class TVShow {
    @SerializedName("poster_path")
    private String posterImage;

    @SerializedName("original_name")
    private String name;

    private Integer id;

    public String getPosterImage() {
        return posterImage;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
