package com.alura.literatura.challenge.services;

import java.util.List;

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
      
        return null;
    }

    @Override
    public List<Autor> findAutorsByYear(int year) {
        
        return null;
    }
    
}
