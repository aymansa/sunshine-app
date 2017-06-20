package com.AymanSamir.PopularMovies;

/**
 * Created by ayman on 21/04/2016.
 */
public class TrailerDetails {


    String Id ;
    String url ;
    String name ;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    String site ;
}
