package com.example.lenovo.mymoviestage1;

public class MovieList {
    String title;
    String vote_avg;
    String poster;
    String overview;
    String releasedate;

    public MovieList(String title, String vote_avg, String poster, String overview, String releasedate) {
        this.title = title;
        this.vote_avg = vote_avg;
        this.poster = poster;
        this.overview = overview;
        this.releasedate = releasedate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_avg() {
        return vote_avg;
    }

    public void setVote_avg(String vote_avg) {
        this.vote_avg = vote_avg;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }
}
