package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Saqib on 13-Mar-16.
 */
public class ImageAdapter extends ArrayAdapter<Movie> {
    private String LOG_TAG = ImageAdapter.class.getSimpleName();
    private Context context;
    private int layoutResourceId;
    private int imageViewId;
    public ImageAdapter(Context context, int layoutResourceId, int imageViewId, List<Movie> movies)
    {
        super(context, layoutResourceId, movies);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.imageViewId = imageViewId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Movie current = getItem(position);
        ImageView imageView;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
            }

        imageView = (ImageView) convertView.findViewById(imageViewId);
        imageView.setLayoutParams(new GridView.LayoutParams(Constants.IMAGEVIEW_WIDTH, Constants.IMAGEVIEW_HEIGHT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(Constants.IMAGEVIEW_PADDING, Constants.IMAGEVIEW_PADDING, Constants.IMAGEVIEW_PADDING, Constants.IMAGEVIEW_PADDING);

        //imageView.setImageResource(R.drawable.dandelion);
        //Picasso.with(context).load(R.drawable.dandelion).into(imageView);
        String posterUrl = Helpers.buildPosterUrl(Constants.IMAGE_BASE_URI, Constants.PREFERRED_POSTER_SIZE_MAIN, current.getPosterPath());
        Picasso.with(context).load(posterUrl).into(imageView);
        //Log.v(LOG_TAG, "Poster URL: " + posterUrl);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constants.MOVIE_DETAIL_INTENT, current.toStringArray());
                context.startActivity(intent);

            }
        });
        return convertView;
    }
}
