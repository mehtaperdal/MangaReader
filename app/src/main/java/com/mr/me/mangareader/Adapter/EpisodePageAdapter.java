package com.mr.me.mangareader.Adapter;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.mr.me.mangareader.DataProvider.MangaProvider;
import com.mr.me.mangareader.Entities.Episode;
import com.mr.me.mangareader.Entities.Manga;

import layout.EpisodePageFragment;

/**
 * Adapter for episode pages.
 */

public class EpisodePageAdapter extends FragmentStatePagerAdapter {

    private Manga manga;
    private Episode episode;

    public EpisodePageAdapter(FragmentManager fm, Manga manga, Episode episode) {
        super(fm);
        this.manga = manga;
        this.episode = episode;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment page = new EpisodePageFragment();

        Bundle args = new Bundle();
        args.putInt("EpisodeNumber", episode.getNumber());
        args.putInt("MangaPosition", MangaProvider.getInstance().getPositionByName(manga.getTitle()));
        page.setArguments(args);

        return page;
    }

    @Override
    public int getCount() {
        return episode.getPageCount();
    }
}
