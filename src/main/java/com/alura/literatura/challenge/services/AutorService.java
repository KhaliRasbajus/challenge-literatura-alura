package com.alura.literatura.challenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literatura.challenge.entites.Autor;
import com.alura.literatura.challenge.repositories.AutorRepository;

@Service
public class AutorService implements IAutorService {


    @Autowired
    private AutorRepository autorRepository;

    @Override
    public Autor createAutor(Autor autor) {
        return autorRepository.save(autor);
    }
    
    @Override
    public List<Autor> findAutors() {
        return autorRepository.findAllAuthorsWithObra();
    }

    @Override
    public List<Autor> findAutorsByYear(Integer year) {
        
        Optional<List<Autor>> autoresAño = autorRepository.findAllByBirthYear(year);

        return autoresAño.orElse(new ArrayList<>());
        
    }
    
}
