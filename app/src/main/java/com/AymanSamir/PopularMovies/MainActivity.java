package com.AymanSamir.PopularMovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.callBack{



    private static boolean twoPane ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(findViewById(R.id.toolbar) != null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }
        if(findViewById(R.id.movie_detail_container) != null)
        {

            twoPane = true ;

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new DetailsActivityFragment() )
                        .commit();


        }else
        {
            twoPane = false ;

        }

    }


    public void onItemClick(String MovieId)
    {



        if(twoPane)
        {
           // detailsActivityFragment.changeData(MovieId);

            DetailsActivityFragment Fragment = new DetailsActivityFragment();
            Bundle args = new Bundle();
            args.putString("MovieId", MovieId);
            Fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, Fragment)
                    .commit();

        }else
        {

            Intent intent = new Intent(this,DetailsActivity.class);
            intent.putExtra("MovieId" , MovieId);

            startActivity(intent);
        }


    }

    public static boolean isTwoPane()
    {
        return twoPane ;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this , SettingsActivity.class) ;

            startActivity(i);
        }
        else if(id == R.id.favorite)
        {
            Intent i = new Intent(this , FavoriteListActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
