package com.gabrieloliveira.kiwi_game_list.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RawgService {

    @Value("${rawg.api.key}")
    private String apiKey;

    @Value("${rawg.api.url}")
    private String apiUrl;

    // Método para testar se funcionou
    public String buscarJogos(String termoDeBusca) {
        // A RAWG exige que você mande a chave como parametro "?key=..." na URL
        String urlCompleta = apiUrl + "/games?key=" + apiKey + "&search=" + termoDeBusca;

        // Vamos imprimir no console só pra você ver a URL sendo montada (depois remova por segurança)
        System.out.println("Chamando a URL: " + urlCompleta);

        // O RestTemplate é quem faz a chamada de verdade pra internet
        RestTemplate restTemplate = new RestTemplate();
        String resultado = restTemplate.getForObject(urlCompleta, String.class);

        return resultado;
    }
}