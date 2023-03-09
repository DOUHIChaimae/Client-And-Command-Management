package ma.enset.dao;

import ma.enset.entities.Client;
import ma.enset.entities.Commande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDaoImpl implements CommandeDao {
    @Override
    public List<Commande> findAll() throws SQLException {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = connection.prepareStatement("select C.IdCommande,C.date,C.IdC,Clt.NOM,Clt.PRENOM " +
                "from COMMANDE C, CLIENTS Clt where C.IdC=Clt.ID");
        ResultSet rs = statement.executeQuery();
        List<Commande> commandes = new ArrayList<>();
        while (rs.next()) {
            Commande commande = new Commande();
            commande.setId(rs.getInt("IdCommande"));
            commande.setDateDeCommande(rs.getString("DATE"));
            Client client = new Client();
            client.setId(rs.getInt("IdC"));
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
            commande.setClient(client);
            commandes.add(commande);
        }
        return commandes;
    }

    @Override
    public Commande findOne(int id) throws SQLException {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from COMMANDE where ID = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Commande commande = new Commande();
        if (rs.next()) {
            commande.setId(rs.getInt("ID"));
            commande.setDateDeCommande(rs.getString("DATE"));
        }
        return commande;
    }

    @Override
    public Commande save(Commande commande) throws SQLException {
        Connection connection = SingletonConnexionDb.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into COMMANDE(DATE,IdC)" + "values (?,?)");
        preparedStatement.setString(1, commande.getDateDeCommande());
        preparedStatement.setInt(2, commande.getClient().getId());
        preparedStatement.executeUpdate();
        return commande;
    }

    @Override
    public boolean delete(Commande commande) throws SQLException {
        try {
            Connection connection = SingletonConnexionDb.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from COMMANDE where IdCommande =?");
            preparedStatement.setInt(1, commande.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Commande update(Commande o) throws SQLException {
        return null;
    }

    @Override
    public List<Commande> searchByMc(String MC) {
        Connection connection = SingletonConnexionDb.getConnection();
        List<Commande> commandes = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * from COMMANDE,CLIENTS" +
                    " where DATE like ? and COMMANDE.IDC=CLIENTS.ID");
            pstm.setString(1, "%" + MC + "%");
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                Commande commande = new Commande();
                commande.setId(res.getInt("IdCommande"));
                commande.setDateDeCommande(res.getString("DATE"));
                Client client = new Client();
                client.setId(res.getInt("ID"));
                client.setNom(res.getString("NOM"));
                client.setPrenom(res.getString("PRENOM"));
                client.setAdresse(res.getString("ADDRESSE"));
                client.setEmail(res.getString("EMAIL"));
                client.setAge(res.getInt("AGE"));
                commande.setClient(client);
                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }
}
