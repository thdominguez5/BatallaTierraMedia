package controllers;

import Model.Bestias.Bestias;
import Model.Bestias.Orco;
import Model.Bestias.Trasgo;
import Model.Heroes.Elfo;
import Model.Heroes.Heroes;
import Model.Heroes.Hobbits;
import Model.Heroes.Humanos;
import Model.Personajes;
import Storage.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnIntro;

    @FXML
    private Button btnHeroes;

    @FXML
    private Button btnHome;

    @FXML
    private Label lblStatusMini;

    @FXML
    private Button btnBatalla;

    @FXML
    private Button btnBestias;

    @FXML
    private Label lblStatus;

    @FXML
    private Pane pniStatus;

    @FXML
    private GridPane pnHome;

    @FXML
    private GridPane pnIntro;

    @FXML
    private GridPane pnBestias;

    @FXML
    private GridPane pnBatalla;

    @FXML
    private GridPane pnHeroes;

    //Panel bestias
    @FXML private ComboBox<Personajes> cboPersonajesBestias;
    private ObservableList<Bestias> bestias;
    @FXML private ListView<Bestias> lstBestias;
    @FXML private TextField txtNombreBestia;
    @FXML private TextField txtVidaBestia;
    @FXML private TextField txtArmaduraBestia;

    @FXML private Button btnGuardarBestia;
    @FXML private Button btnModificarBestia;
    @FXML private Button btnEliminarBestia;

    private ArrayList<String> erroresBestias;

    //Panel Heroes
    @FXML private ComboBox<Personajes> cboPersonajesHeroes;
    private ObservableList<Heroes> heroes;
    @FXML private ListView<Heroes> lstHeroes;
    @FXML private TextField txtNombreHeroe;
    @FXML private TextField txtVidaHeroe;
    @FXML private TextField txtArmaduraHeroe;

    @FXML private Button btnGuardarHeroe;
    @FXML private Button btnModificarHeroe;
    @FXML private Button btnEliminarHeroe;

    private ArrayList<String> erroresHeroes;

    //Panel Batalla
    @FXML private Button btnLimpiarBatalla;
    @FXML private Button btnEmpezarBatalla;
    private ObservableList<String> batalla;
    @FXML private ListView<String> lstBatalla;

    //Contructor del controlador
    public Controller() {
        erroresBestias = new ArrayList<>();
        erroresHeroes = new ArrayList<>();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //ComboBox Bestias
        ObservableList<Personajes> personajesBestias = FXCollections.observableArrayList();
        personajesBestias.add(new Orco("x",1,1));
        personajesBestias.add(new Trasgo("x", 1,1));
        cboPersonajesBestias.setItems(personajesBestias);

        //Lista Bestias
        bestias = FXCollections.observableArrayList();
        lstBestias.setItems(bestias);

        //Modificar Bestia
        lstBestias.getSelectionModel().selectedItemProperty().addListener((observableValue, x, bestiaNuevo) -> {
            if (bestiaNuevo != null) {
            txtNombreBestia.setText(bestiaNuevo.getNombre());
            txtVidaBestia.setText(String.valueOf(bestiaNuevo.getVida()));
            txtArmaduraBestia.setText(String.valueOf(bestiaNuevo.getResistencia()));

            btnGuardarBestia.setDisable(true);
            btnModificarBestia.setDisable(false);
            btnEliminarBestia.setDisable(false);
            }

        });

        //ComboBox Heroes
        ObservableList<Personajes> personajesHeroes = FXCollections.observableArrayList();
        personajesHeroes.add(new Elfo("x",1,1));
        personajesHeroes.add(new Hobbits("x",1,1));
        personajesHeroes.add(new Humanos("x",1,1));
        cboPersonajesHeroes.setItems(personajesHeroes);

        //Lista Heroes
        heroes = FXCollections.observableArrayList();
        lstHeroes.setItems(heroes);

        //Modificar Heroe
        lstHeroes.getSelectionModel().selectedItemProperty().addListener((observableValue, x, heroeNuevo) -> {
            if (heroeNuevo != null) {
                txtNombreHeroe.setText(heroeNuevo.getNombre());
                txtVidaHeroe.setText(String.valueOf(heroeNuevo.getVida()));
                txtArmaduraHeroe.setText(String.valueOf(heroeNuevo.getResistencia()));

                btnGuardarHeroe.setDisable(true);
                btnModificarHeroe.setDisable(false);
                btnEliminarHeroe.setDisable(false);

            }
        });

        //Lista Batalla
        batalla = FXCollections.observableArrayList();
        lstBatalla.setItems(batalla);
    }

    @FXML
    public void guardarBestia(){
        validarBestia();
        if (erroresBestias.size()>0) {
            StringBuilder cadenaErrores = new StringBuilder();
            for (String erroresBestia : erroresBestias) {
                cadenaErrores.append(erroresBestia).append("\n");
            }
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setHeaderText("Se encontraron los siguientes errores");
            mensaje.setContentText(cadenaErrores.toString());
            mensaje.show();
            return;
        }
        //Se usa else, ya que saltaría una excepción null pointer exception si no seleccionaramos la raza del personaje.
        if (cboPersonajesBestias.getSelectionModel().getSelectedItem() instanceof Orco){
            bestias.add(new Orco(txtNombreBestia.getText(), Integer.parseInt(txtVidaBestia.getText()), Integer.parseInt(txtArmaduraBestia.getText())));
        }
        else {
            bestias.add(new Trasgo(txtNombreBestia.getText(), Integer.parseInt(txtVidaBestia.getText()), Integer.parseInt(txtArmaduraBestia.getText())));
        }
        limpiarPanelBestias();
        btnGuardarBestia.setDisable(false);
        btnModificarBestia.setDisable(true);
        btnEliminarBestia.setDisable(true);
    }

    @FXML
    public void guardarHeroe() {
        validarHeroe();
        if (erroresHeroes.size()>0) {
            StringBuilder cadenaErroresHeroes = new StringBuilder();
            for (String erroresHeroes : erroresHeroes) {
                cadenaErroresHeroes.append(erroresHeroes).append("\n");
            }
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setHeaderText("Se encontraron los siguientes errores");
            mensaje.setContentText(cadenaErroresHeroes.toString());
            mensaje.show();
            return;
        }
            if (cboPersonajesHeroes.getSelectionModel().getSelectedItem() instanceof Elfo) {
                heroes.add(new Elfo(txtNombreHeroe.getText(), Integer.parseInt(txtVidaHeroe.getText()), Integer.parseInt(txtArmaduraHeroe.getText())));
            }
            if (cboPersonajesHeroes.getSelectionModel().getSelectedItem() instanceof Hobbits) {
                heroes.add(new Hobbits(txtNombreHeroe.getText(), Integer.parseInt(txtVidaHeroe.getText()), Integer.parseInt(txtArmaduraHeroe.getText())));
            } else {
                heroes.add(new Humanos(txtNombreHeroe.getText(), Integer.parseInt(txtVidaHeroe.getText()), Integer.parseInt(txtArmaduraHeroe.getText())));
            }
            limpiarPanelHeroes();
            btnGuardarHeroe.setDisable(false);
            btnModificarBestia.setDisable(true);
            btnEliminarBestia.setDisable(true);
        }

    @FXML
    public void modificarBestia() {
        if (cboPersonajesBestias.getSelectionModel().getSelectedItem() instanceof Orco){
            bestias.set(lstBestias.getSelectionModel().getSelectedIndex(), new Orco(txtNombreBestia.getText(), Integer.parseInt(txtVidaBestia.getText()), Integer.parseInt(txtArmaduraBestia.getText())));
        }
        else {
            bestias.set(lstBestias.getSelectionModel().getSelectedIndex(), new Trasgo(txtNombreBestia.getText(), Integer.parseInt(txtVidaBestia.getText()), Integer.parseInt(txtArmaduraBestia.getText())));
        }
        limpiarPanelBestias();
        btnGuardarBestia.setDisable(false);
        btnModificarBestia.setDisable(true);
        btnEliminarBestia.setDisable(true);
    }

    @FXML
    public void modificarHeroe() {

        if (cboPersonajesHeroes.getSelectionModel().getSelectedItem() instanceof Elfo) {
            heroes.set(lstHeroes.getSelectionModel().getSelectedIndex(), new Elfo(txtNombreHeroe.getText(), Integer.parseInt(txtVidaHeroe.getText()), Integer.parseInt(txtArmaduraHeroe.getText())));
        }
        if (cboPersonajesHeroes.getSelectionModel().getSelectedItem() instanceof Hobbits) {
            heroes.set(lstHeroes.getSelectionModel().getSelectedIndex(), new Hobbits(txtNombreHeroe.getText(), Integer.parseInt(txtVidaHeroe.getText()), Integer.parseInt(txtArmaduraHeroe.getText())));
        }
        else {
            heroes.set(lstHeroes.getSelectionModel().getSelectedIndex(), new Humanos(txtNombreHeroe.getText(), Integer.parseInt(txtVidaHeroe.getText()), Integer.parseInt(txtArmaduraHeroe.getText())));
        }
        limpiarPanelHeroes();
        btnGuardarHeroe.setDisable(false);
        btnModificarBestia.setDisable(true);
        btnEliminarBestia.setDisable(true);
    }



    @FXML
    public void eliminarBestia(){
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Eliminar");
        mensaje.setHeaderText("Se eliminará una bestia.");
        mensaje.setContentText("¿Estás seguro que quieres eliminarla?");
        Optional<ButtonType> resultado = mensaje.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            bestias.remove(lstBestias.getSelectionModel().getSelectedIndex());
            limpiarPanelBestias();
        }
    }

    @FXML
    public void eliminarHeroe() {
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Eliminar");
        mensaje.setHeaderText("Se eliminará un Heroe.");
        mensaje.setContentText("¿Estás seguro que quieres eliminarlo?");
        Optional<ButtonType> resultado = mensaje.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            heroes.remove(lstHeroes.getSelectionModel().getSelectedIndex());
            limpiarPanelHeroes();
        }

    }

    @FXML
    public void limpiarPanelBestias(){
        //Limpiar textos
        txtNombreBestia.setText("");
        txtVidaBestia.setText("");
        txtArmaduraBestia.setText("");
        cboPersonajesBestias.getSelectionModel().select(null);
        //Limpiar lista
        lstBestias.getSelectionModel().clearSelection();
        btnGuardarBestia.setDisable(false);
        btnModificarBestia.setDisable(true);
        btnEliminarBestia.setDisable(true);
    }

    @FXML
    public void limpiarPanelHeroes(){

        txtNombreHeroe.setText("");
        txtVidaHeroe.setText("");
        txtArmaduraHeroe.setText("");
        cboPersonajesHeroes.getSelectionModel().select(null);

        lstHeroes.getSelectionModel().clearSelection();
        btnGuardarHeroe.setDisable(false);
        btnModificarHeroe.setDisable(true);
        btnEliminarHeroe.setDisable(true);
    }

    public void validarBestia() {
        erroresBestias.clear();
        if (txtNombreBestia.getText().isEmpty())
            erroresBestias.add("Las bestias, aun que sean bestias, tienen nombre. Introduce el nombre de la bestia que quieres crear.");
        if (txtVidaBestia.getText().isEmpty())
            erroresBestias.add("Las bestias sin vidas no van a llegar muy lejos. Introduce la vida de la bestia.");
        if (txtArmaduraBestia.getText().isEmpty())
            erroresBestias.add("Introduce la armadura");

        if (!txtVidaBestia.getText().isEmpty()) {
            try { Integer.parseInt(txtVidaBestia.getText());
            }catch (NumberFormatException e){
                erroresBestias.add("El campo vida debe ser numérico.");
            }
        }

        if (!txtArmaduraBestia.getText().isEmpty()) {
            try { Integer.parseInt(txtArmaduraBestia.getText());
            }catch (NumberFormatException e){
                erroresBestias.add("El campo Armadura debe ser numérico.");
            }
        }
    }

    public void validarHeroe(){
        erroresHeroes.clear();
        if (txtNombreHeroe.getText().isEmpty())
            erroresHeroes.add("Introduce el nombre del que quieres crear.");
        if (txtVidaHeroe.getText().isEmpty())
            erroresHeroes.add("El campo vida es obligatorio.");
        if (txtArmaduraHeroe.getText().isEmpty())
            erroresHeroes.add("Introduce la armadura");


        if (!txtVidaHeroe.getText().isEmpty()) {
            try { Integer.parseInt(txtVidaHeroe.getText());
            }catch (NumberFormatException e){
                erroresHeroes.add("El campo vida debe ser numérico.");
            }
        }

        if (!txtArmaduraHeroe.getText().isEmpty()) {
            try { Integer.parseInt(txtArmaduraHeroe.getText());
            }catch (NumberFormatException e){
                erroresHeroes.add("El campo Armadura debe ser numérico.");
            }
        }
    }

    //Batalla

    @FXML
    public void limpiarBatalla(){
        btnLimpiarBatalla.setDisable(true);
        batalla.clear();
    }

    @FXML
    public void empezarBatalla() {
        Storage storage = new Storage(heroes,bestias);
        empezarLucha(storage);
        btnLimpiarBatalla.setDisable(false);
    }

    public void empezarLucha (Storage storage) {
        Storage Bandos = storage;
        int turno = 0;
        while (!storage.getBando1().isEmpty() && !storage.getBando2().isEmpty()){
            turno++;
            batalla.add("Turno: " + turno);
            Bandos = empezarRonda(Bandos);
        }

        if (storage.getBando1().isEmpty()) {
            batalla.add("Ganan los malos");
        } else if (storage.getBando2().isEmpty()){
            batalla.add("Ganan los buenos");
        } else {
            batalla.add("Empate!");
        }
    }

    public Storage empezarRonda (Storage storage){
        ObservableList<Heroes> buenos = storage.getBando1();
        ObservableList<Bestias> malos = storage.getBando2();
        for (int x = 0; x < buenos.size(); x++) {
            if (x < malos.size()){
                int golpe = buenos.get(x).atacar(malos.get(x));
                batalla.add(buenos.get(x).getNombre() + " inflinge " + golpe + " de daño a " + malos.get(x).getNombre());
                batalla.add(malos.get(x).getNombre() + " recibe: " + malos.get(x).recibirGolpe(golpe, buenos.get(x)) + " y le queda " + malos.get(x).getVida() + " de vida");
                if (!(malos.get(x).getVida() <=0)){
                    int golpeMalos = malos.get(x).atacar(buenos.get(x));
                    batalla.add(malos.get(x).getNombre() + " inflinge " + golpeMalos + " de daño a " + buenos.get(x).getNombre());
                    batalla.add(buenos.get(x).getNombre() + " recibe: " + buenos.get(x).recibirGolpe(golpeMalos, malos.get(x)) + " y le queda " + buenos.get(x).getVida() + " de vida");
                }
            }
        }
        buenos.removeIf(personajes -> personajes.getVida() <= 0);
        //Todo queda notificar que se muera
        malos.removeIf(personajes -> personajes.getVida() <= 0);
        return new Storage(buenos,malos);
    }

    @FXML
    private void handleClicks(ActionEvent event) {

        //No se puede usar Switch con Objetos ActionEvent

        if (event.getSource() == btnHome) {

            lblStatusMini.setText("/home");
            lblStatus.setText("Bienvenida");
            pniStatus.setBackground(new Background(new BackgroundFill(Color.rgb(113,86,221), CornerRadii.EMPTY, Insets.EMPTY)));
            pnHome.toFront();

        }
        else if (event.getSource() == btnBestias) {

            lblStatusMini.setText("/home/Bestias");
            lblStatus.setText("Bestias");
            pniStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43,99,63), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBestias.toFront();
            pnBatalla.toBack();
            pnHome.toBack();
        }
        else if (event.getSource() == btnHeroes) {

            lblStatusMini.setText("/home/Heroes");
            lblStatus.setText("Heroes");
            pniStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43,63,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnHeroes.toFront();
            pnBatalla.toBack();
            pnHome.toBack();
        }

        else if (event.getSource() == btnBatalla) {
            lblStatusMini.setText("/home/Batalla");
            lblStatus.setText("Batalla");
            pniStatus.setBackground(new Background(new BackgroundFill(Color.rgb(42,28,66), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBatalla.toFront();
        }
    }
}
