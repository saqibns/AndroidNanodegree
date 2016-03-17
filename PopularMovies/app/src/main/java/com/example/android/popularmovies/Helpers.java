package com.example.android.popularmovies;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Saqib on 15-Mar-16.
 */
public class Helpers {

    private static String LOG_TAG = Helpers.class.getSimpleName();

    /**
     * Take the String representing the complete forecast in JSON Format and
     * pull out the data we need to construct the Strings needed for the wireframes.
     */
    public static Movie[] getMovieDataFromJson(String movieDetailJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String TMDB_LIST = "results";
        final String TMDB_TITLE = "original_title";
        final String TMDB_POSTER = "poster_path";
        final String TMDB_PLOT_SUMMARY = "overview";
        final String TMDB_RATING = "vote_average";
        final String TMDB_RELEASE_DATE = "release_date";

        JSONObject movieDetailObject = new JSONObject(movieDetailJsonStr);
        JSONArray movieDetailArray = movieDetailObject.getJSONArray(TMDB_LIST);

        int items = movieDetailArray.length();

        Movie[] results = new Movie[items];
        for(int i = 0; i < items; i++) {
            String title, posterPath, plot, rating, date;

            // Get the JSON object representing the movie
            JSONObject movieDetail = movieDetailArray.getJSONObject(i);

            title = movieDetail.getString(TMDB_TITLE);
            posterPath = movieDetail.getString(TMDB_POSTER);
            plot = movieDetail.getString(TMDB_PLOT_SUMMARY);
            rating = movieDetail.getString(TMDB_RATING) + "/10";
            String tmpDate = movieDetail.getString(TMDB_RELEASE_DATE);
            if(tmpDate.length() >= 4)
                date = tmpDate.substring(0, 4);
            else
                date = tmpDate;

            results[i] = new Movie(title, posterPath, plot, rating, date);
        }

        for (Movie s : results) {
            Log.v(LOG_TAG, "Forecast entry: " + s);
        }
        return results;
    }

    public static String buildUrl(String baseUri, String[] queryParams, String[] queryParamValues)
    {
        assert queryParams.length == queryParamValues.length;
        Uri.Builder builtUri = Uri.parse(baseUri).buildUpon();
        for(int i = 0; i < queryParams.length; i++)
        {
            builtUri.appendQueryParameter(queryParams[i], queryParamValues[i]);
        }
        Uri uri = builtUri.build();
        return uri.toString();
    }

    public static String getMovieData(String apiUrl)
    {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieDetailsJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL(apiUrl);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            movieDetailsJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return movieDetailsJsonStr;

    }

    public static String buildPosterUrl(String baseImageUrl, String size, String posterPath) {
        return baseImageUrl + size + posterPath;
    }
}
