package view;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.ClientDAO;
import controller.ProduitDAO;
import model.Client;
import model.Produit;


public class Application 
{		
	@SuppressWarnings("resource")
	static public void main(String args[]) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp_bdd_hibernate");
		EntityManager em = emf.createEntityManager();
		
		Client c1 = ClientDAO.createClient(em, "nom1", "prenom1", "adresse1", "ville1", 20);
		Client c2 = ClientDAO.createClient(em, "nom2", "prenom2", "adresse2", "ville2", 30);
		Client c3 = ClientDAO.createClient(em, "nom3", "prenom3", "adresse3", "ville3", 25);
		
		Produit p1 = ProduitDAO.createProduit(em, "nom1", "fleur", "espece1", 3.56, 10);
		Produit p2 = ProduitDAO.createProduit(em, "nom2", "fleur", "espece2", 2.86, 36);
		Produit p3 = ProduitDAO.createProduit(em, "nom3", "plante", "espece3", 4.52, 50);
		Produit p4 = ProduitDAO.createProduit(em, "nom4", "plante", "espece4", 1.25, 20);
		Produit p5 = ProduitDAO.createProduit(em, "nom5", "fleur", "espece5", 7.41, 8);
		
		p1.afficherProduit();
		p2.afficherProduit();
		p3.afficherProduit();
		p4.afficherProduit();
		p5.afficherProduit();
		
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
				prod.afficherProduit();
			}
		}
		
		em.close();
		emf.close();
				
	}	
}
