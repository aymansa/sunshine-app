package com.AymanSamir.PopularMovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ayman on 21/04/2016.
 */
public class DetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        if(savedInstanceState == null)
        {
            Bundle args = new Bundle();
            Intent intent = getIntent();
            args.putString("MovieId", intent.getStringExtra("MovieId"));
            DetailsActivityFragment fragment = new DetailsActivityFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container , fragment )
                    .commit();
        }


    }
}
