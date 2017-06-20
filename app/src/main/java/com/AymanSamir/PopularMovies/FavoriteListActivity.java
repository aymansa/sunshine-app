package com.AymanSamir.PopularMovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ayman on 21/04/2016.
 */
public class FavoriteListActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorite_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.favorite_fragment_placehoder, new FavoriteListFragment())
                    .commit();
        }
    }
}
