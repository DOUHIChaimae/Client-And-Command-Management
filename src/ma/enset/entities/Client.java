package ma.enset.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {

    private int id;
    private String nom, prenom, addresse, email;
    private int age;
    private List<Commande> commandes = new ArrayList<>();

    private static int ID_GENERATOR = 1;

    public Client(String nom, String prenom, String adresse, String email, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = adresse;
        this.email = email;
        this.age = age;
        this.id = ID_GENERATOR++;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return addresse;
    }

    public void setAdresse(String adresse) {
        this.addresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder()
                .append(nom)
                .append(" ")
                .append(prenom);
        return st.toString();
    }
}
