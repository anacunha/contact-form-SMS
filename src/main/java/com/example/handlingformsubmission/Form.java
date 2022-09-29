package com.example.handlingformsubmission;

public class Form {

    private String id;
    private String body;
    private String name;
    private String title;

    public Form() {
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
