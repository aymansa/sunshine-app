package com.AymanSamir.PopularMovies;

import java.net.URL;

/**
 * Created by ayman on 21/04/2016.
 */
public class Review {

    String id ;
    String content;
    String author ;
    URL url ;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
