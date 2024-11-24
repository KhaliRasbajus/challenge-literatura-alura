package com.alura.literatura.challenge.services;

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
    public Optional<Obra> findObraByTitle(String titulo) {
        
        return Optional.empty();
    }

    @Override
    public List<Obra> findObras() {
       
        return null;
    }

    
}
