import dao.IMedicamentDao;
import dao.MedicamentDaoJsonImpl;
import entity.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    // On utilise maintenant l'implémentation JSON
    private static IMedicamentDao dao = new MedicamentDaoJsonImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Démarrage du système...");
        // Plus besoin d'initialiser des fausses données,
        // elles sont lues depuis le fichier JSON s'il existe.

        int choix;
        do {
            afficherMenuPrincipal();
            choix = lireEntier("Votre choix : ");

            switch (choix) {
                case 1:
                    menuAjouter();
                    break;
                case 2:
                    afficherTousMedicaments();
                    break;
                case 3:
                    menuRechercher();
                    break;
                case 4:
                    menuMiseAJourStock();
                    break;
                case 5:
                    menuSupprimer();
                    break;
                case 0:
                    System.out.println("Sauvegarde et fermeture de l'application...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 0);
    }

    // --- MENUS D'AFFICHAGE ---

    private static void afficherMenuPrincipal() {
        System.out.println("\n=== GESTION PHARMACIE (PERSISTANCE JSON) ===");
        System.out.println("1. Ajouter un médicament");
        System.out.println("2. Lister les médicaments");
        System.out.println("3. Rechercher un médicament");
        System.out.println("4. Mettre à jour le stock");
        System.out.println("5. Supprimer un médicament");
        System.out.println("0. Quitter");
        System.out.println("============================================");
    }

    // --- FONCTIONS CRUD ---

    private static void menuAjouter() {
        System.out.println("\n--- AJOUT NOUVEAU MEDICAMENT ---");
        System.out.println("Type de médicament :");
        System.out.println("1. Comprimé");
        System.out.println("2. Sirop");
        System.out.println("3. Injection");
        System.out.println("4. Pommade");
        System.out.println("0. Annuler");

        int type = lireEntier("Votre choix : ");
        if (type == 0) return;

        System.out.print("Code unique : ");
        String code = scanner.nextLine();

        if(dao.trouverParCode(code) != null) {
            System.out.println("Erreur : Ce code existe déjà !");
            return;
        }

        System.out.print("Nom commercial : ");
        String nom = scanner.nextLine();
        System.out.print("Laboratoire : ");
        String labo = scanner.nextLine();
        System.out.print("Date Expiration (JJ/MM/AAAA) : ");
        String date = scanner.nextLine();
        double prix = lireDouble("Prix : ");
        int stock = lireEntier("Stock initial : ");

        Medicament nouveau = null;

        switch (type) {
            case 1:
                System.out.print("Dosage (ex: 500mg) : ");
                String dosage = scanner.nextLine();
                int nb = lireEntier("Nombre de comprimés : ");
                nouveau = new Comprime(nom, code, prix, date, stock, labo, dosage, nb);
                break;
            case 2:
                int vol = lireEntier("Volume (ml) : ");
                System.out.print("Goût : ");
                String gout = scanner.nextLine();
                nouveau = new Sirop(nom, code, prix, date, stock, labo, vol, gout);
                break;
            case 3:
                double dose = lireDouble("Volume dose (ml) : ");
                System.out.print("Voie d'administration : ");
                String voie = scanner.nextLine();
                nouveau = new Injection(nom, code, prix, date, stock, labo, dose, voie);
                break;
            case 4:
                int qte = lireEntier("Quantité (g) : ");
                System.out.print("Zone d'application : ");
                String zone = scanner.nextLine();
                nouveau = new Pommade(nom, code, prix, date, stock, labo, qte, zone);
                break;
            default:
                System.out.println("Type inconnu.");
        }

        if (nouveau != null) {
            dao.ajouter(nouveau);
            System.out.println("Succès : Données sauvegardées dans medicaments.json !");
        }
    }

    private static void afficherTousMedicaments() {
        List<Medicament> liste = dao.listerTout();
        System.out.println("\n--- LISTE DES MEDICAMENTS (" + liste.size() + ") ---");
        if (liste.isEmpty()) {
            System.out.println("Aucune donnée. Ajoutez des médicaments.");
        } else {
            for (Medicament m : liste) {
                System.out.println(m); // Utilise le toString() de l'objet
            }
        }
    }

    private static void menuRechercher() {
        System.out.print("\nEntrez le code à rechercher : ");
        String code = scanner.nextLine();
        Medicament m = dao.trouverParCode(code);
        if (m != null) {
            System.out.println("Trouvé : " + m);
        } else {
            System.out.println("Aucun médicament trouvé.");
        }
    }

    private static void menuMiseAJourStock() {
        System.out.print("\nEntrez le code du médicament : ");
        String code = scanner.nextLine();
        Medicament m = dao.trouverParCode(code);

        if (m != null) {
            System.out.println("Stock actuel : " + m.getStock());
            int nouveauStock = lireEntier("Nouveau stock : ");
            dao.mettreAJourStock(code, nouveauStock);
            System.out.println("Succès : Stock mis à jour et sauvegardé.");
        } else {
            System.out.println("Erreur : Médicament introuvable.");
        }
    }

    private static void menuSupprimer() {
        System.out.print("\nEntrez le code à supprimer : ");
        String code = scanner.nextLine();

        if (dao.supprimer(code)) {
            System.out.println("Succès : Médicament supprimé du fichier.");
        } else {
            System.out.println("Erreur : Code introuvable.");
        }
    }

    // --- UTILITAIRES ---

    private static int lireEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Nombre entier requis.");
            }
        }
    }

    private static double lireDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Nombre décimal requis (avec un point).");
            }
        }
    }
}