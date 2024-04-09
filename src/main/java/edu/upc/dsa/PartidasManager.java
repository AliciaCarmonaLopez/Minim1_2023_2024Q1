package edu.upc.dsa;

import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public interface PartidasManager {

    public void addPartida(String idUser, String idJuego);
    public void addJuego(String idJuego, String descripcion, int numNiveles);
    public void addUser(String idUser);
    public int nivelActual(String idUser);
    public int puntuacionActual(String idUser);
    public int subirNivel(String idUser, int puntos, java.time.LocalDateTime fecha);
    public void finalizarPartida(String idUser);
    public List<User> usersByJuego(String idJuego);
    public List<Partida> partidasByUser(String idUser);

    public HashMap<String, Partida> getListaPartidas();

    public HashMap<String,User> getListaUsers();

    public int getNumJuegos();
}
