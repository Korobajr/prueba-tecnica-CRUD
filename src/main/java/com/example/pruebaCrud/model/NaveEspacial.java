package com.example.pruebaCrud.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NaveEspacial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String serie;
    private String pelicula;

    public NaveEspacial() {
    }

    public NaveEspacial(Long id, String nombre, String serie, String pelicula) {
        this.id = id;
        this.nombre = nombre;
        this.serie = serie;
        this.pelicula = pelicula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "NaveEspacial{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", serie='" + serie + '\'' +
                ", pelicula='" + pelicula + '\'' +
                '}';
    }
}
