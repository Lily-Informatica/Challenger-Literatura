
package com.literalura.principal;

import com.literalura.model.Libro;
import com.literalura.repository.LibroRepository;
import com.literalura.service.ConsumoAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    private final LibroRepository repository;

    public Principal(LibroRepository repository){
        this.repository = repository;
    }

    Scanner teclado = new Scanner(System.in);
    ConsumoAPI api = new ConsumoAPI();

    @Override
    public void run(String... args) throws Exception {

        int opcion = -1;

        while(opcion != 0){

            System.out.println("\n--- LITERALURA ---");
            System.out.println("1 Buscar libro por titulo");
            System.out.println("2 Listar libros");
            System.out.println("3 Listar libros por idioma");
            System.out.println("0 Salir");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch(opcion){

                case 1:
                    buscarLibro();
                    break;

                case 2:
                    listarLibros();
                    break;

                case 3:
                    listarIdioma();
                    break;
            }

        }

    }

    private void buscarLibro(){

        try{

            System.out.println("Escriba el titulo del libro:");
            String titulo = teclado.nextLine();

            String url = "https://gutendex.com/books/?search="+titulo.replace(" ","%20");

            String json = api.obtenerDatos(url);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode datos = mapper.readTree(json);
            JsonNode resultado = datos.get("results").get(0);

            String nombreLibro = resultado.get("title").asText();
            String idioma = resultado.get("languages").get(0).asText();
            int descargas = resultado.get("download_count").asInt();
            String autor = resultado.get("authors").get(0).get("name").asText();

            Libro libro = new Libro(nombreLibro,autor,idioma,descargas);

            repository.save(libro);

            System.out.println("Libro guardado:");
            System.out.println(libro);

        }catch(Exception e){
            System.out.println("Libro no encontrado.");
        }

    }

    private void listarLibros(){

        List<Libro> libros = repository.findAll();

        libros.forEach(System.out::println);

    }

    private void listarIdioma(){

        System.out.println("Ingrese idioma (en, es, fr):");

        String idioma = teclado.nextLine();

        List<Libro> libros = repository.findByIdioma(idioma);

        libros.forEach(System.out::println);

    }

}
