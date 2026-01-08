package com.gabrieloliveira.kiwi_game_list.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.gabrieloliveira.kiwi_game_list.dto.GameMinDTO;
import com.gabrieloliveira.kiwi_game_list.dto.RawgResponseDTO;
import java.util.List;

@Service
public class RawgService {

    @Value("${rawg.api.key}")
    private String apiKey;

    @Value("${rawg.api.url}")
    private String apiUrl;

    public List<GameMinDTO> buscarJogos(String termo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/games?key=" + apiKey + "&search=" + termo;

        RawgResponseDTO response = restTemplate.getForObject(url, RawgResponseDTO.class);

        return response != null ? response.results() : List.of();
    }
}