package com.AymanSamir.PopularMovies;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayman on 21/04/2016.
 */
public class MovieReviewsFragment extends Fragment {


    String MovieId;
    ListView reviewListView ;
    private List<Review> reviewList ;
    CustomAdapterforReviews reviewsAdapter ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);


        View rootView = inflater.inflate(R.layout.fragment_movie_reviews, container, false);

        Intent intent = getActivity().getIntent();

        MovieId = intent.getStringExtra("MovieId");

        reviewListView = (ListView) rootView.findViewById(R.id.reviews);
        reviewList = new ArrayList<>();

        try {

            RequestBuilder builder = new RequestBuilder();
            URL url = builder.BuildgetReviewReqest(MovieId);
            FatchMovieReviews fatchMovieReviews = new FatchMovieReviews();
            fatchMovieReviews.execute(url);

        }catch (IOException e)
        {

        }
        reviewsAdapter = new CustomAdapterforReviews(  getActivity() ,reviewList);

        reviewListView.setAdapter(reviewsAdapter);

        return rootView;
    }


    class FatchMovieReviews extends AsyncTask<URL , Void , Review[] >
    {
        @Override
        protected Review[] doInBackground(URL... params) {
            HttpURLConnection uriconnect = null ;
            BufferedReader reader  = null ;

            Review[] MovieReview ;

            try {

                URL url = params[0];
                uriconnect = (HttpURLConnection) url.openConnection();
                Log.v("request url", url.toString());
                uriconnect.connect();

                InputStream inputStream = uriconnect.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if(inputStream == null)
                    return null ;


                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line ;
                while ( (line = reader.readLine()) != null  )
                {
                    buffer.append(line + "\n") ;
                }

                String JsonString = buffer.toString() ;

                JSONParser parser = new JSONParser();
                MovieReview = parser.getReviews(JsonString);


                return MovieReview ;

            }catch (IOException e)
            {
                Log.e("IO" , e.toString());

            }catch (JSONException e)
            {
                Log.e("JSON" , e.toString());
            }
            finally {
                if (uriconnect != null) {
                    uriconnect.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("ForecastFragment", "Error closing stream", e);
                    }
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(Review[] reviews) {

            if(reviews != null)
            {
                for (int i = 0; i < reviews.length; i++) {
                    reviewList.add(reviews[i]);
                }

                reviewsAdapter.notifyDataSetChanged();
            }
        }
    }
}
