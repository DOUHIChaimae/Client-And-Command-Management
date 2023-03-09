package ma.enset.dao;

import ma.enset.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements Dao<Client> {
    @Override
    public List<Client> findAll() throws SQLException {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from CLIENTS");
        ResultSet rs = statement.executeQuery();
        List<Client> clients = new ArrayList<>();
        while (rs.next()) {
            Client client = new Client();
            client.setId(rs.getInt("ID"));
            client.setNom(rs.getString("NOM"));
            client.setPrenom(rs.getString("PRENOM"));
            client.setAdresse(rs.getString("ADDRESSE"));
            client.setEmail(rs.getString("EMAIL"));
            client.setAge(rs.getInt("AGE"));
            clients.add(client);
        }
        return clients;
    }

    @Override
    public Client findOne(int id) throws SQLException {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from CLIENTS where ID = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Client client = null;
        if (rs.next()) {
            client.setId(rs.getInt("ID"));
            client.setNom(rs.getString("NOM"));
            client.setPrenom(rs.getString("PRENOM"));
            client.setAdresse(rs.getString("ADDRESSE"));
            client.setAdresse(rs.getString("EMAIL"));
            client.setAge(rs.getInt("AGE"));
        }
        return client;
    }

    @Override
    public Client save(Client client) throws SQLException {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into CLIENTS(NOM,PRENOM,ADDRESSE,EMAIL,AGE)" +
                "values (?,?,?,?,?)");
        preparedStatement.setString(1, client.getNom());
        preparedStatement.setString(2, client.getPrenom());
        preparedStatement.setString(3, client.getAdresse());
        preparedStatement.setString(4, client.getEmail());
        preparedStatement.setInt(5, client.getAge());
        preparedStatement.executeUpdate();
        return client;
    }

    @Override
    public boolean delete(Client client) {
        try {
            Connection connection = SingletonConnexionDb.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from clients where id =?");
            preparedStatement.setInt(1, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Client update(Client client) throws SQLException {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update CLIENTS set NOM =?,PRENOM=?,ADRESSE=?, EMAIL=?," +
                "AGE=?" + " where ID =?");
        preparedStatement.setString(1, client.getNom());
        preparedStatement.setString(2, client.getPrenom());
        preparedStatement.setString(3, client.getAdresse());
        preparedStatement.setString(4, client.getEmail());
        preparedStatement.setInt(5, client.getAge());
        preparedStatement.setInt(6, client.getId());
        preparedStatement.executeUpdate();
        return client;
    }
}
