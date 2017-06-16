package com.mr.me.mangareader.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mr.me.mangareader.R;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class OptionsAdapter extends BaseAdapter {
    private Context _context;

    private Integer[] _thumbnails = {
            R.drawable.import_icon,
            R.drawable.library,
            R.drawable.settings
    };

    private String[] _button_names= {
            "Import","Library","Settings"
    };

    public OptionsAdapter(Context c){
        _context = c;
    }

    @Override
    public Object getItem(int position){
        return _thumbnails[position];
    }

    @Override
    public int getCount(){
        return _thumbnails.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertView == null)
        {
            gridView = new View(_context);
            gridView = inflater.inflate(R.layout.option_layout, null);

            TextView textView = (TextView) gridView.findViewById(R.id.button_name);
            textView.setText(_button_names[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.image_button);

            imageView.setImageResource(_thumbnails[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else{
            gridView = convertView;
        }

        return gridView;
    }
}
