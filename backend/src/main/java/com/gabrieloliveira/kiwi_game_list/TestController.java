package com.gabrieloliveira.kiwi_game_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabrieloliveira.kiwi_game_list.service.RawgService;
import com.gabrieloliveira.kiwi_game_list.dto.GameMinDTO;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private RawgService rawgService;

    @GetMapping("/teste-rawg")
    public List<GameMinDTO> testeRawg() {
        return rawgService.buscarJogos("mario");
    }
}