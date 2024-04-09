package edu.upc.dsa;

import edu.upc.dsa.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class PartidasManagerImplTest {
    PartidasManager pm;

    @Before
    public void setUp() throws Exception{
        this.pm = new PartidasManagerImpl();
        pm.addJuego("LOL", "Juego de lucha", 20);
        pm.addJuego("Celda", "Juego de aventuras", 3);
        pm.addJuego("MB", "Juego de pantallas", 3);

        pm.addUser("11111");
        pm.addUser("22222");
        pm.addUser("33333");

        pm.addPartida("11111", "LOL");
        pm.addPartida("22222", "MB");
    }

     @After
     public void tearDown(){this.pm = null;}

    @Test
    public void testIniciarPartida() throws Exception{
        Assert.assertEquals(2, this.pm.getListaPartidas().size());
        pm.addPartida("22222", "LOL");
        Assert.assertEquals(2, this.pm.getListaPartidas().size());
        pm.addPartida("33333", "LOL");
        Assert.assertEquals(3, this.pm.getListaPartidas().size());
    }

    @Test
    public void testSubirNivel() throws Exception{
        Assert.assertEquals(0, this.pm.getListaPartidas().get("22222").getNivel());
        this.pm.subirNivel("22222", 40, java.time.LocalDateTime.now());
        Assert.assertEquals(90, pm.getListaPartidas().get("22222").getPuntuacion());
        Assert.assertEquals(1, pm.getListaPartidas().get("22222").getNivel());
        this.pm.subirNivel("22222", 20, java.time.LocalDateTime.now());
        this.pm.subirNivel("22222", 40, java.time.LocalDateTime.now());
        Assert.assertEquals(1, pm.getListaUsers().get("22222").getListaPartidas().size());
        Assert.assertEquals(2, pm.getListaPartidas().size());
        Assert.assertEquals(210, pm.getListaUsers().get("22222").getListaPartidas().get(0).getPuntuacion());
    }

    @Test
    public void testUsersByJuego() throws Exception{
        pm.addPartida("33333", "LOL");
        this.pm.subirNivel("33333", 20, java.time.LocalDateTime.now());
        List<User> u = pm.usersByJuego("LOL");
        Assert.assertEquals(u.get(0).getIdUser(), "33333");
    }

    @Test
    public void testFinalizarPartida() throws Exception{
        Assert.assertEquals(2, this.pm.getListaPartidas().size());
        pm.finalizarPartida("22222");
        Assert.assertEquals(1, this.pm.getListaPartidas().size());
        Assert.assertEquals(1, pm.getListaUsers().get("22222").getListaPartidas().size());
    }
}
