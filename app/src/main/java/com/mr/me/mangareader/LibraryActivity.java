package com.mr.me.mangareader;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mr.me.mangareader.Adapter.MangaListAdapter;
import com.mr.me.mangareader.Adapter.OptionsAdapter;
import com.mr.me.mangareader.DataProvider.MangaProvider;
import com.mr.me.mangareader.Entities.Manga;
import com.mr.me.mangareader.Entities.MangaType;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    //region Fields
    List<Manga> mangaList;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        // Set toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.library_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mangaList = MangaProvider.getInstance().getMangaList();

        //Set list adapter
        ListView listView = (ListView)findViewById(R.id.library_list);
        listView.setAdapter(new MangaListAdapter(this, mangaList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                String mangaName = ((TextView)v.findViewById(R.id.manga_title)).getText().toString();

                Intent intent = new Intent(LibraryActivity.this, MangaDetailActivity.class);
                intent.putExtra("Position", MangaProvider.getInstance().getPositionByName(mangaName));
                startActivity(intent);
            }
        });
    }

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
    //endregion
}
