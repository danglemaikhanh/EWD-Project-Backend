package com.dlmk.cheflist_backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String imgURL;

    public AppUser() {}

    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public AppUser(String name, String email, String password, String imgURL) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.imgURL = imgURL;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgURL() {
        return imgURL;
    }
    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id) &&
                Objects.equals(name, appUser.name) &&
                Objects.equals(email, appUser.email) &&
                Objects.equals(password, appUser.password) &&
                Objects.equals(imgURL, appUser.imgURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, imgURL);
    }
}
