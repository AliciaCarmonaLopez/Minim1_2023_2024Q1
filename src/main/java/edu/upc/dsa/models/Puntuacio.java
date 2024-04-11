package edu.upc.dsa.models;

public class Puntuacio {
    String id;
    int p;

    public Puntuacio() {}
    public Puntuacio(String id, int p) {
        this.id = id;
        this.p = p;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }
}
