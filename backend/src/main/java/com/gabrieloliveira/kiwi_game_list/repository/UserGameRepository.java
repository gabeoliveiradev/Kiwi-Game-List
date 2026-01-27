package com.gabrieloliveira.kiwi_game_list.repository;

import com.gabrieloliveira.kiwi_game_list.entity.UserGame;
import com.gabrieloliveira.kiwi_game_list.entity.Game;
import com.gabrieloliveira.kiwi_game_list.entity.User;
import com.gabrieloliveira.kiwi_game_list.entity.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {

    List<UserGame> findByUserId(Long userId);

    List<UserGame> findByUser(User user);

    List<UserGame> findByUserIdAndStatus(Long userId, GameStatus status);

    boolean existsByUserAndGame(User user, Game game);
}