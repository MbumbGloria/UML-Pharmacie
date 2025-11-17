package org.example;

public class Sirop extends Medicament {
    private int volume;
    private String gout;

    public Sirop(String nomCommercial, String code, double prix, String dateExpiration,
                 int stock, String laboratoire, int volume, String gout) {
        super(nomCommercial, code, prix, dateExpiration, stock, laboratoire, "Sirop");
        this.volume = volume;
        this.gout = gout;
    }

    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public String getGout() {
        return gout;
    }
    public void setGout(String gout) {
        this.gout = gout;
    }
}
