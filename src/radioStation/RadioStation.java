package radioStation;

import sourceUtils.SongJSONReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import musicCollection.data.Song;
import parserUtils.SongParser;
import sourceUtils.SongJSONWriter;


public class RadioStation {    

    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
        String fileName = "allSongs.json";
        
        SongJSONReader reader = new SongJSONReader();
        SongParser songParser = new SongParser();
        
        String str = reader.load(fileName);
        
        ArrayList<Song> musicCollection = songParser.parser(str);

        ///////////// PRINT MUSIC COLLECTION //////////////
        for (Song s : musicCollection) {
            System.out.println(s.toString());
        }
        
        ///////////// SEARCHING ///////////// 
        // ArrayList<Song> findingSongs = search(musicCollection, "kicker");     
//        for (Song s: findingSongs) {
//            System.out.println(findingSongs.toString());
//        }       
        
        ////////////// SORTING //////////////
//        sort(musicCollection, "artist");
//        for (Song s : musicCollection) {
//            System.out.println(s.toString());
//        }
        
        ////////////// WRITING TO JSON  //////////////
        SongJSONWriter jsonWriter = new SongJSONWriter(fileName);
        jsonWriter.writerToJSON(musicCollection);     
    }

    public static ArrayList<Song> sort(ArrayList<Song> collection, String sortKeyword) {
        switch (sortKeyword) {
            case "album": {
                Collections.sort(collection, new Comparator<Song>() {

                    @Override
                    public int compare(Song s1, Song s2) {
                        return s1.getAlbum().compareTo(s2.getAlbum());
                    }
                });
            }
            break;

            case "artist": {
                Collections.sort(collection, new Comparator<Song>() {

                    @Override
                    public int compare(Song s1, Song s2) {
                        return s1.getArtist().compareTo(s2.getArtist());
                    }
                });
            }
            break;

            case "title": {
                Collections.sort(collection, new Comparator<Song>() {

                    @Override
                    public int compare(Song s1, Song s2) {
                        return s1.getTitle().compareTo(s2.getTitle());
                    }
                });
            }
            break;
        }

        return collection;
    }

    public static ArrayList<Song> search(ArrayList<Song> collection, String searchKeyword) {

        ArrayList<Song> findingSongs = new ArrayList<>();

        for (Song song : collection) {
            if (song.getAlbum().toLowerCase().contains(searchKeyword.toLowerCase()) || song.getArtist().toLowerCase().contains(searchKeyword.toLowerCase())
                    || song.getTitle().toLowerCase().contains(searchKeyword.toLowerCase())) {
                findingSongs.add(song);
            }
        }
        return findingSongs;
    }
}
