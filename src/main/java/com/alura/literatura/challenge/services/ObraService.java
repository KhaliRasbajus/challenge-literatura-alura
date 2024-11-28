package com.alura.literatura.challenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literatura.challenge.entites.Obra;
import com.alura.literatura.challenge.repositories.ObraRepository;

@Service
public class ObraService implements IObraService {

    


    @Autowired
    private ObraRepository obraRepository;


    @Override
    public Obra createObra(Obra obra) {
        return obraRepository.save(obra);
    }

    @Override
    public List<Obra> findObraByTitle(String titulo) {


        Optional<List<Obra>> obras = obraRepository.findAllWithLanguages(titulo);
        
        return obras.orElse(new ArrayList<>());

    }

    @Override
    public List<Obra> findObras() {

        return (List<Obra>) obraRepository.findAllWithAuthors();
    }

    @Override
    public Optional<Obra> findByTitulo(String titulo) {
        Optional<Obra> obra = obraRepository.findByTitulo(titulo);
        
        return obra;
    }
    
    

    
}
