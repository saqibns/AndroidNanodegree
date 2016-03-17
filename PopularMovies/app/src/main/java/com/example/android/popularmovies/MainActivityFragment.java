package com.example.android.popularmovies;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private ImageAdapter mAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.main_activity_grid_view);
        List<Movie> movies = new ArrayList<>();

        mAdapter = new ImageAdapter(getActivity(), R.layout.movie_poster, R.id.movie_poster_imageview, movies);
        gridView.setAdapter(mAdapter);
        updateUI();
        return rootView;
    }

    private void updateUI() {
        String url;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = preferences.getString(getString(R.string.pref_sort_key), getString(R.string.sort_most_popular));
        if(sortOrder.equals(getString(R.string.sort_most_popular))) {
            url = Helpers.buildUrl(Constants.BASE_URI, new String[]{Constants.SORT_PARAM, Constants.KEY_PARAM}, new String[]{Constants.POPULARITY_SORT, Constants.KEY});
        }
        else {
            url = Helpers.buildUrl(Constants.BASE_URI, new String[] {Constants.SORT_PARAM, Constants.KEY_PARAM}, new String[] {Constants.RATING_SORT, Constants.KEY});
        }

        FetchMovieDetailsTask task = new FetchMovieDetailsTask();
        task.execute(url);
    }

    private class FetchMovieDetailsTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected Movie[] doInBackground(String... params) {
            Movie[] movies;
            movies = new Movie[0];
            String movieDetailJsonStr = Helpers.getMovieData(params[0]);
            Log.v(LOG_TAG, "JSON: " + movieDetailJsonStr);
            try {
                if(movieDetailJsonStr != null)
                    movies = Helpers.getMovieDataFromJson(movieDetailJsonStr);
                    Log.v(LOG_TAG, "Successful in Parsing JSON");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movies;
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            if(movies != null)
            {
                mAdapter.clear();
                mAdapter.addAll(movies);
            }
        }
    }

}
