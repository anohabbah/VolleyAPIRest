package com.example.volleyexample;

// a simple class that represents a post
public class Post {
    private int id;
    private String title;
    private String author;

    public Post(int id,String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
