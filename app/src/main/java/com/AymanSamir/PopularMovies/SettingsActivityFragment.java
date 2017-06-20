package com.AymanSamir.PopularMovies;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by ayman on 21/04/2016.
 */
public class SettingsActivityFragment extends Fragment {

     String sortBy ;

    RadioGroup  radioGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_settings, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        radioGroup = (RadioGroup) rootview.findViewById(R.id.settings_radioGroup);

        sortBy = preferences.getString("sort_by", Settings.sortBypopularity());

        Toast.makeText(getActivity() , sortBy , Toast.LENGTH_SHORT).show();

        if(sortBy == Settings.sortByVote_count())
        {
            radioGroup.check(R.id.sort_by_vote_count);
        }
        if(sortBy == Settings.sortBypopularity() )
        {
            radioGroup.check(R.id.sort_by_popularity);
        }else if (sortBy == Settings.sortByfavorite())
        {
            radioGroup.check(R.id.sort_by_favorite);
        }



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                SharedPreferences settingsPreferences = getActivity().getSharedPreferences("settings" , Context.MODE_PRIVATE);

                SharedPreferences.Editor editor =  settingsPreferences.edit();

                Settings settings = new Settings();

                if(checkedId == R.id.sort_by_favorite)
                {
                    editor.putString("sort_by", settings.sortByfavorite());

                }else if(checkedId == R.id.sort_by_popularity)
                {
                    editor.putString("sort_by" , settings.sortBypopularity());

                }else if(checkedId == R.id.sort_by_vote_count)
                {
                    editor.putString("sort_by" , settings.sortByVote_count());
                }

                editor.commit();
            }
        });

        return rootview ;

    }
}
