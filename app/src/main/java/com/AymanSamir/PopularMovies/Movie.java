package com.AymanSamir.PopularMovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ayman on 21/04/2016.
 */
 public class Movie implements Parcelable {


    String ID;
    String Title;
    String description;// overview
    String tagline;
    String poster_path;//poster_path image
    String backdrop_path;//image2
    String release_date;
    String budget;
    String homepage;
    double popularity;
    String vote_average;

    double similerMoviePages;
    String[] genres;
    Integer vote_count;
    boolean isInfav;


    public double getSimilerMoviePages() {
        return similerMoviePages;
    }

    public void setSimilerMoviePages(double similerMoviePages) {
        this.similerMoviePages = similerMoviePages;
    }


    public boolean isInfav() {
        return isInfav;
    }

    public void setIsInfav(boolean isInfav) {
        this.isInfav = isInfav;
    }

    public double getRuntime() {
        return Runtime;
    }

    public void setRuntime(double runtime) {
        Runtime = runtime;
    }

    double Runtime;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };



    public Movie(Parcel in) {

        ID = in.readString();
        Title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        description = in.readString();
        vote_average = in.readString();
        release_date = in.readString();


    }

    public Movie() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(Title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(description);
        dest.writeString(vote_average);
        dest.writeString(release_date);

    }
/*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.ID);
        dest.writeString(this.Title);
        dest.writeString(this.description);
        dest.writeString(this.tagline);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.release_date);
        dest.writeString(this.budget);
        dest.writeString(this.homepage);
        dest.writeStringArray(this.genres);
        dest.writeDouble(this.popularity);
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.vote_count);
        dest.writeByte((byte) ((this.isInfav == true) ? 1 : 0));

    }
    */



}