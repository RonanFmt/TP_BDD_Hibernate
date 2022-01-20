package controller;

import java.util.List;

import javax.persistence.EntityManager;
import model.Fournisseur;
import model.Produit;


public class FournisseurDAO 
{
	public static Fournisseur createFournisseur(EntityManager em, String nom, String prenom, String adresse, String ville, List<Produit> listeProduitsFournis)
	{
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom(nom);
		fournisseur.setPrenom(prenom);
		fournisseur.setAdresse(adresse);
		fournisseur.setVille(ville);
		
		em.getTransaction().begin();
		
		for(Produit produit : listeProduitsFournis) {
			fournisseur.addProduit(produit);
			produit.setFournisseur(fournisseur);
			em.persist(produit);
		}
						
		em.persist(fournisseur);
		em.getTransaction().commit();
		
		return fournisseur;
	}
}
