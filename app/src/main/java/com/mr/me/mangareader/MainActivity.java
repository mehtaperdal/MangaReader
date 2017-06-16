package com.mr.me.mangareader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import com.mr.me.mangareader.Adapter.OptionsAdapter;
import com.mr.me.mangareader.DataProvider.MangaProvider;
import com.mr.me.mangareader.Entities.Manga;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    public static final int GET_FROM_GALLERY = 3;
    private Manga lastManga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if( getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        //region Set grid adapter
        GridView gridView = (GridView)findViewById(R.id.options);
        gridView.setAdapter(new OptionsAdapter(this));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                String name = ((TextView)v.findViewById(R.id.button_name)).getText().toString();
                if (name.compareTo("Library") == 0)
                    startActivity(new Intent(MainActivity.this, LibraryActivity.class));

                if(name.compareTo("Import") == 0)
                    startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });
        //endregion
        //region Last Manga View
        lastManga = MangaProvider.getInstance().getLastRead();
        setLastManga();
        //endregion

    }

    @Override
    protected void onResume() {
        if (lastManga != MangaProvider.getInstance().getLastRead())
        {
            lastManga = MangaProvider.getInstance().getLastRead();
            setLastManga();
        }

        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                Toast.makeText(getApplicationContext(), "Image loaded successfully!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //region Helper
    private void setLastManga(){
        LinearLayout layout = (LinearLayout) findViewById(R.id.last_manga_item);
        TextView textView = (TextView) layout.findViewById(R.id.manga_title);
        textView.setText(lastManga.getTitle());

        TextView tagsView = (TextView) layout.findViewById(R.id.manga_tags);
        tagsView.setText(lastManga.toString());

        // set image based on selected text
        ImageView imageView = (ImageView) layout.findViewById(R.id.manga_image);
        imageView.setImageResource(lastManga.getImageSource());
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        layout.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, MangaDetailActivity.class);
                intent.putExtra("Position", MangaProvider.getInstance().getPositionByName(lastManga.getTitle()));
                startActivity(intent);
            }
        });
    }

    //endregion
}
