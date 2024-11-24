package com.alura.literatura.challenge.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

@Component
public class ConsultarApi {



    private String BASE_URL = "https://gutendex.com/books/?search=";



    public String consultar(String titulo) {




        String url = BASE_URL + titulo.replace(" ", "%20");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    

        String json = response.body();

        String primerResultado = "";

        try {
            int resultInicio = json.indexOf("\"results\":")+10;

            int primerLibroInicio = json.indexOf("{", resultInicio);
            int primerLibroFinal = json.indexOf("}", primerLibroInicio) + 1;

            primerResultado = json.substring(primerLibroInicio, primerLibroFinal) + "]}";
            
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el json " + e);
        }


        

        System.out.println(primerResultado);
        return primerResultado;
    }
}
