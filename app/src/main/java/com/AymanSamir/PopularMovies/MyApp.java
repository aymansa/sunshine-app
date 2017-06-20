package com.AymanSamir.PopularMovies;

/**
 * Created by ayman on 22/04/2016.
 */
import android.app.Application;

public class MyApp extends Application{

    private int i = 0;

    public int inc() {
        return ++i;
    }
}
