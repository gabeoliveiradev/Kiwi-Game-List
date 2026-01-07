package com.gabrieloliveira.kiwi_game_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabrieloliveira.kiwi_game_list.service.RawgService;

@RestController
public class TestController {

    // Injetamos o nosso novo Service aqui
    @Autowired
    private RawgService rawgService;

    @GetMapping("/teste-rawg")
    public String testeRawg() {
        // Tenta buscar por "Mario"
        return rawgService.buscarJogos("mario");
    }
}
