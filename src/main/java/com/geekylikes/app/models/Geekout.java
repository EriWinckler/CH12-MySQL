package com.geekylikes.app.models;

import javax.persistence.*;

@Entity
public class Geekout {

    @Id
    @GeneratedValue
    private Long id;

    //post creator
    @ManyToOne
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    private Developer developer;

    private String title;
    private String content;

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
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
}
