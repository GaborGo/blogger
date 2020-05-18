package com.blgr.blogapp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BlogEntry {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Date posted;

    @ManyToOne
    private User user;

    public BlogEntry() {}


    public BlogEntry(String title, String content, Date posted, User user) {
        this.title = title;
        this.content = content;
        this.posted = posted;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Story [title=" + title + "]";
    }



}
