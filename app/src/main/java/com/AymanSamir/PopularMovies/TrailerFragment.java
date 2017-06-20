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
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ayman on 21/04/2016.
 */
public class TrailerFragment extends Fragment {


    String MovieId;
    WebView webView;
    TrailerDetails trailerDetails ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_trailer , container , false);
        webView = (WebView) rootView.findViewById(R.id.video_player);
        Intent intent = getActivity().getIntent();
        MovieId = intent.getStringExtra("MovieId");
        try {
            RequestBuilder  builder = new RequestBuilder();
           URL url =  builder.BuildGetTrailerReqest(MovieId);
            FatechTrailerDatails fatechTrailerDatails = new FatechTrailerDatails();
            fatechTrailerDatails.execute(url);
        }catch (IOException e)
        {
            Log.e("UrlError TrailerReqest" , e.toString());
        }


        return  rootView ;
    }

    class FatechTrailerDatails extends AsyncTask<URL, Void ,TrailerDetails>
    {
        @Override
        protected TrailerDetails doInBackground(URL... params) {


            HttpURLConnection urlConnection = null ;
            BufferedReader reader = null ;

            try {
                URL url = params[0];

                Log.e("url" , url.toString());
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ( (line = reader.readLine()) != null)
                {
                    buffer.append(line + "/n");
                }

                String jsonString = buffer.toString();

                JSONParser jsonParser = new JSONParser();

                TrailerDetails trailerDetails = jsonParser.getTrailerDetails(jsonString);

                return trailerDetails ;
            }catch (IOException e)
            {

                Log.e("nehal" , e.toString());
            }catch (JSONException e)
            {
                Log.e("nehal" , e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(TrailerDetails trailerDetails) {

            if(trailerDetails != null)
            {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                webView.loadUrl( trailerDetails.url + "?autoplay=1&vq=small");
                webView.setWebChromeClient(new WebChromeClient());
            }else
            {
                Toast.makeText(getActivity() , "No vidos found" , Toast.LENGTH_LONG).show();
            }
        }
    }
}
