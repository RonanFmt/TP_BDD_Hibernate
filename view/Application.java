package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.ClientDAO;
import controller.FournisseurDAO;
import controller.FournitureDAO;
import controller.ProduitDAO;
import model.Client;
import model.Fournisseur;
import model.Fourniture;
import model.Produit;


public class Application 
{		
	@SuppressWarnings("resource")
	static public void main(String args[]) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp_bdd_hibernate");
		EntityManager em = emf.createEntityManager();
		
		Client c1 = ClientDAO.createClient(em, "nom1", "prenom1", "adresse1", "ville1", 20);
		// Client c2 = ClientDAO.createClient(em, "nom2", "prenom2", "adresse2", "ville2", 30);
		// Client c3 = ClientDAO.createClient(em, "nom3", "prenom3", "adresse3", "ville3", 25);
		
		Produit p1 = ProduitDAO.createProduit(em, "nom1", "fleur", "espece1", 3.56, 10);
		Produit p2 = ProduitDAO.createProduit(em, "nom2", "fleur", "espece2", 2.86, 36);
		Produit p3 = ProduitDAO.createProduit(em, "nom3", "plante", "espece3", 4.52, 50);
		Produit p4 = ProduitDAO.createProduit(em, "nom4", "plante", "espece4", 1.25, 20);
		Produit p5 = ProduitDAO.createProduit(em, "nom5", "fleur", "espece5", 7.41, 8);
		Produit p6 = ProduitDAO.createProduit(em, "nom6", "fleur", "espece6", 5.12, 13);
		
		List<Produit> listeProduitsFournis1 = new ArrayList<Produit>();
		listeProduitsFournis1.add(p1);
		listeProduitsFournis1.add(p2);
		listeProduitsFournis1.add(p3);
		
		List<Produit> listeProduitsFournis2 = new ArrayList<Produit>();
		listeProduitsFournis2.add(p4);
		listeProduitsFournis2.add(p5);
		listeProduitsFournis2.add(p6);
		
		Fournisseur f1 = FournisseurDAO.createFournisseur(em, "nom1", "prenom1", "adresse1", "ville1", listeProduitsFournis1);
		Fournisseur f2 = FournisseurDAO.createFournisseur(em, "nom2", "prenom2", "adresse2", "ville2", listeProduitsFournis2);
		
		System.out.println("Produits du fournisseur ID n°" + f1.getId() + " (" + f1.getPrenom() + " " + f1.getNom() + ") : ");
		for (Produit prod : f1.getListeProduits())
			prod.afficherProduit();
		
		System.out.println("\nProduits du fournisseur ID n°" + f2.getId() + " (" + f2.getPrenom() + " " + f2.getNom() + ") : ");
		for (Produit prod : f2.getListeProduits())
			prod.afficherProduit();
		
		List<Produit> listeProduitsAchetesF1 = new ArrayList<>();		
		double factureF1 = 0;
		List<Produit> listeProduitsAchetesF2 = new ArrayList<>();		
		double factureF2 = 0;
		
		int id = 1;
		boolean stop = false;
		Scanner scanner = new Scanner(System.in);
		while (!stop) 
		{
			System.out.print("\nVeuillez choisir un ID de produit (0 pour arrêter) : ");
			id = scanner.nextInt();
			if (id == 0) stop = true;
			else {
				System.out.print("Choisir la quantité pour le produit : ");
				int quantite = scanner.nextInt();
				if (!ProduitDAO.checkQuantiteDispo(em, id, quantite)) {
					System.out.println("Quantité insuffisante.");
					System.exit(1);
				}
				ProduitDAO.updateQuantiteDispo(em, id, quantite);
				Produit prod = ProduitDAO.getProduitByID(em, id);
				
				if (prod.getFournisseur().getId() == f1.getId())
				{
					factureF1 += prod.getPrixUnitaire() * quantite;
					listeProduitsAchetesF1.add(prod);
				}
				else if (prod.getFournisseur().getId() == f2.getId())
				{
					factureF2 += prod.getPrixUnitaire() * quantite;
					listeProduitsAchetesF1.add(prod);
				}
				
				prod.afficherProduit();
			}
		}
		
		factureF1 -= factureF1 * c1.getReduction() / 100;
		factureF2 -= factureF2 * c1.getReduction() / 100;
		
		Fourniture fourniture1 = FournitureDAO.createFourniture(em, factureF1, listeProduitsAchetesF1, f1);
		Fourniture fourniture2 = FournitureDAO.createFourniture(em, factureF2, listeProduitsAchetesF2, f2);
		System.out.println("\n" + fourniture1.getFacture() + " euros achetés chez le fournisseur n°" + fourniture1.getFournisseur().getId() + ".");
		System.out.println(fourniture2.getFacture() + " euros achetés chez le fournisseur n°" + fourniture2.getFournisseur().getId() + ".");
		
		double prixTotal = factureF1 + factureF2;
		System.out.println("\nLe prix de la commande s'élève à " + prixTotal + " euros.");
		
		em.close();
		emf.close();
				
	}	
}
