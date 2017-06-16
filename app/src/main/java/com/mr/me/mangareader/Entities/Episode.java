package com.mr.me.mangareader.Entities;

import android.graphics.Color;
import com.mr.me.mangareader.R;

import java.util.List;

public class Episode {
    //region Fields
    private String title;
    private int number = 1;
    private boolean read;
    private List<EpisodePage> pages;
    //endregion

    public Episode(String title, int number, boolean read) {
        this.title = title;
        this.number = number;
        this.read = read;
    }

    public Episode(String title, int number) {
        this.title = title;
        this.number = number;
    }

    //region Getter & Setter
    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public int getColor(){
        return !read ? Color.BLACK : R.color.darkerBackgroundColor ;
    }

    public List<EpisodePage> getPages() {
        return pages;
    }

    public void setPages(List<EpisodePage> pages) {
        this.pages = pages;
    }

    public int getPageCount(){
        return 5; //pages.size();
    }

    public void setRead(boolean read){ this.read = read; }
    //endregion
}
