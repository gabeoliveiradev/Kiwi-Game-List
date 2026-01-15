package com.gabrieloliveira.kiwi_game_list.controller;

import com.gabrieloliveira.kiwi_game_list.entity.User;
import com.gabrieloliveira.kiwi_game_list.entity.UserGame;
import com.gabrieloliveira.kiwi_game_list.repository.UserGameRepository;
import com.gabrieloliveira.kiwi_game_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/games")
public class UserGameController {

    @Autowired
    private UserGameRepository userGameRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserGame> listarJogos(@PathVariable Long userId) {
        return userGameRepository.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<UserGame> adicionarJogo(@PathVariable Long userId, @RequestBody UserGame novoJogo) {

        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        novoJogo.setUser(usuario);

        UserGame jogoSalvo = userGameRepository.save(novoJogo);

        return ResponseEntity.status(HttpStatus.CREATED).body(jogoSalvo);
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