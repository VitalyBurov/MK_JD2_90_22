package web.vote.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenresService {

    private static GenresService genresService;

    private GenresService() {
    }

    public static GenresService getInstance() {
        if (genresService == null) {
            genresService = new GenresService();
        }
        return genresService;
    }

    private List<String> genres = new ArrayList<>(Arrays.asList("Rock", "Rap", "Jazz", "Pop", "Techno",
            "R&B", "Classical", "Lyrics", "Electronic", "Disco"));

    public List<String> getGenres() {
        return this.genres;
    }

    public void add(String genre) {
        genres.add(genre);
    }

    public boolean isExist(int index) {
        int size = genres.size();
        return index >= 0 && index < size;
    }
}

