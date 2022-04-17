package web.vote.core;

public class VoteDTO {
    private int artist;
    private int[] genres;
    private String aboutMe;

    public VoteDTO(int artist, int[] genres, String aboutMe) {
        this.artist = artist;
        this.genres = genres;
        this.aboutMe = aboutMe;
    }

    public int getArtist() {
        return artist;
    }


    public int[] getGenres() {
        return genres;
    }

    public String getAboutMe() {
        return aboutMe;
    }

}
