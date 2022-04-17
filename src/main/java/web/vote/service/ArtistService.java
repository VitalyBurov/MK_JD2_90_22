package web.vote.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistService {

    private static  ArtistService artistService;

    private ArtistService() {
    }

    public static ArtistService getInstance() {
        if (artistService == null) {
            artistService = new ArtistService();
        }
        return artistService;
    }

    private List<String> artists = new ArrayList<>(
            Arrays.asList("Nirvana", "Metallica", "Rammstein", "LinkinPark"));

    public List<String> getArtists() {
        return this.artists;
    }

    public void add(String artist) {
        artists.add(artist);
    }

    public boolean isExist(int index) {
        int size = artists.size();
        return index >= 0 && index < size;
    }
}
