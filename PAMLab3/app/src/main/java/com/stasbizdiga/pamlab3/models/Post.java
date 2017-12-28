package com.stasbizdiga.pamlab3.models;


public class Post {

    private String title;
    private String author;
    private String link;
    private String description;
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {this.description = description;}

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {this.pubDate = pubDate;}

}
