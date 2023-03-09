package ma.enset.presentation;

import ma.enset.dao.ClientDaoImpl;
import ma.enset.dao.Dao;
import ma.enset.entities.Client;

import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        Dao clientDao = new ClientDaoImpl();
        Client client1 = new Client();
        client1.setNom("douhi");
        client1.setPrenom("chaimae");
        client1.setAdresse("hey el amal");
        clientDao.save(client1);
        List<Client> clients = clientDao.findAll();
        for (Client client : clients) {
            System.out.println(client.getNom() + " " + client.getPrenom() + " " + client.getAdresse());
        }
    }
}
