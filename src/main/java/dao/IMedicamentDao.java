package dao;

import entity.Medicament;
import java.util.List;

public interface IMedicamentDao {
    void ajouter(Medicament medicament);
    List<Medicament> listerTout();
    Medicament trouverParCode(String code);
    boolean supprimer(String code);
    boolean mettreAJourStock(String code, int nouveauStock);
}