package Model.Heroes;


import Model.Bestias.Orco;
import Model.Personajes;

public class Elfo extends Heroes {

    public Elfo(String nombre, int vida, int resistencia) {
        super(nombre, vida, resistencia);
    }

    @Override
    public int atacar(Personajes personaje) {

            int ataqueBase = (int) (Math.random() * 100) +1;

            if (personaje instanceof Orco) {
                ataqueBase +=10;
            }

        return ataqueBase;
    }

    @Override
    public String toString() {
        if (this.getNombre().equals("x")) {
            return "Elfo";
        }
        return "Nombre: " + getNombre() + " Vida: " + getVida() + " Armadura: " + getResistencia() + "Raza: Elfo.";
    }
}
