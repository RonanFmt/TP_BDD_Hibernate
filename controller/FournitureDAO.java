package controller;

import java.util.List;

import javax.persistence.EntityManager;

import model.Fournisseur;
import model.Fourniture;
import model.Produit;


public class FournitureDAO 
{
	public static Fourniture createFourniture(EntityManager em, double facture, List<Produit> listeProduitsAchetes, Fournisseur fournisseur)
	{
		Fourniture fourniture = new Fourniture();
		fourniture.setFacture(facture);		
		fourniture.setFournisseur(fournisseur);
		
		fournisseur.setFourniture(fourniture);
		
		for (Produit prod : listeProduitsAchetes) {
			fourniture.addProduitAchete(prod);
			prod.setFourniture(fourniture);
			em.persist(fourniture);
		}
			
		em.getTransaction().begin();
		em.persist(fourniture);
		em.persist(fournisseur);
		em.getTransaction().commit();
		
		return fourniture;
	}
}