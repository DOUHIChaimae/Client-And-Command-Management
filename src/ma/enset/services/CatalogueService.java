package ma.enset.services;

import ma.enset.entities.Client;
import ma.enset.entities.Commande;

import java.sql.SQLException;
import java.util.List;

public interface CatalogueService {
    void addClient(Client client) throws SQLException;
    void deleteClient(Client client) throws SQLException;
    List<Client> getAllClients() throws SQLException;
    List<Commande> getAllCommandes() throws SQLException;
    Commande getCommandeById(int Id) throws SQLException;
    void addCommande(Commande commande) throws SQLException;
    void deleteCommande(Commande commande) throws SQLException;
    List<Commande> chercherParMc(String MC);

}
