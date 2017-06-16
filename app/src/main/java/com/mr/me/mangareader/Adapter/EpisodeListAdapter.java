package com.mr.me.mangareader.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mr.me.mangareader.Entities.Episode;
import com.mr.me.mangareader.R;

import java.util.List;

/**
 * Adapter for episode list in manga detail page.
 */

public class EpisodeListAdapter extends ArrayAdapter<Episode> {
    //region Constructors
    public EpisodeListAdapter(Context context, List<Episode> objects) {
        super(context, 0, objects);
    }
    //endregion

    //region Override Methods
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Episode episodeItem = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.episode_item, parent, false);

        if(episodeItem != null){
            TextView nTextView = (TextView) convertView.findViewById(R.id.episode_number);
            nTextView.setText(String.valueOf(episodeItem.getNumber()));
            nTextView.setTextColor(episodeItem.getColor());

            TextView eTextView = (TextView) convertView.findViewById(R.id.episode_name);
            eTextView.setText(episodeItem.getTitle());
            eTextView.setTextColor(episodeItem.getColor());
        }

        return convertView;
    }
    //endregion

    public void setData(List<Episode> episodeList){
        if (!episodeList.isEmpty())
            notifyDataSetChanged();
    }
}
