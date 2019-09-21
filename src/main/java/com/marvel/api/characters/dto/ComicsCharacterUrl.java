package com.marvel.api.characters.dto;

public class ComicsCharacterUrl {

    private String type;
    private String url;

    public ComicsCharacterUrl() {
    }

    public ComicsCharacterUrl(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
