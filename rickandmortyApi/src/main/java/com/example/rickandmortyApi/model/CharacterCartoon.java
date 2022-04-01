package com.example.rickandmortyApi.model;


public class CharacterCartoon {

    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private long episodeCount;
    private OriginCharacter originCharacter;

    public CharacterCartoon() {
    }

    public CharacterCartoon(int id, String name, String status, String species, String type, long episodeCount, OriginCharacter originCharacter) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.episodeCount = episodeCount;
        this.originCharacter = originCharacter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(long episodeCount) {
        this.episodeCount = episodeCount;
    }

    public OriginCharacter getOriginCharacter() {
        return originCharacter;
    }

    public void setOriginCharacter(OriginCharacter originCharacter) {
        this.originCharacter = originCharacter;
    }
}
