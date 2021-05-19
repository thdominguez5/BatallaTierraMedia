package Model.Heroes;

public class Humanos extends Heroes{

    public Humanos(String nombre, int vida, int resistencia) {
        super(nombre, vida, resistencia);
    }

    @Override
    public String toString() {
        if (this.getNombre().equals("x")) {
            return "Humano";
        }
        return "Nombre: " + getNombre() + " Vida: " + getVida() + " Armadura: " + getResistencia() + "Raza: Humano.";
    }
}
