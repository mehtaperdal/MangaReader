package com.mr.me.mangareader.Entities;

import android.graphics.Bitmap;

public class EpisodePage {
    //region
    private Bitmap image;
    private int position;
    //endregion


    public EpisodePage(Bitmap image, int position) {
        this.image = image;
        this.position = position;
    }

    //region Getters & Setters
    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    //endregion
}
