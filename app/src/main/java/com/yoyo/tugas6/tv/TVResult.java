package com.yoyo.tugas6.tv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVResult {

    @SerializedName("results")
    @Expose
    List<TVShow> tvShowList;

    public List<TVShow> getTvShowList() {
        return tvShowList;
    }
}
