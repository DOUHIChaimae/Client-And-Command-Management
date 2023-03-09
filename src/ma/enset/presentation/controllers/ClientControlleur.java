package ma.enset.presentation.controllers;

import JavaFx.dao.ClientDaoImpl;
import JavaFx.dao.CommandeDaoImpl;
import JavaFx.entities.Client;
import JavaFx.services.CatalogueService;
import JavaFx.services.CatalogueServieImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientControlleur implements Initializable {

    @FXML
    private TextField textNom;
    @FXML
    private TextField textPrenom;
    @FXML
    private TextField textAddresse;
    @FXML
    private TextField textAge;
    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableColumn<Client, Integer> col_Id;
    @FXML
    private TableColumn<Client, String> col_Nom;
    @FXML
    private TableColumn<Client, String> col_Prenom;
    @FXML
    private TableColumn<Client, String> col_Addresse;
    @FXML
    private TableColumn<Client, String> col_Age;
    @FXML
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    @FXML
    private CatalogueService catalogueService = new CatalogueServieImpl(new ClientDaoImpl(), new CommandeDaoImpl());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            col_Addresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            col_Age.setCellValueFactory(new PropertyValueFactory<>("age"));
            tableClient.setItems(clients);
            loadClients();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addClient() throws SQLException {
        String nom = textNom.getText();
        String prenom = textPrenom.getText();
        String addresse = textAddresse.getText();
        int age = Integer.parseInt(textAge.getText());
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setAdresse(addresse);
        client.setAge(age);
        catalogueService.addClient(client);
        loadClients();
    }
    public void deleteClient() throws SQLException {
        Client client = tableClient.getSelectionModel().getSelectedItem();
        catalogueService.deleteClient(client);
        loadClients();
    }

    private void loadClients() throws SQLException {
        clients.clear();
        clients.addAll(catalogueService.getAllClients());
    }
}
