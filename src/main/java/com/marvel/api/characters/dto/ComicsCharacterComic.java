package com.marvel.api.characters.dto;

public class ComicsCharacterComic {

    private Integer available;

    public ComicsCharacterComic() {
    }

    public ComicsCharacterComic(Integer available) {
        this.available = available;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
