package com.alura.literatura.challenge.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.literatura.challenge.api.ConsultarApi;
import com.alura.literatura.challenge.entites.Autor;
import com.alura.literatura.challenge.entites.Obra;
import com.alura.literatura.challenge.models.DatosObra;
import com.alura.literatura.challenge.services.ConvertirDatos;
import com.alura.literatura.challenge.services.IAutorService;
import com.alura.literatura.challenge.services.IObraService;


@Component
public class Menu {
    Scanner teclado = new Scanner(System.in);

    @Autowired
    private ConsultarApi api = new ConsultarApi();
    @Autowired
    private IAutorService autorService;
    @Autowired
    private IObraService obraService;
    private ConvertirDatos convertir = new ConvertirDatos();

    public int opciones() {
        int opcion;
    
        System.out.println("\n------------");
        System.out.println("Elige la opcion a través de su numero: ");
        System.out.println("1- buscar libro por titulo");
        System.out.println("2- listar libros registrados");
        System.out.println("3- listar autores registrados");
        System.out.println("4- listar autores vivos en un determinado año");
        System.out.println("5- listar libros por idioma");
        System.out.println("0- Salir");
        opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;

    }

  public void play() {
        boolean salir = false;

        while (!salir) {
            int opcion = opciones();
            switch (opcion) {
                case 0:
                    System.out.println("Hasta luego");
                    salir = true;
                    break;
                case 1:
                    System.out.println("Ingrese el nombre del libro que deseas buscar: ");
                    String titulo = teclado.nextLine();
                    DatosObra datos = geDatosAutor(titulo);

                   
                    Autor autor = datos.autor().stream()
                        .findFirst() 
                        .map(a -> {
                            Autor nuevoAutor = new Autor();
                            nuevoAutor.setNombreCompleto(a.getNombreCompleto());
                            nuevoAutor.setAñoNacido(a.getAñoNacido());
                            nuevoAutor.setAñoFallecido(a.getAñoFallecido());
                            return nuevoAutor;
                        }).orElse(null);

                    if (autor != null) {
                        autorService.createAutor(autor);
                    }

                    // Crear la obra asociada
                    Obra obra = new Obra();
                    obra.setAutor(autor);
                    obra.setTitulo(datos.titulo());
                    obra.setIdioma(datos.idioma().stream().findFirst().orElse("")); // Asignar primer idioma
                    obra.setNumeroDescargas(datos.numeroDescargas());

                    obraService.createObra(obra);

                    // Imprimir información una sola vez
                    System.out.println("---- Libro ----");
                    System.out.println("Titulo: " + obra.getTitulo());
                    System.out.println("Autor: " + autor.getNombreCompleto());
                    System.out.println("Fecha de nacimiento: " + autor.getAñoNacido());
                    System.out.println("Fecha de fallecimiento: " + autor.getAñoFallecido());
                    System.out.println("Idioma: " + obra.getIdioma());
                    System.out.println("Numero de descargas: " + obra.getNumeroDescargas());
                    break;

                case 2:
                    System.out.println("Libros Registrados");
                    List<Obra> obras = obraService.findObras();
                    obras.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Autores Registrados");
                    List<Autor> autores = autorService.findAutors();
                    autores.forEach(System.out::println);
                    break;
                case 4:
                    List<Autor> autoresPorAño = autoresVivosPorAño();
                    System.out.println("Autores Vivos Año\n");
                    autoresPorAño.forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Libros Por Idioma");
                    break;
                default:
                    System.out.println("La opcion " + opcion + " no existe");
                    break;
            }
        }
    }

    private DatosObra geDatosAutor(String nombreObra) {
        var json = api.consultar(nombreObra);
        DatosObra datos = convertir.obtenerDatos(json, DatosObra.class);
        return datos;
    }

    private List<Autor> autoresVivosPorAño() {

        System.out.println("Ingrese el año que deseas buscar: ");
        Integer año = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autores = autorService.findAutorsByYear(año);
        
        return autores;
    }


}
