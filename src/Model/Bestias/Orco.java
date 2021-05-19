package Model.Bestias;


import Model.Personajes;

public class Orco extends Bestias {
    public Orco(String nombre, int vida, int resistencia) {
        super(nombre, vida, resistencia);
    }

    @Override
    public int atacar(Personajes personaje) {

        int ataqueBase = (int) (Math.random() * 90) +1;


        return ataqueBase;
    }

    @Override
    public String toString() {
        if (this.getNombre().equals("x")) {
            return "Orco";
        }
        return "Nombre: " + getNombre() + " Vida: " + getVida() + " Armadura: " + getResistencia() + "Raza: Orco.";
    }
}
