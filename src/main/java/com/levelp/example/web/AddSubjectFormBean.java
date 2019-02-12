package com.levelp.example.web;

import javax.validation.constraints.Pattern;

public class AddSubjectFormBean {
    @Pattern(regexp = "[0-9]+(:[0-9]+)+", message = "Неверный формат кадастрового номера.")
    private String cadNumber;
    private String title;
    private String address;
    private String name;
    private String passportNumber;

    public String getCadNumber() {
        return cadNumber;
    }

    public void setCadNumber(String cadNumber) {
        this.cadNumber = cadNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
