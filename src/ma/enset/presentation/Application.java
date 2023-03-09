package ma.enset.presentation;

import JavaFx.dao.ClientDaoImpl;
import JavaFx.dao.Dao;
import JavaFx.entities.Client;

import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        Dao clientDao = new ClientDaoImpl();
        Client client1 = new Client();
        client1.setNom("akasmiou");
        client1.setPrenom("ouassima");
        client1.setAdresse("hey x");
        clientDao.save(client1);
        List<Client> clients = clientDao.findAll();
        for (Client client : clients) {
            System.out.println(client.getNom() + " " + client.getPrenom() + " " + client.getAdresse());
        }
    }
}
