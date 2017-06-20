package com.AymanSamir.PopularMovies;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

/**
 * Created by ayman on 21/04/2016.
 */
public class JSONParser {



    public Movie[] getMoveiesList( String JSONstring ) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(JSONstring) ;

        JSONArray resultMovie = jsonObject.getJSONArray("results");
        Movie[] movieList  = new Movie[resultMovie.length()  ];

        for (Integer i = 0 ; i < resultMovie.length() ; i++)
        {
            JSONObject movieInfo = resultMovie.getJSONObject(i);

            Movie movie = new Movie();

            movie.setID(  movieInfo.getString("id")  );
            movie.setTitle(movieInfo.getString("title"));
            movie.setDescription(movieInfo.getString("overview"));
            movie.setRelease_date(movieInfo.getString("release_date"));
            movie.setPoster_path("http://image.tmdb.org/t/p/w185/" + movieInfo.getString("poster_path"));
            movie.setBackdrop_path("http://image.tmdb.org/t/p/w185/" + movieInfo.getString("backdrop_path"));
            movie.setVote_average(movieInfo.getString("vote_average"));
            movie.setVote_count(movieInfo.getInt("vote_count"));

            movieList[i] = movie ;

        }


        return movieList;
    }


    public Movie getMovieObject(String jsonString) throws JSONException
    {
        JSONObject movieInfo = new JSONObject(jsonString);
        Movie movie = new Movie();

        movie.setID(movieInfo.getString("id"));
        movie.setBudget(movieInfo.getString("budget"));
        movie.setTitle(movieInfo.getString("title"));
        movie.setDescription(movieInfo.getString("overview"));
        movie.setTagline(movieInfo.getString("tagline"));
        movie.setRelease_date(movieInfo.getString("release_date"));
        movie.setPoster_path("http://image.tmdb.org/t/p/w185/" + movieInfo.getString("poster_path"));
        movie.setBackdrop_path("http://image.tmdb.org/t/p/w185/" + movieInfo.getString("backdrop_path"));
        movie.setVote_average(movieInfo.getString("vote_average"));
        movie.setVote_count(movieInfo.getInt("vote_count"));
        movie.setRuntime(movieInfo.getDouble("runtime"));
        JSONArray Jsongenres = movieInfo.getJSONArray("genres");

        String[] genres = new String[Jsongenres.length()] ;

        for(int i=0 ; i < genres.length ; i++)
        {

            JSONObject genre = Jsongenres.getJSONObject(i);
            genres[i] = genre.getString("name");
        }

        movie.setGenres(genres);

        return movie;

    }

    public TrailerDetails getTrailerDetails(String jsonString ) throws JSONException
    {

        TrailerDetails trailerDetails = new TrailerDetails();

        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray results  = jsonObject.getJSONArray("results");


        for(int i=0 ; i < results.length() ; i++) {
            JSONObject trailer = results.getJSONObject(i);

                trailerDetails.setId(trailer.getString("id"));
                trailerDetails.setName(trailer.getString("name"));

                trailerDetails.setUrl("https://www.youtube.com/embed/" + trailer.getString("key"));

                return  trailerDetails ;


        }
        return null ;
    }


    public Review[] getReviews(String jsonString ) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray results = jsonObject.getJSONArray("results");
       Review[] reviews = new Review[results.length()];
        for(int i = 0 ; i < results.length() ; i++)
        {
            try {


                Review review = new Review();
                JSONObject reviewJsonObject = results.getJSONObject(i);

                review.setId(reviewJsonObject.getString("id"));
                review.setAuthor(reviewJsonObject.getString("author"));
                review.setContent(reviewJsonObject.getString("content"));
                review.setUrl( new URL(reviewJsonObject.getString("url")));
                reviews[i] = review ;
            }catch (IOException e)
            {
               // Log.e("UrlError" , e.toString());
            }
        }

        return reviews ;

    }

}
