package entity;

public class Injection extends Medicament {
    private double volumeDose;
    private String voieAdministration; // ex: Intraveineuse

    public Injection(String nomCommercial, String code, double prix, String dateExpiration,
                     int stock, String laboratoire, double volumeDose, String voieAdministration) {
        super(nomCommercial, code, prix, dateExpiration, stock, laboratoire, "Injection");
        this.volumeDose = volumeDose;
        this.voieAdministration = voieAdministration;
    }

    public double getVolumeDose() { return volumeDose; }
    public void setVolumeDose(double volumeDose) { this.volumeDose = volumeDose; }

    public String getVoieAdministration() { return voieAdministration; }
    public void setVoieAdministration(String voieAdministration) { this.voieAdministration = voieAdministration; }

    @Override
    public String toString() {
        return super.toString() + " | Détails: " + volumeDose + "ml via " + voieAdministration;
    }
}