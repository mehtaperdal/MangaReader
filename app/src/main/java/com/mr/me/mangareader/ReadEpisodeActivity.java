package com.mr.me.mangareader;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.mr.me.mangareader.Adapter.EpisodePageAdapter;
import com.mr.me.mangareader.DataProvider.MangaProvider;
import com.mr.me.mangareader.Entities.Episode;
import com.mr.me.mangareader.Entities.Manga;

public class ReadEpisodeActivity extends AppCompatActivity {
    //region Fields
    private Manga manga;
    private Episode episode;
    private ViewPager ePager;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_episode);

        //region Get Manga&Episode Detail
        Bundle bundle = this.getIntent().getExtras();
        int mangaPosition = bundle.getInt("PositionManga", 0);
        int episodeNo = bundle.getInt("EpisodeNumber", 1);

        setManga(MangaProvider.getInstance().getMangaByPosition(mangaPosition));
        setEpisode(manga.getEpisode(episodeNo));
        //Set last read
        MangaProvider.getInstance().setLastRead(manga);
        episode.setRead(true);
        //endregion

        //region Set toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.episode_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Set toolbar title
        TextView toolbarTitle = (TextView)findViewById(R.id.detail_toolbar_title);
        toolbarTitle.setText(getEpisode().getTitle());
        //endregion

        //region Initialize adapter
        ePager = (ViewPager) findViewById(R.id.pager);
        ePager.setAdapter(new EpisodePageAdapter(getSupportFragmentManager(),manga, episode));
        //endregion
    }

    //region Getter & Setter
    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    //endregion

    //region UI Events
    /**
     * Go Back Method
     * @param item back button in toolbar
     * @return go back action
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int current = ePager.getCurrentItem();
        if (current == 0)
            super.onBackPressed();
        else
            ePager.setCurrentItem(current -1);
    }
    //endregion
}
