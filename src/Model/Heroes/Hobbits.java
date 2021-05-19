package Model.Heroes;


import Model.Bestias.Trasgo;
import Model.Personajes;

public class Hobbits extends Heroes {
    public Hobbits(String nombre, int vida, int resistencia) {
        super(nombre, vida, resistencia);
    }

    @Override
    public int atacar(Personajes personaje) {

        int ataqueBase = (int) (Math.random() * 100) +1;

        if (personaje instanceof Trasgo) {
            ataqueBase -= 5;
        }

        return ataqueBase;
    }

    @Override
    public String toString() {
        if (this.getNombre().equals("x")) {
            return "Hobbit";
        }
        return "Nombre: " + getNombre() + " Vida: " + getVida() + " Armadura: " + getResistencia() + "Raza: Hobbit.";
    }
}
