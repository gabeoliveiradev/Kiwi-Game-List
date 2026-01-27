package com.gabrieloliveira.kiwi_game_list.repository;

import com.gabrieloliveira.kiwi_game_list.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}