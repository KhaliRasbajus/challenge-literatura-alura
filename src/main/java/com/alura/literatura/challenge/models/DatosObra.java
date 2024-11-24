package com.alura.literatura.challenge.models;

import java.util.List;
import com.alura.literatura.challenge.entites.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosObra(
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") List<Autor> autor,
    @JsonAlias("languages") List<String>  idioma,
    @JsonAlias("download_count") Integer numeroDescargas
) {
    
}
