package ma.enset.services;

import JavaFx.dao.Dao;
import JavaFx.entities.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    Dao cltdao;

    public ClientServiceImpl(Dao dao) {
        this.cltdao = dao;
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
    public Client getClients() {
        return null;
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        return cltdao.findAll();
    }
}
