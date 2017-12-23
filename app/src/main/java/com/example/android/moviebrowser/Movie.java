package com.example.android.moviebrowser;


public class Movie {
    private int id;
    private int theaterId;
    private String name;
    private String posterUrl;
    private double rating;
    private String buyTicketUrl;

    public Movie(int id, int theaterId, String name, String posterUrl, double rating, String buyTicketUrl) {
        this.id = id;
        this.theaterId = theaterId;
        this.name = name;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.buyTicketUrl = buyTicketUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;

    }

    public int getTheaterId() {
        return theaterId;
    }

    public String getName() {
        return name;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public double getRating() {
        return rating;
    }

    public String getBuyTicketUrl() {
        return buyTicketUrl;
    }
}
