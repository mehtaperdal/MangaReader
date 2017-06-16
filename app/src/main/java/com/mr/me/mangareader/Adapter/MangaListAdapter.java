package com.mr.me.mangareader.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.annotation.NonNull;

import com.mr.me.mangareader.Entities.Manga;
import com.mr.me.mangareader.R;

public class MangaListAdapter extends ArrayAdapter<Manga> {
    //region Constructors
    public MangaListAdapter(Context context, List<Manga> objects) {
        super(context, 0, objects);
    }
    //endregion

    //region Override Methods
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Manga mangaItem = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.manga_item, parent, false);

        TextView textView = (TextView) convertView.findViewById(R.id.manga_title);
        textView.setText(mangaItem.getTitle());

        TextView tagsView = (TextView) convertView.findViewById(R.id.manga_tags);
        tagsView.setText(mangaItem.toString());

        // set image based on selected text
        ImageView imageView = (ImageView) convertView.findViewById(R.id.manga_image);
        imageView.setImageResource(mangaItem.getImageSource());
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        return convertView;
    }
    //endregion
}

