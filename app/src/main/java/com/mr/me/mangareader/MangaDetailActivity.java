package com.mr.me.mangareader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mr.me.mangareader.Adapter.EpisodeListAdapter;
import com.mr.me.mangareader.DataProvider.MangaProvider;
import com.mr.me.mangareader.Entities.Manga;

public class MangaDetailActivity extends AppCompatActivity {
    private Manga data;
    private boolean forceUpdate;
    private int mangaPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_detail);

        // Get Manga Detail
        Intent intent = this.getIntent();
        mangaPosition = intent.getIntExtra("Position", 0);
        setData(MangaProvider.getInstance().getMangaByPosition(mangaPosition));

        //region Set toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Set toolbar title
        TextView toolbarTitle = (TextView)findViewById(R.id.detail_toolbar_title);
        toolbarTitle.setText(getData().getTitle());
        //endregion

        // Set description
        TextView textView = (TextView) findViewById(R.id.manga_description);
        textView.setText(getData().getDescription());

        //region Set list adapter
        ListView listView = (ListView)findViewById(R.id.episode_list);
        listView.setAdapter(new EpisodeListAdapter(this, getData().getEpisodes()));
        forceUpdate = false;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                String episodeNo = ((TextView)v.findViewById(R.id.episode_number)).getText().toString();
                //Toast.makeText(getApplicationContext(), episodeNo, Toast.LENGTH_SHORT).show();
                forceUpdate = true;
                Intent intent = new Intent(MangaDetailActivity.this, ReadEpisodeActivity.class);
                intent.putExtra("PositionManga", MangaProvider.getInstance().getPositionByName(getData().getTitle()));
                intent.putExtra("EpisodeNumber", Integer.parseInt(episodeNo));
                startActivity(intent);
            }
        });
        //endregion
    }

    @Override
    protected void onResume() {
        if (forceUpdate){
            ListView listView = (ListView)findViewById(R.id.episode_list);
            setData(MangaProvider.getInstance().getMangaByPosition(mangaPosition));
            ((EpisodeListAdapter)listView.getAdapter()).setData(getData().getEpisodes());
            forceUpdate = false;
        }

        super.onResume();
    }

    //region Getter / Setter
    private Manga getData(){
        return data;
    }

    public void setData(Manga data) {
        this.data = data;
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

        if (item.getItemId() == R.id.add_episode_action) {
            Toast.makeText(getApplicationContext(), "Episode added!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manga_detail_menu, menu);
        return true;
    }

    //endregion
}
