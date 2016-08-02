package com.example.ayushimathur.tabsandlistview;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class Artist implements Comparable<Artist> {
    private String name;
    private String location;
    private String zoneColor;
    private String starttime;

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getZoneColor() {
        return zoneColor;
    }

    public void setZoneColor(String zoneColor) {
        this.zoneColor = zoneColor;
    }

    private String song;
    private String image;
    private String duration;

    public Artist(){}
    public Artist(String name, String image, String starttime, String song,String location, String zoneColor, String duration) {
        this.name = name;
        this.location = location;
        this.starttime = starttime;
        this.song = song;
        this.zoneColor = zoneColor;
        this.image = image;
        this.duration  = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    @Override
    public int compareTo(Artist a) {

        String s1 = this.getStarttime();
        String s2 = a.getStarttime();

        DateFormat f = new SimpleDateFormat("hh:mm");
        try {
            return f.parse(s1).compareTo(f.parse(s2));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }


}