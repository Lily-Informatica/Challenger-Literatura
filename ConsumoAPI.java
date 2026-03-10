
package com.literalura.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    public String obtenerDatos(String url){

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try{

            HttpResponse<String> response = client
                    .send(request,HttpResponse.BodyHandlers.ofString());

            return response.body();

        }catch(Exception e){
            throw new RuntimeException("Error consultando API");
        }

    }
}
