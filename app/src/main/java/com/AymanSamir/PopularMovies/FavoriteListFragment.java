package com.AymanSamir.PopularMovies;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayman on 21/04/2016.
 */
public class FavoriteListFragment extends Fragment {

    ListView favoritList_ListView ;
    ArrayAdapter<String> favoriteList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_favorite_list, container, false);

        favoritList_ListView = (ListView) rootView.findViewById(R.id.favorite_list);
        SQLiteMoviesAdapter sqLiteMoviesAdapter = new SQLiteMoviesAdapter(getActivity());
        final List<Movie> movies= sqLiteMoviesAdapter.selectAllMovies();


        List<String> MoviesTitles = new ArrayList<>();
        for(Movie m : movies)
        {
            MoviesTitles.add(m.getTitle());
        }
            Toast.makeText(getActivity() , Integer.toString(movies.size()) ,Toast.LENGTH_SHORT).show();

       favoriteList = new ArrayAdapter<>(getActivity() , R.layout.favorite_list_item , R.id.favorite_list_item , MoviesTitles);

        favoritList_ListView.setAdapter(favoriteList);


        favoritList_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity() , DetailsActivity.class);

                intent.putExtra("MovieId" , movies.get(position).getID());

                startActivity(intent);
            }
        });

        return rootView ;
    }


}
