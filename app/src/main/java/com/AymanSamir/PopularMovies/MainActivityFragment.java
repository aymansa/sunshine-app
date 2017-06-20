package com.AymanSamir.PopularMovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

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
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AdapterView.OnItemClickListener {

    GridView MovieGridView;
    private CustomAdapter adapter;
    private int result_pageNumber = 1;
    private ArrayList<Movie> moviesList;
    private static final String MOVIES_KEY = "movies";
    private static final String SORT_SETTING_KEY = "sort_setting";

   /* main m=new main();
    main.onConfigurationChanged();*/

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    //  @Override
    //  public void onSaveInstanceState(Bundle outState) {

    /////    outState.putParcelableArrayList("Movies", moviesList);
    ////   super.onSaveInstanceState(outState);

    // }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
                /*if (savedInstanceState.containsKey(SORT_SETTING_KEY)) {
                    mSortBy = savedInstanceState.getString(SORT_SETTING_KEY);
                }*/

            if (savedInstanceState.containsKey(MOVIES_KEY)) {
                moviesList = savedInstanceState.getParcelableArrayList(MOVIES_KEY);
                adapter.setData(moviesList);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        moviesList = new ArrayList<>();

        SharedPreferences settingsPreference = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);

        final String sortBy = settingsPreference.getString("sort_by", Settings.sortBypopularity());



        if (savedInstanceState == null) {
            URL url;
            try {

                if (sortBy.equals("favorite")) {

                    SQLiteMoviesAdapter sqLiteMoviesAdapter = new SQLiteMoviesAdapter(getActivity());

                    List<Movie> movies = new ArrayList<>();
                    movies = sqLiteMoviesAdapter.selectAllMovies();


                    for (Movie m : movies) {

                        RequestBuilder builder = new RequestBuilder();
                        url = builder.BuildSimilarMoviesRequest(m.getID(), result_pageNumber++);
                        FatchMoviesInfo fatchMoviesInfo = new FatchMoviesInfo();
                        fatchMoviesInfo.execute(url);

                    }

                } else {

                    RequestBuilder Builder = new RequestBuilder();
                    url = Builder.BuildDiscoverUrl(sortBy, result_pageNumber++);
                    FatchMoviesInfo fatchMoviesInfo = new FatchMoviesInfo();
                    fatchMoviesInfo.execute(url);
                }


            } catch (IOException e) {
                // Log.e("url error", e.toString());
            }
        }


        adapter = new CustomAdapter(getActivity(), moviesList);

        MovieGridView = (GridView) rootView.findViewById(R.id.movies_gridview);
        MovieGridView.setAdapter(adapter);

        MovieGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (MovieGridView.getLastVisiblePosition() >= MovieGridView.getCount() - MovieGridView.getNumColumns()) {


                    URL url;
                    try {

                        if (sortBy.equals("favorite")) {
                            SQLiteMoviesAdapter sqLiteMoviesAdapter = new SQLiteMoviesAdapter(getActivity());

                            List<Movie> movies = new ArrayList<>();
                            movies = sqLiteMoviesAdapter.selectAllMovies();


                            for (Movie m : movies) {

                                RequestBuilder builder = new RequestBuilder();
                                url = builder.BuildSimilarMoviesRequest(m.getID(), result_pageNumber++);
                                FatchMoviesInfo fatchMoviesInfo = new FatchMoviesInfo();
                                fatchMoviesInfo.execute(url);

                            }

                        } else {

                            RequestBuilder Builder = new RequestBuilder();
                            url = Builder.BuildDiscoverUrl(sortBy, result_pageNumber++);
                            FatchMoviesInfo fatchMoviesInfo = new FatchMoviesInfo();
                            fatchMoviesInfo.execute(url);

                        }


                    } catch (IOException e) {
                        Log.e("url error", e.toString());
                    }


                }

            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        MovieGridView.setOnItemClickListener(this);


        return rootView;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ((callBack) getActivity()).onItemClick(moviesList.get(position).getID());


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        /*if (!mSortBy.contentEquals(POPULARITY_DESC)) {
            outState.putString(SORT_SETTING_KEY, mSortBy);
        }*/

        if (moviesList != null) {
            outState.putParcelableArrayList(MOVIES_KEY, moviesList);
        }
        super.onSaveInstanceState(outState);
    }

    public interface callBack {
        public void onItemClick(String MovieId);

    }




    public class FatchMoviesInfo extends AsyncTask<URL, Void, Movie[]> {
        @Override
        protected Movie[] doInBackground(URL... params) {

            HttpURLConnection uriconnect = null;
            BufferedReader reader = null;


            Movie[] MovieList;


            try {

                URL url = params[0];
                uriconnect = (HttpURLConnection) url.openConnection();
                // Log.v("request url", url.toString());
                uriconnect.connect();

                InputStream inputStream = uriconnect.getInputStream();
                StringBuffer buffer = new StringBuffer();


                if (inputStream == null)
                    return null;


                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                String JsonString = buffer.toString();
                //  Log.e("Json" , JsonString + "no things");
                JSONParser parser = new JSONParser();
                MovieList = parser.getMoveiesList(JsonString);


                return MovieList;

            } catch (IOException e) {
                // Log.e("IO", e.toString());

            } catch (JSONException e) {
                //Log.e("JSON", e.toString());
            } finally {
                if (uriconnect != null) {
                    uriconnect.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        // Log.e("ForecastFragment", "Error closing stream", e);
                    }
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(Movie[] movies) {


            if (movies != null) {
                if (adapter != null) {

                    for (int i = 0; i < movies.length; i++) {
                        moviesList.add(movies[i]);
                    }
                    adapter.notifyDataSetChanged();
                    if (MainActivity.isTwoPane())
                        ((callBack) getActivity()).onItemClick(moviesList.get(0).getID());
                }
            }
        }
    }


}
