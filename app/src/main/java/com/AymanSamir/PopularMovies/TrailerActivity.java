package com.AymanSamir.PopularMovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ayman on 21/04/2016.
 */
public class TrailerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trailer);


        if(savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_trailer_placeholder  , new TrailerFragment() )
                   .commit();
        }
    }
}
