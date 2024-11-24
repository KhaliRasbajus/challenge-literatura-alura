package com.alura.literatura.challenge.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alura.literatura.challenge.entites.Obra;

@Repository
public interface ObraRepository extends CrudRepository<Obra, Long> {

}
