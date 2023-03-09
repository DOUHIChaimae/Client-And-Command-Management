package ma.enset.services;

import ma.enset.dao.CommandeDao;
import ma.enset.dao.Dao;
import ma.enset.entities.Client;
import ma.enset.entities.Commande;

import java.sql.SQLException;
import java.util.List;

public class CatalogueServieImpl implements CatalogueService {
    Dao cltdao;
    CommandeDao commandeDao;

    public CatalogueServieImpl(Dao cltdaodao, CommandeDao commandeDao) {
        this.cltdao = cltdaodao;
        this.commandeDao = commandeDao;
    }

    @Override
    public void addClient(Client client) throws SQLException {
        cltdao.save(client);
    }

    @Override
    public void deleteClient(Client client) throws SQLException {
        cltdao.delete(client);
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        return cltdao.findAll();
    }

    @Override
    public List<Commande> getAllCommandes() throws SQLException {
        return commandeDao.findAll();
    }

    @Override
    public Commande getCommandeById(int Id) throws SQLException {
        return commandeDao.findOne(Id);
    }

    @Override
    public void addCommande(Commande commande) throws SQLException {
        commandeDao.save(commande);
    }
    @Override
    public void deleteCommande(Commande commande) throws SQLException {
        commandeDao.delete(commande);
    }

    @Override
    public List<Commande> chercherParMc(String MC) {
        return commandeDao.searachByMc(MC);
    }
}
