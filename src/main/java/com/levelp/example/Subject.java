package com.levelp.example;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Engineer engineer;

    @Column(unique = true, nullable = false)
    private String cadNum;

    @Column
    private String title;

    @Column(unique = true, nullable = false, length = 500)
    private String address;

    public Subject() {
    }

    public Subject(String cadNum, String address) {
        this.cadNum = cadNum;
        this.address = address;
    }

    public String getCadNum() {
        return cadNum;
    }

    public void setCadNum(String cadNum) {
        this.cadNum = cadNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Engineer getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }
}
