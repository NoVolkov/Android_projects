package ru.novlk.post_its.list_item;

import java.util.List;

public class PostIt {
    private Integer id;
    private String title;
    private String text;
    private List<Tag> tags;
    private String dateDeadline;
    private String createdDate;
    public PostIt(Integer id,String title,String text, List<Tag> tags, String dateDeadline, String createdDate){
        this.id=id;
        this.title=title;
        this.text=text;
        this.tags=tags;
        this.dateDeadline=dateDeadline;
        this.createdDate=createdDate;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getDateDeadline() {
        return dateDeadline;
    }

    public void setDateDeadline(String dateDeadline) {
        this.dateDeadline = dateDeadline;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
