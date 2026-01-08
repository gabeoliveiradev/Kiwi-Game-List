package com.gabrieloliveira.kiwi_game_list.dto;

import java.util.List;

public record RawgResponseDTO(
        List<GameMinDTO> results
) {}