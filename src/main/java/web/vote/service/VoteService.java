package web.vote.service;

import web.vote.core.VoteDTO;

import java.util.HashMap;
import java.util.Map;

public class VoteService {

    private static VoteService voteService;

    private ArtistService artistService;
    private GenresService genresService;


    private VoteService() {
        this.artistService = ArtistService.getInstance();
        this.genresService = GenresService.getInstance();
    }


    public static VoteService getInstance() {
        if (voteService == null) {
            voteService = new VoteService();
        }
        return voteService;
    }

    private Map<String, VoteDTO> voteRepository = new HashMap<>();

    public void check(VoteDTO voteDTO) {
        if (voteDTO.getGenres().length < 3 || voteDTO.getGenres().length > 5) {
            throw new IllegalArgumentException("Wrong !!!!");
        }
        if (!this.artistService.isExist(voteDTO.getArtist())) {
            throw new IllegalArgumentException("The Artist dose not exist");
        }
        for (int genre : voteDTO.getGenres()) {
            if (!this.genresService.isExist(genre)) {
                throw new IllegalArgumentException("The Genre dose not exist!");
            }
        }
    }

    public void save(VoteDTO voteDTO) {
        check(voteDTO);
        voteRepository.put(voteDTO.getAboutMe(), voteDTO);
    }
 /*
    public String about(String userName) {
        return voteRepository.get(userName).getAboutMe();
    }

   public Map<String, Integer> getArtistsRating() {

    }

    public Map<String, Integer> getGenresRating() {

    }
*/
}
