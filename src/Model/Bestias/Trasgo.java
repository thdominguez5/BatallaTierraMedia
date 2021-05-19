package Model.Bestias;


import Model.Personajes;

public class Trasgo extends Bestias{

    public Trasgo(String nombre, int vida, int resistencia) {
        super(nombre, vida, resistencia);
    }


    @Override
    public int atacar(Personajes personaje) {

        return (int) (Math.random() * 90) +1;
    }

    @Override
    public String toString() {
        if (this.getNombre().equals("x")) {
            return "Trasgo";
        }
        return "Nombre: " + getNombre() + " Vida: " + getVida() + " Armadura: " + getResistencia() + " Raza: Trasgo.";
    }
}
