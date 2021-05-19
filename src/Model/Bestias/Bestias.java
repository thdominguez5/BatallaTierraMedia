package Model.Bestias;


import Model.Personajes;

public class Bestias extends Personajes {


    public Bestias(String nombre, int vida, int resistencia) {
        super(nombre, vida, resistencia);
        super.setBando(Bando.BESTIAS);
    }



    @Override
    public int atacar(Personajes personaje) {
        return (int) (Math.random()*90 + 1);
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + " Vida: " + getVida() + " Armadura: " + getResistencia();
    }
}
