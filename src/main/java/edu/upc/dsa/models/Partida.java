package edu.upc.dsa.models;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Partida {
    private String idJuego;
    private String idUser;
    private int puntuacion;
    private int nivel;
    private HashMap<Integer, java.time.LocalDateTime> cambiosNivel;

    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public Partida(String idJuego, String idUser) {
        this.idJuego = idJuego;
        this.idUser = idUser;
        this.puntuacion = 50;
        this.nivel = 0;
        this.cambiosNivel = new HashMap<>();
        cambiosNivel.put(0,java.time.LocalDateTime.now());
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public HashMap<Integer, LocalDateTime> getCambiosNivel() {
        return cambiosNivel;
    }

    public void setCambiosNivel(HashMap<Integer, LocalDateTime> cambiosNivel) {
        this.cambiosNivel = cambiosNivel;
    }

    public int actualizarNivel(int numNiveles, int puntos){
        nivel++;
        if(nivel == numNiveles){
            puntuacion += 100;
            return 0;
        }
        else{
            cambiosNivel.put(nivel, java.time.LocalDateTime.now());
            puntuacion += puntos;
            return nivel;
        }
    }
}
