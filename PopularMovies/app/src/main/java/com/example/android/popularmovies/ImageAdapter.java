package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Saqib on 13-Mar-16.
 */
public class ImageAdapter extends ArrayAdapter<String> {
    private int layoutResourceId;
    private int imageViewId;
    public ImageAdapter(Context context, int layoutResourceId, int imageViewId, String[] urls)
    {
        super(context, layoutResourceId, urls);
        this.layoutResourceId = layoutResourceId;
        this.imageViewId = imageViewId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResourceId, parent, false);
            }

        imageView = (ImageView) convertView.findViewById(imageViewId);
        imageView.setLayoutParams(new GridView.LayoutParams(Constants.IMAGEVIEW_WIDTH, Constants.IMAGEVIEW_HEIGHT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(Constants.IMAGEVIEW_PADDING, Constants.IMAGEVIEW_PADDING, Constants.IMAGEVIEW_PADDING, Constants.IMAGEVIEW_PADDING);

        imageView.setImageResource(R.drawable.dandelion);
        return convertView;
    }
}
