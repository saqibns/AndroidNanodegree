package com.example.android.popularmovies;

/**
 * Created by Saqib on 15-Mar-16.
 */
public class Movie {

    private String originalTitle;
    private String posterPath;
    private String plotSynopsis;
    private String userRating;
    private String releaseDate;

    public Movie(String originalTitle, String posterPath, String plotSynopsis, String userRating, String releaseDate) {
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public Movie(String[] detail)
    {
        this.originalTitle = detail[0];
        this.posterPath = detail[1];
        plotSynopsis = detail[2];
        userRating = detail[3];
        releaseDate = detail[4];
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("{")
          .append("Original Title: ")
          .append(originalTitle)
          .append(", ")
          .append("Poster Path: ")
          .append(posterPath)
          .append(", ")
          .append("Plot Synopsis: ")
          .append(plotSynopsis)
          .append(", ")
          .append("User Rating: ")
          .append(userRating)
          .append(", ")
          .append("Release Date")
          .append(releaseDate)
          .append("}");

        return sb.toString();
    }

    public String[] toStringArray()
    {
        String[] detail = new String[5];
        detail[0] = originalTitle;
        detail[1] = posterPath;
        detail[2] = plotSynopsis;
        detail[3] = userRating;
        detail[4] = releaseDate;

        return detail;
    }

}
