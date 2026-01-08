package com.gabrieloliveira.kiwi_game_list.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record GameMinDTO(
        long id,
        String name,
        @JsonAlias("background_image") String backgroundImage,
        double rating
) {}