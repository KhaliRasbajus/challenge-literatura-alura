package com.alura.literatura.challenge.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alura.literatura.challenge.entites.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("select a from Autor a join fetch a.obras")
    List<Autor> findAllAuthorsWithObra();


    @Query("select a from Autor a join fetch a.obras where a.añoNacido=?1")
     Optional<List<Autor>> findAllByBirthYear(Integer year);

}
