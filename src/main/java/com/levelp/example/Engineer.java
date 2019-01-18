package com.levelp.example;

import javax.persistence.*;
import java.util.List;

@Entity
public class Engineer {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String login;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Subject> subjects;

    public Engineer() {
    }

    public Engineer(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
