package ma.enset.services;

import ma.enset.entities.Commande;

import java.sql.SQLException;
import java.util.List;

public interface CommandeService {
    List<Commande> getAllCommandes() throws SQLException;
    Commande getCategorieById(int Id) throws SQLException;
    void addCommande(Commande commande) throws SQLException;

}
