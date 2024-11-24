package com.alura.literatura.challenge.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.alura.literatura.challenge.api.ConsultarApi;
import com.alura.literatura.challenge.models.DatosAutor;
import com.alura.literatura.challenge.models.DatosObra;
import com.alura.literatura.challenge.services.ConvertirDatos;
import com.alura.literatura.challenge.services.IAutorService;
import com.alura.literatura.challenge.services.IObraService;

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

        while (salir != true) {
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
                    System.out.println(datos);
                    break;
                case 2:
                    System.out.println("LibrosRegistrados");
                    break;
                case 3:
                    System.out.println("AutoresRegistrados");
                    break;
                case 4:
                    System.out.println("AutoresVivosAño");
                    break;
                case 5:
                    System.out.println("Libros Por Idioma");
                    break;
                default:
                    System.out.println("La opcion " + opcion + "no existe");
                    break;
            }
        }
        
    }


    private DatosObra geDatosAutor(String nombreObra) {
        var json = api.consultar(nombreObra);
        DatosObra datos = convertir.obtenerDatos(json, DatosObra.class);
        return datos;
    }


}
