package org.example;

public class Comprime extends Medicament {
    private String dosage;
    private int nbComprimes;

    public Comprime(String nomCommercial, String code, double prix, String dateExpiration,
                    int stock, String laboratoire, String dosage, int nbComprimes) {
        super(nomCommercial, code, prix, dateExpiration, stock, laboratoire, "Comprimé");
        this.dosage = dosage;
        this.nbComprimes = nbComprimes;
    }

    public String getDosage() {
        return dosage;
    }
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getNbComprimes() {
        return nbComprimes;
    }
    public void setNbComprimes(int nbComprimes) {
        this.nbComprimes = nbComprimes;
    }
}
