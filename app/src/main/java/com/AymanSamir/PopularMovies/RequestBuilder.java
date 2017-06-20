package com.AymanSamir.PopularMovies;

import android.net.Uri;

import java.io.IOException;
import java.net.URL;

/**
 * Created by ayman on 21/04/2016.
 */
public class RequestBuilder {


    private final String BaseUrl               = "http://api.themoviedb.org/3/" ;
    private final String BaseUrlDiscoverMovies = BaseUrl + "discover/movie?";
    private final String sort_by               = "sort_by";
    private final String api_key               = "api_key";
    private final String page                  = "page" ;
    private final String BaseUrlMovie         = BaseUrl + "movie/";
    private final String reviews              = "reviews";
    private final String videos               = "videos";
    private final String similar              = "similar";





    public URL BuildDiscoverUrl( String sortBy_param , int pageNum_param ) throws IOException
    {


        Uri uriBuilder = Uri.parse(BaseUrlDiscoverMovies).buildUpon()
                .appendQueryParameter(sort_by , sortBy_param)
                .appendQueryParameter(page , Integer.toString( pageNum_param ))
                .appendQueryParameter(api_key , BuildConfig.MOVIES_API_KEY)
                .build();

        URL url = new URL(uriBuilder.toString());
        return url;
    }

    public URL BuildMovieByIdUrl(String MovieId) throws IOException
    {
        Uri uriBuilder = Uri.parse(BaseUrlMovie + MovieId).buildUpon()
                .appendQueryParameter(api_key , BuildConfig.MOVIES_API_KEY)
                .build();

        URL url = new URL(uriBuilder.toString());

        return url;
    }

    public URL BuildgetReviewReqest(String MovieId) throws IOException
    {
        Uri uriBuilder = Uri.parse(BaseUrlMovie + MovieId +"/" + reviews).buildUpon()
                .appendQueryParameter(api_key , BuildConfig.MOVIES_API_KEY)
                .build();

        URL url = new URL(uriBuilder.toString());

        return url;
    }

    public URL BuildGetTrailerReqest(String MovieId) throws IOException
    {
        Uri uriBuilder = Uri.parse(BaseUrlMovie + MovieId +"/" + videos).buildUpon()
                .appendQueryParameter(api_key , BuildConfig.MOVIES_API_KEY)
                .build();

        URL url = new URL(uriBuilder.toString());

        return url;
    }

    public URL BuildSimilarMoviesRequest(String MovieId , int pageNumber)throws IOException
    {
        Uri uriBuilder = Uri.parse(BaseUrlMovie + MovieId +"/" + similar).buildUpon()
                .appendQueryParameter(page,Integer.toString( pageNumber ))
                .appendQueryParameter(api_key, BuildConfig.MOVIES_API_KEY)
                .build();

        URL url = new URL(uriBuilder.toString());

        return url;

    }

}
