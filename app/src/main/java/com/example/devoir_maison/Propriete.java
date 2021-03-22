package com.example.devoir_maison;

import java.util.ArrayList;
import java.util.List;

public class Propriete {
    String idPropriete, titre, description, ville,codePostal,date;
    int nbPieces, prix;
    List caracteristiques;
    ArrayList<String> images;
    Vendeur vendeur;


    public Propriete(String idPropriete, String titre, String description, String ville, int nb_piece_disponible, int prix, String code_postale, String date, ArrayList img, Vendeur v) {
        this.idPropriete = idPropriete;
        this.titre = titre;
        this.description = description;
        this.ville = ville;
        this.nbPieces = nb_piece_disponible;
        this.prix = prix;
        this.date=date;
        this.images=img;
        this.codePostal = code_postale;
        this.caracteristiques = caracteristiques;
        this.vendeur = v;

    }


    public String getIdPropriete() {
        return idPropriete;
    }

    public void setIdPropriete(String idPropriete) {
        this.idPropriete = idPropriete;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode_postale() {
        return codePostal;
    }

    public void setCode_postale(String code_postale) {
        this.codePostal= code_postale;
    }

    public int getNb_piece_disponible() {
        return nbPieces;
    }

    public void setNb_piece_disponible(int nb_piece_disponible) {
        this.nbPieces = nb_piece_disponible;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public List getCaracteristique() {
        return caracteristiques;
    }

    public void setCaracteristique(List caracteristique) {
        this.caracteristiques = caracteristique;
    }

    public Vendeur getV() {
        return vendeur;
    }

    public void setV(Vendeur v) {
        this.vendeur = v;
    }

    public ArrayList<String> getImg() {
        return images;
    }

    public void setImg(ArrayList<String>img) {
        this.images = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
