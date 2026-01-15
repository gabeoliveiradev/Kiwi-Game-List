package com.gabrieloliveira.kiwi_game_list.controller;

import com.gabrieloliveira.kiwi_game_list.entity.User;
import com.gabrieloliveira.kiwi_game_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User criarUsuario(@RequestBody User usuario) {
        return userRepository.save(usuario);
    }

    @GetMapping
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    public User atualizarUsuario(@PathVariable Long id, @RequestBody User userAtualizado) {
        return userRepository.findById(id)
                .map(usuarioExistente -> {
                    if (userAtualizado.getUsername() != null) {
                        usuarioExistente.setUsername(userAtualizado.getUsername());
                    }
                    if (userAtualizado.getPassword() != null) {
                        usuarioExistente.setPassword(userAtualizado.getPassword());
                    }
                    return userRepository.save(usuarioExistente);
                })
                .orElse(null);
    }
}