package com.gabrieloliveira.kiwi_game_list.controller;

import com.gabrieloliveira.kiwi_game_list.dto.GameMinDTO;
import com.gabrieloliveira.kiwi_game_list.service.RawgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameSearchController {

    @Autowired
    private RawgService rawgService;

    // URL: GET http://localhost:8080/games/search?query=
    @GetMapping("/search")
    public List<GameMinDTO> search(@RequestParam String query) {
        return rawgService.buscarJogos(query);
    }
}