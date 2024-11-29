package com.alura.literatura.challenge.main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.literatura.challenge.api.ConsultarApi;
import com.alura.literatura.challenge.entites.Autor;
import com.alura.literatura.challenge.entites.Obra;
import com.alura.literatura.challenge.enums.LANGUAGES;
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

                    Optional<Obra> obraExiste = obraService.findByTitulo(titulo);

                    if (obraExiste.isPresent()) {
                        System.out.println("\nNo se puede guardar la obra " + "'" + titulo + "'" + " otra vez");
                        break;
                    }
                    DatosObra datos = geDatosAutor(titulo);

                    if (datos == null) {
                        System.out.println("\nNo se encontro el libro");
                    }else {
                        agregarObra(datos);
                    }
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
                    autoresPorAño.forEach(System.out::println);
                    break;
                case 5:
                    List<Obra> librosPorIdiomas = obrasPorIdioma();
                    librosPorIdiomas.forEach(System.out::println);
                    break;
                default:
                    System.out.println("La opcion " + opcion + " no existe");
                    break;
            }
        }
    }

    private DatosObra geDatosAutor(String nombreObra) {
        var json = api.consultar(nombreObra);
        if (json == null) {
            return null;
        }
        DatosObra datos = convertir.obtenerDatos(json, DatosObra.class);
        return datos;
    }

    private List<Autor> autoresVivosPorAño() {

        System.out.println("Ingrese el año que deseas buscar: ");
        Integer año = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autores = autorService.findAutorsByYear(año);
        if (autores.size() > 0) {
            System.out.println("\nAutores vivos en el año: " + año + "\n");
        } else {
            System.out.println("\nNo hay Autores vivos en el año: " + año + " \n");
        }
        return autores;
    }
    

    private List<Obra> obrasPorIdioma() {

        String idioma = "";
        System.out.println("Seleccione el idioma que deseas buscar");
        System.out.println("1- Español");
        System.out.println("2- Ingles");
        System.out.println("3- Frances");
        System.out.println("4- Portuges");
        
        Integer opcion = teclado.nextInt();
        teclado.nextLine();
        switch (opcion) {
            case 1:
                idioma = LANGUAGES.ES.toString().toLowerCase();
                break;
            case 2: 
                idioma = LANGUAGES.EN.toString().toLowerCase();
                break;
            case 3:
                idioma = LANGUAGES.FR.toString().toLowerCase();
                break;
            case 4:
                idioma = LANGUAGES.PT.toString().toLowerCase();
                break;
            default:
                System.out.println("La opcion: " + opcion + "no existe ");
                break;
        }

        List<Obra> obras = obraService.findObraByTitle(idioma);
         if (obras.size() > 0) {
            System.out.println("Libros en el idioma: " + idioma+"\n");
        } else {
            System.out.println("No hay Libros en el idioma: " + idioma+" \n" );
        }
        return obras;
    }


    private void agregarObra(DatosObra  datos) {
        

        Autor autor;

        System.out.println(datos);
        
        Optional<Autor> autorExiste = autorService.findAutorsByName(datos.autor());

        
        if (autorExiste.isPresent()) {
            autor = autorExiste.get();
        } else {
            autor = datos.autor().stream()
            .findFirst()
            .map(a -> {
                Autor nuevoAutor = new Autor();
                nuevoAutor.setNombreCompleto(a.getNombreCompleto());
                nuevoAutor.setAñoNacido(a.getAñoNacido());
                nuevoAutor.setAñoFallecido(a.getAñoFallecido());
                autorService.createAutor(nuevoAutor);
                return nuevoAutor;
            }).orElse(null);
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
    }
}
