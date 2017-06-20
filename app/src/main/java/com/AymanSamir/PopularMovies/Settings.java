package com.AymanSamir.PopularMovies;

/**
 * Created by ayman on 21/04/2016.
 */
 public  class Settings {


    private static final String popularityDesc = "popularity.desc";
    private static final String vote_countDesc = "vote_count.desc";
    private static final String favorite = "favorite";


    public static String sortBypopularity()
    {
        return popularityDesc;
    }

    public   static String sortByVote_count()
    {
        return vote_countDesc;
    }

    public static String sortByfavorite() {return favorite ; }
}
