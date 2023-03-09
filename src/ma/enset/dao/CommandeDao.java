package ma.enset.dao;

import ma.enset.entities.Commande;

import java.util.List;

public interface CommandeDao extends Dao<Commande> {
    List<Commande> searachByMc(String MC);
}
