package com.gabrieloliveira.kiwi_game_list.controller;

import com.gabrieloliveira.kiwi_game_list.dto.GameMinDTO;
import com.gabrieloliveira.kiwi_game_list.entity.Game;
import com.gabrieloliveira.kiwi_game_list.repository.GameRepository;
import com.gabrieloliveira.kiwi_game_list.entity.User;
import com.gabrieloliveira.kiwi_game_list.entity.UserGame;
import com.gabrieloliveira.kiwi_game_list.repository.UserGameRepository;
import com.gabrieloliveira.kiwi_game_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@RestController
@RequestMapping("/my-games")
public class UserGameController {

    @Autowired
    private UserGameRepository userGameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<UserGame> listarJogos(@AuthenticationPrincipal UserDetails userDetails) {
        User usuario = (User) userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return userGameRepository.findByUser(usuario);
    }

    @PostMapping
    public ResponseEntity<Object> adicionarJogo(@RequestBody GameMinDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        User usuario = (User) userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Game game = gameRepository.findById(data.id()).orElseGet(() -> {
            Game newGame = new Game();
            newGame.setId(data.id());
            newGame.setName(data.name());
            newGame.setBackgroundImage(data.backgroundImage());
            newGame.setRating(data.rating());
            return gameRepository.save(newGame);
        });

        if (userGameRepository.existsByUserAndGame(usuario, game)) {
            return ResponseEntity.badRequest().body("Jogo já está na lista");
        }

        UserGame novoUserGame = new UserGame();
        novoUserGame.setUser(usuario);
        novoUserGame.setGame(game);

        userGameRepository.save(novoUserGame);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<UserGame> atualizarJogo(@PathVariable Long userId, @PathVariable Long gameId, @RequestBody UserGame dadosAtualizados) {

        UserGame jogoExistente = userGameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogo não encontrado"));

        if (!jogoExistente.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse jogo não pertence ao usuário informado!");
        }

        if (dadosAtualizados.getStatus() != null) {
            jogoExistente.setStatus(dadosAtualizados.getStatus());
        }

        if (dadosAtualizados.getPersonalRating() != null) {
            jogoExistente.setPersonalRating(dadosAtualizados.getPersonalRating());
        }

        if (dadosAtualizados.getNotes() != null) {
            jogoExistente.setNotes(dadosAtualizados.getNotes());
        }

        UserGame jogoSalvo = userGameRepository.save(jogoExistente);

        return ResponseEntity.ok(jogoSalvo);
    }
}