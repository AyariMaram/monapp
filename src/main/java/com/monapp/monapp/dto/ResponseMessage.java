package com.monapp.monapp.dto;

public class ResponseMessage {
    private String content;
    public String title;

    public ResponseMessage() {
    }

    public ResponseMessage(String title ,String content) {
        this.content = content;
        this.title=title;
    }


    // Getters et setters




    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
