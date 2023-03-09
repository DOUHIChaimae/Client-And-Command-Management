package ma.enset.entities;

import java.io.Serializable;

public class Commande implements Serializable {
    private static int ID_GENERATOR = 1;

    private int id;
    private String dateDeCommande;
    private Client client = new Client();

    public Commande(String dateDeCommande, Client client) {
        this.dateDeCommande = dateDeCommande;
        this.client = client;
        this.id = ID_GENERATOR++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande() {
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public String getDateDeCommande() {
        return dateDeCommande;
    }

    public void setDateDeCommande(String dateDeCommande) {
        this.dateDeCommande = dateDeCommande;
    }

}
