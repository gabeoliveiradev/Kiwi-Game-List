package com.gabrieloliveira.kiwi_game_list.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_game")
public class Game {

    @Id
    private Long id;
    private String name;

    @Column(name = "background_image")
    private String backgroundImage;

    private Double rating;

    public Game() {
    }

    public Game(Long id, String name, String backgroundImage, Double rating) {
        this.id = id;
        this.name = name;
        this.backgroundImage = backgroundImage;
        this.rating = rating;
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

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}