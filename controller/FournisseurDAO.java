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
		
		for(Produit produit : listeProduitsFournis)
			fournisseur.addProduit(produit);
						
		em.getTransaction().begin();
		em.persist(fournisseur);
		em.getTransaction().commit();
		
		return fournisseur;
	}
}
