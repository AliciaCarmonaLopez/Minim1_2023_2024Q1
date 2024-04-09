package edu.upc.dsa;

import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.User;

import java.time.LocalDateTime;
import java.util.*;

import org.apache.log4j.Logger;

public class PartidasManagerImpl implements PartidasManager {
    final static Logger logger = Logger.getLogger(PartidasManagerImpl.class);
    private HashMap<String, Partida> listaPartidas;
    private List<Juego> listaJuegos;
    private HashMap<String, User> listaUsers;
    public PartidasManagerImpl(){
        listaPartidas = new HashMap<>();
        listaJuegos = new ArrayList<>();
        listaUsers = new HashMap<>();
    }
    private static PartidasManager instance;
    public static PartidasManager getInstance() {
        if (instance==null) instance = new PartidasManagerImpl();
        return instance;
    }

    @Override
    public void addPartida(String idUser, String idJuego) {
        logger.info("Nueva partida de jugador: " + idUser + " en el Juego: " + idJuego);
        boolean encontrado = false;
        for(String s: listaPartidas.keySet()){
            if(s == idUser){
                encontrado = true;
                logger.warn("No se ha podido iniciar la partida ya que el jugador " + idUser + " ya tiene una en marcha.");
            }
        }
        if(!encontrado){
            Partida p = new Partida(idJuego, idUser);
            listaPartidas.put(idUser, p);
            logger.info("Nueva partida añadida");
        }
    }

    @Override
    public void addJuego(String idJuego, String descripcion, int numNiveles) {
        logger.info("Nuevo juego con id: " + idJuego + " Descripción: " + descripcion + " Número de niveles: " + numNiveles);
        Juego j = new Juego(idJuego, descripcion, numNiveles);
        listaJuegos.add(j);
        logger.info("Nuevo Juego añadido");
    }

    @Override
    public void addUser(String idUser) {
        logger.info("Nueva user con id: " + idUser);
        User u = new User(idUser);
        listaUsers.put(idUser, u);
        logger.info("Nuevo User añadido");
    }

    @Override
    public int nivelActual(String idUser) {
        logger.info("Consultar el nivel actual del jugador: " + idUser);
        try{listaPartidas.get(idUser);}
        catch(NullPointerException exp) {
            logger.warn("El usuario no existe o no tiene ninguna partida en marcha");
            return -1;
        }
        logger.info("Nivel actual = " + listaPartidas.get(idUser).getNivel());
        return listaPartidas.get(idUser).getNivel();
    }

    @Override
    public int puntuacionActual(String idUser) {
        logger.info("Consultar la puntuación actual del jugador: " + idUser);
        try{listaPartidas.get(idUser);}
        catch(NullPointerException exp) {
            logger.warn("El usuario no existe o no tiene ninguna partida en marcha");
            return -1;
        }
        logger.info("Puntuación actual = " + listaPartidas.get(idUser).getPuntuacion());
        return listaPartidas.get(idUser).getPuntuacion();
    }

    @Override
    public int subirNivel(String idUser, int puntos, LocalDateTime fecha) {
        logger.info("Subir de nivel a: " + idUser + " con " + puntos + " puntos en el dia " +  fecha);
        try{listaPartidas.get(idUser);}
        catch(NullPointerException exp) {
            logger.warn("El usuario no existe o no tiene ninguna partida en marcha");
            return -2;
        }

        for(Juego j: listaJuegos){
            if(j.getIdJuego().equals(listaPartidas.get(idUser).getIdJuego())){
                int err = listaPartidas.get(idUser).actualizarNivel(j.getNiveles(), puntos);
                if(err == 0){
                    logger.info("La partida de " +idUser + " ha sido finalizada con " + err + " puntos");
                    listaUsers.get(idUser).addPartida(listaPartidas.get(idUser));
                    return -1;
                }
                logger.info("El jugador " + idUser + " ha subido a nivel " + err);
                return err;
            }
        }
        logger.warn("No existe el juego de la partida indicada");
        return -2;
    }

    @Override
    public void finalizarPartida(String idUser) {
        try{listaPartidas.get(idUser);}
        catch(NullPointerException exp) {
            logger.warn("El usuario no existe o no tiene ninguna partida en marcha");
        }
        listaUsers.get(idUser).addPartida(listaPartidas.get(idUser));
        listaPartidas.remove(idUser);
        logger.info("La partida de " + idUser +" ha sido finalizada");
    }

    @Override
    public List<User> usersByJuego(String idJuego) {
        List<Partida> lp = new ArrayList<>();
        List<User> lu = new ArrayList<>();
        for(String s:listaUsers.keySet())
        {
            for(Partida p: listaUsers.get(s).getListaPartidas()){
                if(Objects.equals(p.getIdJuego(), idJuego)){
                    lp.add(p);
                }
            }
        }
        for(String s: listaPartidas.keySet()){
            if(Objects.equals(listaPartidas.get(s).getIdJuego(), idJuego))
                lp.add(listaPartidas.get(s));
        }
        Comparator<Partida> pointscomparator = Comparator.comparingDouble(Partida::getPuntuacion);
        lp.sort(pointscomparator.reversed());
        for(Partida p : lp){
            lu.add(listaUsers.get(p.getIdUser()));
            logger.info(listaUsers.get(p.getIdUser()).getIdUser());
        }
        return lu;
    }

    @Override
    public List<Partida> partidasByUser(String idUser) {
        try{listaUsers.get(idUser);}
        catch(NullPointerException exp) {
            logger.warn("El usuario no existe o no tiene ninguna partida en marcha");
        }
        return listaUsers.get(idUser).getListaPartidas();
    }

    @Override
    public HashMap<String, Partida> getListaPartidas() {
        return listaPartidas;
    }

    @Override
    public HashMap<String, User> getListaUsers() {
        return listaUsers;
    }

    @Override
    public int getNumJuegos() {
        return listaJuegos.size();
    }
}
