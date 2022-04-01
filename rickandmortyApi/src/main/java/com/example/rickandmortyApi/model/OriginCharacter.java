package com.example.rickandmortyApi.model;

import java.util.ArrayList;

public class OriginCharacter {
    private String name;
    private String url;
    private String dimension;
    private ArrayList<String> residents;

    public OriginCharacter() {
    }

    public OriginCharacter(String name, String url, String dimension, ArrayList<String> residents) {
        this.name = name;
        this.url = url;
        this.dimension = dimension;
        this.residents = residents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public ArrayList<String> getResidents() {
        return residents;
    }

    public void setResidents(ArrayList<String> residents) {
        this.residents = residents;
    }
}
