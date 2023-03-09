package ma.enset.services;

import JavaFx.dao.CommandeDao;
import JavaFx.entities.Commande;

import java.sql.SQLException;
import java.util.List;

public class CommandeServiceImpl implements CommandeService {
    private CommandeDao commandeDao;


    public CommandeServiceImpl(CommandeDao commandeDao) {
        this.commandeDao = commandeDao;
    }

    @Override
    public List<Commande> getAllCommandes() throws SQLException {
        return commandeDao.findAll();
    }

    @Override
    public Commande getCategorieById(int Id) throws SQLException {
        return commandeDao.findOne(Id);
    }

    @Override
    public void addCommande(Commande commande) throws SQLException {
        commandeDao.save(commande);
    }
}
