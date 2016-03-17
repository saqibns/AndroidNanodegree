package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private String LOG_TAG = DetailActivityFragment.class.getSimpleName();

    public DetailActivityFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView title = (TextView) rootView.findViewById(R.id.detail_title_textview);
        LinearLayout level1Layout = (LinearLayout) rootView.findViewById(R.id.detail_level1_linear_layout);

        ImageView poster = (ImageView) level1Layout.findViewById(R.id.detail_poster_imageview);

        LinearLayout level2Layout = (LinearLayout) level1Layout.findViewById(R.id.detail_level2_linear_layout);

        TextView date = (TextView) level2Layout.findViewById(R.id.detail_release_date_textview);
        TextView rating = (TextView) level2Layout.findViewById(R.id.detail_rating_textview);

        TextView plotSummary = (TextView) rootView.findViewById(R.id.detail_summary_textview);

        Intent intent = getActivity().getIntent();
        if(intent != null && intent.hasExtra(Constants.MOVIE_DETAIL_INTENT))
        {
            String[] movieDetail = intent.getStringArrayExtra(Constants.MOVIE_DETAIL_INTENT);
            Movie movie = new Movie(movieDetail);
            title.setText(movie.getOriginalTitle());

            //poster.setImageResource(R.drawable.dandelion);
            //Picasso.with(getActivity()).load(R.drawable.dandelion).into(poster);

            String posterUrl = Helpers.buildPosterUrl(Constants.IMAGE_BASE_URI, Constants.PREFFERED_POSTER_SIZE_DETAIL, movie.getPosterPath());
            Picasso.with(getActivity()).load(posterUrl).into(poster);
            Log.v(LOG_TAG, posterUrl);


            poster.setScaleType(ImageView.ScaleType.CENTER_CROP);

            date.setText(movie.getReleaseDate());
            rating.setText(movie.getUserRating());
            plotSummary.setText(movie.getPlotSynopsis());

        }


        return rootView;
    }
}
