package fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.stocks;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Stock {
    @Id
    @GeneratedValue
    private long id;

    private String nom;

    private int quantite;

    public Stock() {
    }

    public Stock(String nom, int quantite) {
        this.nom = nom;
        this.quantite = quantite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
