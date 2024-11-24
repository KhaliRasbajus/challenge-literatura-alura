package com.alura.literatura.challenge.services;

import java.util.List;
import java.util.Optional;

import com.alura.literatura.challenge.entites.Obra;

public interface IObraService {

    Obra createObra(Obra obra);

    Optional<Obra> findObraByTitle(String titulo);
    List<Obra> findObras();

}