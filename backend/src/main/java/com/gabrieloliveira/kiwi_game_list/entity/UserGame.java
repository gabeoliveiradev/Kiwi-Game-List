package com.gabrieloliveira.kiwi_game_list.entity;

import com.gabrieloliveira.kiwi_game_list.entity.enums.GameStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "tb_user_games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_api_id")
    private Long gameApiId;

    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING) // Salva no banco como texto ("JOGANDO") em vez de número (1)
    private GameStatus status;

    private Double personalRating;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}