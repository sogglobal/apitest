package com.marvel.api.characters.dto;

import java.util.List;

public class ComicsCharacter {

    private Long id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
    private String thumbnail;
    private ComicsCharacterComic comics;
    private String stories;
    private String events;
    private List<ComicsCharacterUrl> urls;

    public ComicsCharacter() {
    }

    public ComicsCharacter(Long id, String name, String description, String modified, String resourceURI,
                           String thumbnail, ComicsCharacterComic comics, String stories, String events, List<ComicsCharacterUrl> urls) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.modified = modified;
        this.resourceURI = resourceURI;
        this.thumbnail = thumbnail;
        this.comics = comics;
        this.stories = stories;
        this.events = events;
        this.urls = urls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ComicsCharacterComic getComics() {
        return comics;
    }

    public void setComics(ComicsCharacterComic comics) {
        this.comics = comics;
    }

    public String getStories() {
        return stories;
    }

    public void setStories(String stories) {
        this.stories = stories;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public List<ComicsCharacterUrl> getUrls() {
        return urls;
    }

    public void setUrls(List<ComicsCharacterUrl> urls) {
        this.urls = urls;
    }
}
