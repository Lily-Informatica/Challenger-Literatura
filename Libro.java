
package com.literalura.model;

import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String idioma;
    private Integer descargas;

    public Libro(){}

    public Libro(String titulo,String autor,String idioma,Integer descargas){
        this.titulo=titulo;
        this.autor=autor;
        this.idioma=idioma;
        this.descargas=descargas;
    }

    public Long getId(){ return id; }

    public String getTitulo(){ return titulo; }

    public String getAutor(){ return autor; }

    public String getIdioma(){ return idioma; }

    public Integer getDescargas(){ return descargas; }

    @Override
    public String toString(){
        return "Libro: "+titulo+" | Autor: "+autor+" | Idioma: "+idioma+" | Descargas: "+descargas;
    }
}
