package com.mr.me.mangareader.Entities;

import java.util.ArrayList;
import java.util.List;

public class Manga {

    //region Fields
    private Integer imageSource;
    private String title;
    private String description;
    private List<MangaType> tags;
    private List<Episode> episodes;
    //endregion

    //region Constructors
    public Manga() {
        title = "";
        tags = new ArrayList<MangaType>();
    }

    public Manga(String title,  String description, List<MangaType> tags, List<Episode> episodes, Integer imageSource) {
        this.imageSource = imageSource;
        this.title = title;
        this.episodes = episodes;
        this.description = description;
        this.tags = tags;
    }
    //endregion

    @Override
    public String toString() {

        String tagsList;
        tagsList = "";
        for (MangaType item : tags) {
           tagsList += item.toString() + ", ";
        }

        return tagsList;
    }

    //region Getters & Setters
    public Integer getImageSource() {
        return imageSource;
    }

    public void setImageSource(Integer imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return (title != null) ? title : "";
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public String getDescription() {
        return description;
    }

    public int getEpisodeNoByName(String episodeName){
        for (Episode item: episodes) {
            if (item.getTitle().compareTo(episodeName) == 0)
                return item.getNumber();
        }
        return 1;
    }

    public Episode getEpisode(int number){
        for (Episode episode: episodes) {
            if (episode.getNumber() == number)
                return episode;
        }

        return episodes.get(0);
    }
    //endregion
}
