package com.example.android.moviebrowser;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Movie> movies = QueryUtils.fetchMovieData(QueryUtils.MOVIES_JSON_URL);
        MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        ListView moviesView = findViewById(R.id.movies_list);
        moviesView.setAdapter(movieAdapter);

    }

    class MovieAsyncTask extends AsyncTask<String, Void, List<Movie>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Movie> doInBackground(String... strings) {
            return QueryUtils.fetchMovieData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
        }
    }
}
