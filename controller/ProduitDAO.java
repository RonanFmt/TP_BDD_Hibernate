package controller;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Produit;


public class ProduitDAO
{
	public static Produit createProduit(EntityManager em, String nom, String categorie, String espece, double prixUnitaire, int quantiteDispo)
	{
		Produit produit = new Produit();
		produit.setNom(nom);
		produit.setCategorie(categorie);
		produit.setEspece(espece);
		produit.setPrixUnitaire(prixUnitaire);
		produit.setQuantiteDispo(quantiteDispo);
		
		em.getTransaction().begin();
		em.persist(produit);
		em.getTransaction().commit();
		
		return produit;
	}
	
	public static Produit getProduitByID(EntityManager em, int id)
	{
		Query query = em.createQuery("from Produit p where p.id = (?1)");
		query.setParameter(1, id);
		Produit prod = (Produit)query.getSingleResult();
		
		return prod;
	}
	
	public static boolean checkQuantiteDispo(EntityManager em, int id, int quantiteDemandee)
	{
		Query query = em.createQuery("from Produit p where p.id = (?1)");
		query.setParameter(1, id);
		Produit prod = (Produit)query.getSingleResult();
		
		if (prod.getQuantiteDispo() < quantiteDemandee)
			return false;
		return true;
	}
	
	public static void updateQuantiteDispo(EntityManager em, int id, int quantiteDemandee)
	{
		em.getTransaction().begin();
		Query updatedEntities = em.createQuery("update Produit p set p.quantiteDispo = p.quantiteDispo - (?1) where p.id = (?2)");
		updatedEntities.setParameter(1, quantiteDemandee);
		updatedEntities.setParameter(2, id);
		updatedEntities.executeUpdate();
		em.getTransaction().commit();
	}
}
