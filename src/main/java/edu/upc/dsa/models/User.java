package edu.upc.dsa.models;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private String idUser;
    private List<Partida> listaPartidas;

    public User() {
    }

    public User(String idUser) {
        this.idUser = idUser;
        this.listaPartidas = new ArrayList<>();
    }

    public void addPartida(Partida p){
        listaPartidas.add(p);
    }
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public List<Partida> getListaPartidas() {
        return listaPartidas;
    }

    public void setListaPartidas(List<Partida> listaPartidas) {
        this.listaPartidas = listaPartidas;
    }
}