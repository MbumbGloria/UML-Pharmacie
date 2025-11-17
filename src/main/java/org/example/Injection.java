package org.example;

public class Injection extends Medicament {
    private double volumeDose;
    private String voieAdministration;

    public Injection(String nomCommercial, String code, double prix, String dateExpiration,
                     int stock, String laboratoire, double volumeDose, String voieAdministration) {
        super(nomCommercial, code, prix, dateExpiration, stock, laboratoire, "Injection");
        this.volumeDose = volumeDose;
        this.voieAdministration = voieAdministration;
    }

    public double getVolumeDose() {
        return volumeDose;
    }
    public void setVolumeDose(double volumeDose) {
        this.volumeDose = volumeDose;
    }

    public String getVoieAdministration() {
        return voieAdministration;
    }
    public void setVoieAdministration(String voieAdministration) {
        this.voieAdministration = voieAdministration;
    }
}
