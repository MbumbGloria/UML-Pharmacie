package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Medicament;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDaoJsonImpl implements IMedicamentDao {
    private List<Medicament> baseDeDonnees;
    private final String NOM_FICHIER = "medicaments.json";
    private Gson gson;

    public MedicamentDaoJsonImpl() {
        // Configuration de Gson avec notre Adaptateur pour le polymorphisme
        // et setPrettyPrinting pour avoir un JSON lisible par les humains
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Medicament.class, new MedicamentAdapter())
                .setPrettyPrinting()
                .create();

        this.baseDeDonnees = new ArrayList<>();
        chargerDonnees(); // Chargement automatique au démarrage
    }

    // --- METHODES DE PERSISTANCE (Privées) ---

    private void chargerDonnees() {
        File file = new File(NOM_FICHIER);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                // TypeToken est nécessaire pour dire à Gson qu'on veut une List<Medicament>
                Type listType = new TypeToken<ArrayList<Medicament>>(){}.getType();
                List<Medicament> listeChargee = gson.fromJson(reader, listType);

                if (listeChargee != null) {
                    this.baseDeDonnees = listeChargee;
                }
            } catch (IOException e) {
                System.err.println("Erreur lors du chargement des données : " + e.getMessage());
            }
        }
    }

    private void sauvegarderDonnees() {
        try (Writer writer = new FileWriter(NOM_FICHIER)) {
            gson.toJson(baseDeDonnees, writer);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    // --- IMPLEMENTATION CRUD ---

    @Override
    public void ajouter(Medicament medicament) {
        baseDeDonnees.add(medicament);
        sauvegarderDonnees(); // Sauvegarde après modification
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
            sauvegarderDonnees(); // Sauvegarde après modification
            return true;
        }
        return false;
    }

    @Override
    public boolean mettreAJourStock(String code, int nouveauStock) {
        Medicament m = trouverParCode(code);
        if (m != null) {
            m.setStock(nouveauStock);
            sauvegarderDonnees(); // Sauvegarde après modification
            return true;
        }
        return false;
    }
}