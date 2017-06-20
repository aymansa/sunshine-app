package com.AymanSamir.PopularMovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ayman on 21/04/2016.
 */
public class MovieReviewsActivity  extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_reviews);

        if(savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.review_fragment_placeholder , new MovieReviewsFragment())
                    .commit();
        }
    }
}
