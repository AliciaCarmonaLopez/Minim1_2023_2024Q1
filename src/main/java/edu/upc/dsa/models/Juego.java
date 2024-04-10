package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Juego {
    private String idJuego;
    private String descripcion;
    private int niveles;



    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNiveles() {
        return niveles;
    }

    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }

    public Juego() {}

    public Juego(String idJuego, String descripcion, int niveles) {
        this.idJuego = idJuego;
        this.descripcion = descripcion;
        this.niveles = niveles;
    }

}
