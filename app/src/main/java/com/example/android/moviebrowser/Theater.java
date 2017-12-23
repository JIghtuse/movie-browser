package com.example.android.moviebrowser;


public class Theater {
    private int id;
    private String name;
    private String geoTag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theater theater = (Theater) o;

        return id == theater.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getGeoTag() {
        return geoTag;
    }

    public Theater(int id, String name, String geoTag) {

        this.id = id;
        this.name = name;
        this.geoTag = geoTag;
    }
}
