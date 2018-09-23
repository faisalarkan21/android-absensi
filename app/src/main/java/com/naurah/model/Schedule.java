package com.naurah.model;

public class Schedule {
    private String title, thumbnailUrl;
    private String year;
    private String dosen;
    private String placeAndTime;
    private String idMhs;
    private String idJadwal;
    private String nip;
    private String pertemuan;
    private String pertemuanDosen;
    private String pertemuanMhs;
    private String time;
    private Boolean isVerified;
    private String idLog;

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


    public String getIdMhs() {
        return idMhs;
    }

    public void setIdMhs(String idMhs) {
        this.idMhs = idMhs;
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

    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(String pertemuan) {
        this.pertemuan = pertemuan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getIdLog() {
        return idLog;
    }

    public void setIdLog(String idLog) {
        this.idLog = idLog;
    }

    public String getPertemuanDosen() {
        return pertemuanDosen;
    }

    public void setPertemuanDosen(String pertemuanDosen) {
        this.pertemuanDosen = pertemuanDosen;
    }

    public String getPertemuanMhs() {
        return pertemuanMhs;
    }

    public void setPertemuanMhs(String pertemuanMhs) {
        this.pertemuanMhs = pertemuanMhs;
    }
}