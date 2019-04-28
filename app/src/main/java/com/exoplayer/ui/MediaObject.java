package com.exoplayer.ui;

public class MediaObject {

    public String title;
    public String mediaUrl;
    public String thumbnail;
    public String description;

    public MediaObject(String title, String mediaUrl, String thumbnail, String description) {
        this.title = title;
        this.mediaUrl = mediaUrl;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    public MediaObject() {
    }
}
