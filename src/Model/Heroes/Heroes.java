package Model.Heroes;


import Model.Personajes;

public class Heroes extends Personajes {

    public enum Raza {

    }

    public Heroes(String nombre, int vida, int resistencia) {
        super(nombre, vida, resistencia);
        super.setBando(Bando.HEROES);
    }



    @Override
    public int atacar(Personajes personajes) {
        return (int) (Math.random()*100 + 1);
    }

    @Override
    public String toString() {
        return getNombre();
    }
}