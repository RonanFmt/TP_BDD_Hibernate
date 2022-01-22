package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.AchatDAO;
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
	static private double TVA = 1.15;
	
	@SuppressWarnings("resource")
	static public void main(String args[]) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp_bdd_hibernate");
		EntityManager em = emf.createEntityManager();
		
		Client c1 = ClientDAO.createClient(em, "Martin", "Jean", "64 avenue Jean Portalis", "Tours", 20);
		
		Produit p1 = ProduitDAO.createProduit(em, "Acanthe epineuse", "fleur", "Acanthacees", 3.56, 10);
		Produit p2 = ProduitDAO.createProduit(em, "Callune", "fleur", "Ericacees", 2.86, 36);
		Produit p3 = ProduitDAO.createProduit(em, "Abricotier", "plante", "Prunus", 4.52, 50);
		Produit p4 = ProduitDAO.createProduit(em, "Chene", "plante", "Fagacees", 1.25, 20);
		Produit p5 = ProduitDAO.createProduit(em, "Euphorbe d'Irlande", "fleur", "Euphorbiacees", 7.41, 8);
		Produit p6 = ProduitDAO.createProduit(em, "Joubarbe", "fleur", "Crassulacees", 5.12, 13);
		
		List<Produit> listeProduitsFournis1 = new ArrayList<Produit>();
		listeProduitsFournis1.add(p1);
		listeProduitsFournis1.add(p2);
		listeProduitsFournis1.add(p3);
		
		List<Produit> listeProduitsFournis2 = new ArrayList<Produit>();
		listeProduitsFournis2.add(p4);
		listeProduitsFournis2.add(p5);
		listeProduitsFournis2.add(p6);
		
		Fournisseur f1 = FournisseurDAO.createFournisseur(em, "Bernard", "Francois", "5316 avenue du parc", "Montreal", listeProduitsFournis1);
		Fournisseur f2 = FournisseurDAO.createFournisseur(em, "Thomas", "Benoit", "155 boulevard Rene Levesque E.", "Montreal", listeProduitsFournis2);
		
		System.out.println("\nProduits du fournisseur ID n°" + f1.getId() + " (" + f1.getPrenom() + " " + f1.getNom() + ") : ");
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
				
				if (prod.getFournisseur().getId() == f1.getId()) {
					factureF1 += prod.getPrixUnitaire() * quantite;
					listeProduitsAchetesF1.add(prod);
				}
				else if (prod.getFournisseur().getId() == f2.getId()) {
					factureF2 += prod.getPrixUnitaire() * quantite * TVA;
					listeProduitsAchetesF1.add(prod);
				}
				
				AchatDAO.createAchat(em, c1, prod, quantite);
			}
		}
		
		factureF1 -= factureF1 * c1.getReduction() / 100;
		factureF2 -= factureF2 * c1.getReduction() / 100;
		
		Fourniture fourniture1 = FournitureDAO.createFourniture(em, factureF1, listeProduitsAchetesF1, f1);
		Fourniture fourniture2 = FournitureDAO.createFourniture(em, factureF2, listeProduitsAchetesF2, f2);
		System.out.println("\n" + fourniture1.getFacture() + " euros achetés chez le fournisseur ID n°" + fourniture1.getFournisseur().getId() + ".");
		System.out.println(fourniture2.getFacture() + " euros achetés chez le fournisseur ID n°" + fourniture2.getFournisseur().getId() + ".");
				
		System.out.println("\nCommande du client " + c1.getPrenom() + " " + c1.getNom() + " (ID n°" + c1.getId() + ") :");
		HashMap<Produit, Integer> produitsClient1 = AchatDAO.getProduitsParClient(em, c1);
		for (Map.Entry<Produit, Integer> entry : produitsClient1.entrySet())
			System.out.println("Produit : " + entry.getKey().getNom() + " -> quantite demandée : " + entry.getValue());
		
		double prixTotal = factureF1 + factureF2;
		System.out.println("\nLe prix de la commande s'élève à " + prixTotal + " euros.");
		
		em.close();
		emf.close();
				
	}	
}
