package com.yoyo.tugas6.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yoyo.tugas6.FavoriteFragment;
import com.yoyo.tugas6.R;
import com.yoyo.tugas6.movie.NowPlayingFragment;
import com.yoyo.tugas6.tv.TVFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private Map<Integer, Fragment> fragmentMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_bottom);
        fragmentMap = new HashMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentMap.put(R.id.now_playing, NowPlayingFragment.newInstance());
        fragmentMap.put(R.id.tv_show, TVFragment.newInstance());
        fragmentMap.put(R.id.favorites, FavoriteFragment.newInstance());

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.now_playing);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMap.get(item.getItemId());
        assert fragment != null;

        switch(item.getItemId()) {
            case R.id.favorites:
                getSupportActionBar().setTitle("Favorites");
                break;
            case R.id.tv_show:
                getSupportActionBar().setTitle("TV Show");
                break;
            case R.id.now_playing:
                getSupportActionBar().setTitle("Now Playing");
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_main, fragment)
                .commit();

        return true;
    }
}