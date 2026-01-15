package com.gabrieloliveira.kiwi_game_list.entity;

import com.gabrieloliveira.kiwi_game_list.entity.enums.GameStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_user_games")
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_api_id")
    private Long gameApiId;

    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private Double personalRating;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserGame() {
    }

    public UserGame(Long id, Long gameApiId, String title, String imageUrl, GameStatus status, Double personalRating, String notes, User user) {
        this.id = id;
        this.gameApiId = gameApiId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.status = status;
        this.personalRating = personalRating;
        this.notes = notes;
        this.user = user;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getGameApiId() { return gameApiId; }
    public void setGameApiId(Long gameApiId) { this.gameApiId = gameApiId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public GameStatus getStatus() { return status; }
    public void setStatus(GameStatus status) { this.status = status; }

    public Double getPersonalRating() { return personalRating; }
    public void setPersonalRating(Double personalRating) { this.personalRating = personalRating; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}