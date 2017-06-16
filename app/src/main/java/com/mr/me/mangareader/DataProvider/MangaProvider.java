package com.mr.me.mangareader.DataProvider;

import com.mr.me.mangareader.Entities.Episode;
import com.mr.me.mangareader.Entities.Manga;
import com.mr.me.mangareader.Entities.MangaType;
import com.mr.me.mangareader.R;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MangaProvider {
    //region Fields
    private List<Manga> mangaList;
    private static final MangaProvider ourInstance = new MangaProvider();
    private Manga lastRead;
    //endregion

    public static MangaProvider getInstance() {
        return ourInstance;
    }

    private MangaProvider() {
        setMangaList(CreateMangaList());
    }

    //region Getter / Setter
    public List<Manga> getMangaList() {
        return mangaList;
    }

    private void setMangaList(List<Manga> mangaList) {
        this.mangaList = mangaList;
    }

    public Manga getLastRead() {
        return (lastRead != null) ? lastRead : mangaList.get(0);
    }

    public void setLastRead(Manga lastRead) {
        this.lastRead = lastRead;
    }

    public Manga getMangaByPosition(int position){
        return (mangaList.size() > position) ? mangaList.get(position) : null;
    }

    public int getPositionByName(String name){
        for (int i = 0; i < mangaList.size(); i++) {
            Manga item = mangaList.get(i);
            if (item.getTitle().compareTo(name) == 0)
                return i;
        }
        return -1;
    }
    //endregion

    //region Dummy Data
    private List<Manga> CreateMangaList(){
        List<Manga> list = new ArrayList<Manga>();

        //region Gintama
        List<MangaType> gTags =  new ArrayList<MangaType>();
        gTags.add(MangaType.Action);
        gTags.add(MangaType.Shounen);
        gTags.add(MangaType.Supernatural);
        gTags.add(MangaType.Fantasy);

        List<Episode> gEpisodes = new ArrayList<>();
        gEpisodes.add(new Episode("There is no evil in those with a Natural Perm", 1, true));
        gEpisodes.add(new Episode("Owners should take care of their Pets until the End", 2, false));

        String gDesc = "Sakata Gintoki is a samurai living in an era when samurai are no longer needed." +
                "To add to his troubles, oppressive aliens have moved in to invade." +
                "Gintoki lives with Kagura and Shinpachi, taking on odd jobs to make the world a better place... and to pay their rent.";

        list.add(new Manga("Gintama", gDesc ,gTags, gEpisodes, R.drawable.gintama));
        //endregion
        //region FMA
        List<MangaType> fmaTags =  new ArrayList<MangaType>();
        fmaTags.add(MangaType.Action);
        fmaTags.add(MangaType.Shounen);
        fmaTags.add(MangaType.Drama);
        fmaTags.add(MangaType.Comedy);

        List<Episode> fmaEpisodes = new ArrayList<>();
        fmaEpisodes.add(new Episode("The Two Alchemists", 1, true));
        fmaEpisodes.add(new Episode("The Price of a Life", 2, false));
        fmaEpisodes.add(new Episode("The Coal Mine Town", 3, false));

        String fmaDesc = "In an alchemical ritual gone wrong, Edward Elric lost his arm and his leg," +
                " and his brother Alphonse became nothing but a soul in a suit of armor. " +
                "Equipped with mechanical \"auto-mail\" limbs, Edward becomes a state alchemist, " +
                "seeking the one thing that can restore his brother and himself... the legendary Philosopher's Stone.";
        list.add(new Manga("Full Metal Alchemist", fmaDesc, fmaTags,fmaEpisodes, R.drawable.full_metal_alchemist));
        //endregion
        //region One Piece
        List<MangaType> opTags =  new ArrayList<MangaType>();
        opTags.add(MangaType.Action);
        opTags.add(MangaType.Shounen);
        opTags.add(MangaType.Adventure);
        opTags.add(MangaType.Comedy);

        List<Episode> opEpisodes = new ArrayList<>();
        opEpisodes.add(new Episode("Romance Dawn", 1, true));
        opEpisodes.add(new Episode("They Call Him Strawhat Luffy", 2, false));
        opEpisodes.add(new Episode("Pirate Hunter Zoro Enters", 3, false));
        opEpisodes.add(new Episode("Marine Lieutenant Axe Hand Morgan", 4, false));

        String opDesc = "Gol D. Roger was known as the Pirate King, the strongest and most infamous being to have sailed the Grand Line. " +
                "The capture and death of Roger by the World Government brought a change throughout the world. " +
                "His last words before his death revealed the location of the greatest treasure in the world, One Piece. " +
                "It was this revelation that brought about the Grand Age of Pirates, men who dreamed of finding One Piece (which promises an unlimited amount of riches and fame), " +
                "and quite possibly the most coveted of titles for the person who found it, the title of the Pirate King.";
        list.add(new Manga("One Piece", opDesc, opTags, opEpisodes, R.drawable.one_piece));
        //endregion
        //region Naruto
        List<MangaType> nTags =  new ArrayList<MangaType>();
        nTags.add(MangaType.Action);
        nTags.add(MangaType.Shounen);
        nTags.add(MangaType.Supernatural);
        nTags.add(MangaType.Adventure);

        List<Episode> nEpisodes = new ArrayList<>();
        nEpisodes.add(new Episode("Uzumaki Naruto", 1, true));
        nEpisodes.add(new Episode("Ko No Ha Maru!!", 2, true));
        nEpisodes.add(new Episode("Uchiha Sasuke!!", 3, true));
        nEpisodes.add(new Episode("Hatake KAKASHI!!", 4, true));
        nEpisodes.add(new Episode("Carelessness Is Your Worst Enemy", 5));
        nEpisodes.add(new Episode("Not Sasuke Kun!!", 6));
        nEpisodes.add(new Episode("Kakashi's Conclusion", 7));
        nEpisodes.add(new Episode("That's Why You're Failures!!", 8));
        nEpisodes.add(new Episode("The Worst Possible Client", 9));

        String nDesc = "Twelve years ago, the powerful Nine-Tailed Demon Fox attacked the ninja village of Konohagakure the village hidden in the leaves. " +
                "The demon was defeated and sealed into the infant Naruto Uzumaki, by the Fourth Hokage who sacrificed his life to protect the village. ";
        list.add(new Manga("Naruto", nDesc, nTags, nEpisodes, R.drawable.naruto));
        //endregion

        return list;
    }
    //endregion
}
