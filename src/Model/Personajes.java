package Model;


import Model.Bestias.Orco;

public abstract class Personajes  {

    public enum Bando {
        HEROES,BESTIAS;
    }

    private String nombre;
    private int vida;
    private int resistencia;
    private Bando bando;

    public Personajes(String nombre, int vida, int resistencia) {
        this.nombre = nombre;
        this.vida = vida;
        this.resistencia = resistencia;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public Bando getBando() {
        return bando;
    }

    public void setBando(Bando bando) {
        this.bando = bando;
    }



    public int atacar(Personajes personajes){
        return 0;
    }

    public int recibirGolpe(int golpe, Personajes personajes) {

        int resistencia = this.resistencia;
        if (personajes instanceof Orco) {
            resistencia -= (resistencia * 10) / 100;
        }

        int golpeRecibido = golpe - resistencia;
        if (golpeRecibido <= 0) {
            return 0;
        }
        int vidaActual = this.getVida();
        this.setVida(vidaActual - golpeRecibido);
        return golpeRecibido;
    }
}


