package Storage;


import Model.Bestias.Bestias;
import Model.Heroes.Heroes;
import Model.Personajes;
import javafx.collections.ObservableList;

public class Storage {

    ObservableList<Heroes> bando1;
    ObservableList<Bestias> bando2;

    public Storage(ObservableList<Heroes> bando1, ObservableList<Bestias> bando2) {
        this.bando1 = bando1;
        this.bando2 = bando2;
    }


    public ObservableList<Heroes> getBando1() {
        return bando1;
    }
    public ObservableList<Bestias> getBando2() {
        return bando2;
    }
}
