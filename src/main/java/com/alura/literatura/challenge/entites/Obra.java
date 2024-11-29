package com.alura.literatura.challenge.entites;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "obra")
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String titulo;
    @Column(name = "languages")
    private String idioma;
    @Column(name = "download_count")
    private Integer numeroDescargas;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;


    public Obra() {

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    @JsonProperty("languages")
    public String getIdioma() {
        return idioma;
    }


    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }



    @JsonProperty("download_count")
    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }


    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Autor getAutor() {
        return autor;
    }


    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("\n---------- Libro ----------\nTitulo: ");
        string.append(titulo);
        string.append("\nAutor: ");
        string.append(autor.getNombreCompleto());
        string.append("\nIdioma: ");
        string.append(idioma);
        string.append("\nNumero de descargas: ");
        string.append(numeroDescargas);
        string.append("\n--------------------\n");
        return string.toString();
    }
    
}
