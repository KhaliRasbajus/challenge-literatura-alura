package com.alura.literatura.challenge.services;

import java.util.List;

import com.alura.literatura.challenge.entites.Autor;

public interface IAutorService {

    Autor createAutor(Autor autor);

    List<Autor> findAutors();

    List<Autor> findAutorsByYear(Integer year);
}
