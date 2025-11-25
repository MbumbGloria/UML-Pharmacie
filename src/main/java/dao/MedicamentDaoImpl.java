package dao;

import entity.Medicament;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDaoImpl implements IMedicamentDao {
    // Simule la base de données en mémoire
    private List<Medicament> baseDeDonnees;

    public MedicamentDaoImpl() {
        this.baseDeDonnees = new ArrayList<>();
    }

    @Override
    public void ajouter(Medicament medicament) {
        // On pourrait vérifier si le code existe déjà ici
        baseDeDonnees.add(medicament);
    }

    @Override
    public List<Medicament> listerTout() {
        return baseDeDonnees;
    }

    @Override
    public Medicament trouverParCode(String code) {
        for (Medicament m : baseDeDonnees) {
            if (m.getCode().equalsIgnoreCase(code)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public boolean supprimer(String code) {
        Medicament m = trouverParCode(code);
        if (m != null) {
            baseDeDonnees.remove(m);
            return true;
        }
        return false;
    }

    @Override
    public boolean mettreAJourStock(String code, int nouveauStock) {
        Medicament m = trouverParCode(code);
        if (m != null) {
            m.setStock(nouveauStock);
            return true;
        }
        return false;
    }
}