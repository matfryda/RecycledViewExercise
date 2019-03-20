package com.example.recycledviewexercise;

class ModelClass {
    private int imageResource;
    private String title;
    private String body;

    ModelClass(int imageResource, String title, String body) {
        this.imageResource = imageResource;
        this.title = title;
        this.body = body;
    }

    int getImageResource() {
        return imageResource;
    }

    String getTitle() {
        return title;
    }

    String getBody() {
        return body;
    }
}
