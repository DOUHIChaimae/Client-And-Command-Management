package ma.enset.services;

import ma.enset.entities.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientService {
    void addClient(Client client) throws SQLException;

    void deleteClient(Client client) throws SQLException;

    Client getClients();

    List<Client> getAllClients() throws SQLException;
}
