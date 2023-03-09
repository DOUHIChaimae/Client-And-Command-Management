package ma.enset.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.dao.ClientDaoImpl;
import ma.enset.dao.CommandeDaoImpl;
import ma.enset.entities.Client;
import ma.enset.entities.Commande;
import ma.enset.services.CatalogueService;
import ma.enset.services.CatalogueServieImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {

    @FXML
    private TextField textDate;
    @FXML
    private TextField textSearch;
    @FXML
    private ComboBox<Client> clientComboBox;
    @FXML
    private TableView<Commande> tableCommande;
    @FXML
    TableColumn<Commande, Integer> colId;
    @FXML
    TableColumn<Commande, Date> colDate;
    @FXML
    private TableColumn<Commande, Client> colClt;
    @FXML
    ObservableList<Commande> commandeObservableList = FXCollections.observableArrayList();
    private CatalogueService catalogueService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        catalogueService = new CatalogueServieImpl(new ClientDaoImpl(), new CommandeDaoImpl());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateDeCommande"));
        colClt.setCellValueFactory(new PropertyValueFactory<>("client"));
        try {
            clientComboBox.getItems().addAll(catalogueService.getAllClients());
            tableCommande.setItems(commandeObservableList);
            loadCommande();
            textSearch.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    commandeObservableList.clear();
                    commandeObservableList.addAll(catalogueService.chercherParMc(newValue));
                }
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void addCommande() throws SQLException {
        String date = textDate.getText();
        if (date.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez entrer une date !!!");
            alert.show();
        } else {
            Commande commande = new Commande();
            commande.setDateDeCommande(date);
            commande.setClient(clientComboBox.getSelectionModel().getSelectedItem());
            catalogueService.addCommande(commande);
            loadCommande();
            tableCommande.setItems(commandeObservableList);
            textDate.clear();
        }
    }

    @FXML
    private void deleteCommande() throws SQLException {
        MultipleSelectionModel<Commande> mpCommande = tableCommande.getSelectionModel();
        if (mpCommande != null) {
            catalogueService.deleteCommande(mpCommande.getSelectedItem());
            commandeObservableList.remove(mpCommande.getSelectedIndex());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un élément !!");
            alert.show();
        }
    }

    private void updateCommand() {

    }

    private void loadCommande() throws SQLException {
        commandeObservableList.clear();
        commandeObservableList.addAll(catalogueService.getAllCommandes());
    }
}
