package org.example;

public class Medicament {
    private String nomCommercial;
    private String code;
    private double prix;
    private String dateExpiration;
    private int stock;
    private String laboratoire;
    private String formePharmaceutique;

    public Medicament(String nomCommercial, String code, double prix, String dateExpiration,
                      int stock, String laboratoire, String formePharmaceutique) {
        this.nomCommercial = nomCommercial;
        this.code = code;
        this.prix = prix;
        this.dateExpiration = dateExpiration;
        this.stock = stock;
        this.laboratoire = laboratoire;
        this.formePharmaceutique = formePharmaceutique;
    }


    public String getNomCommercial() {
        return nomCommercial;
    }
    public void setNomCommercial(String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }
    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getLaboratoire() {
        return laboratoire;
    }
    public void setLaboratoire(String laboratoire) {
        this.laboratoire = laboratoire;
    }

    public String getFormePharmaceutique() {
        return formePharmaceutique;
    }
    public void setFormePharmaceutique(String formePharmaceutique) {
        this.formePharmaceutique = formePharmaceutique;
    }

    public String afficherInfos() {
        return nomCommercial + " (" + code + ") - " + formePharmaceutique +
                ", Prix: " + prix + "€, Stock: " + stock + ", Exp: " + dateExpiration;
    }
}
