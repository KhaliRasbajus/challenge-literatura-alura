package com.alura.literatura.challenge.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alura.literatura.challenge.entites.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {

    @Query("SELECT o FROM Obra o JOIN FETCH o.autor")

    List<Obra> findAllWithAuthors();



    @Query("SELECT o FROM Obra o JOIN FETCH o.autor WHERE o.idioma=?1")
    Optional<List<Obra>> findAllWithLanguages(String idioma);

    @Query("SELECT o FROM Obra o JOIN FETCH o.autor WHERE LOWER(o.titulo) LIKE %?1%")
    Optional<Obra> findByTitulo(String titulo);
}
