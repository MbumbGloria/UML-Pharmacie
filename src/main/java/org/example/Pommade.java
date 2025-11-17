package org.example;

public class Pommade extends Medicament {
    private int quantite;
    private String zoneApplication;

    public Pommade(String nomCommercial, String code, double prix, String dateExpiration,
                   int stock, String laboratoire, int quantite, String zoneApplication) {
        super(nomCommercial, code, prix, dateExpiration, stock, laboratoire, "Pommade");
        this.quantite = quantite;
        this.zoneApplication = zoneApplication;
    }

    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getZoneApplication() {
        return zoneApplication;
    }
    public void setZoneApplication(String zoneApplication) {
        this.zoneApplication = zoneApplication;
    }
}

