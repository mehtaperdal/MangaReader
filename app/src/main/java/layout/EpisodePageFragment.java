package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mr.me.mangareader.DataProvider.MangaProvider;
import com.mr.me.mangareader.Entities.Episode;
import com.mr.me.mangareader.R;

public class EpisodePageFragment extends Fragment {
    private Episode episode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int episodeNo = getArguments().getInt("EpisodeNumber", 1);
        int mangaPosition = getArguments().getInt("MangaPosition", 0);
        episode = MangaProvider.getInstance().getMangaByPosition(mangaPosition).getEpisode(episodeNo);

        ViewGroup view = (ViewGroup) inflater.inflate( R.layout.fragment_episode_page, container, false);
        ImageView image =(ImageView) view.findViewById(R.id.episode_page);
        image.setImageResource(R.drawable.image01);
        //image.setImageMatrix();
        return view;

    }
}
