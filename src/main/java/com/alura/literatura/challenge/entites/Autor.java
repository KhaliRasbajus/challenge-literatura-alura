package com.alura.literatura.challenge.entites;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
 @Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nombreCompleto;

    @Column(name = "birth_year")
    private Integer añoNacido;

    @Column(name = "death_year")
    private Integer añoFallecido;


    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Obra> obras;

    public Autor() {
        obras = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @JsonProperty("birth_year")
    public Integer getAñoNacido() {
        return añoNacido;
    }

    public void setAñoNacido(Integer añoNacido) {
        this.añoNacido = añoNacido;
    }


    @JsonProperty("death_year")
    public Integer getAñoFallecido() {
        return añoFallecido;
    }

    public void setAñoFallecido(Integer añoFallecido) {
        this.añoFallecido = añoFallecido;
    }

    public Set<Obra> getObras() {
        return obras;
    }

    public void setObras(Set<Obra> obras) {
        this.obras = obras;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("\nAutor: ");
        string.append(nombreCompleto);
        string.append("\nFecha de nacimiento: ");
        string.append(añoNacido);
        string.append("\nFecha de fallecimiento: ");
        string.append(añoFallecido);
        if (obras != null && !obras.isEmpty()) {
            string.append("\nLibros: [ ");
            AtomicInteger contador = new AtomicInteger(0);
            int sizeObra = obras.size();
            obras.forEach(o -> {
                string.append(o.getTitulo());
                if (contador.incrementAndGet() < sizeObra) {
                    string.append(", ");
                }
            });
            string.append(" ]");
        } else {
            string.append("\nLibros: [ No hay libros registrados ]\n");
        }
       
        return string.toString();
    }
    
}


