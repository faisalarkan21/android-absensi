package com.naurah.model;

import java.util.ArrayList;

public class Schedule {
    private String title, thumbnailUrl;
    private String year;
    private String dosen;
    private String placeAndTime;
    private String idJadwal;
    private String nip;

    public Schedule() {
    }

    public Schedule(String name, String thumbnailUrl, String year, String dosen,
                String placeAndTime) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.year = year;
        this.dosen = dosen;
        this.placeAndTime = placeAndTime;
    }


    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }



    public String getPlaceAndTime() {
        return placeAndTime;
    }

    public void setPlaceAndTime(String placeAndTime) {
        this.placeAndTime = placeAndTime;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}