package controller;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Achat;
import model.Client;
import model.Produit;


public class AchatDAO
{
	public static Achat createAchat(EntityManager em, Client client, Produit produit, int quantiteDemandee)
	{
		Achat achat = new Achat();
		achat.setClient(client);
		achat.setProduit(produit);
		achat.setQuantiteDemandee(quantiteDemandee);
		
		em.getTransaction().begin();			
		em.persist(achat);
		em.getTransaction().commit();
		
		return achat;
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<Produit, Integer> getProduitsParClient(EntityManager em, Client client)
	{
		HashMap<Produit, Integer> produitsParClient = new HashMap<Produit, Integer>();
		Query query = em.createQuery("select a.quantiteDemandee, a.produit from Achat a where a.client = (?1)");
		query.setParameter(1, client);
		List<Object[]> produits = (List<Object[]>)query.getResultList();
	    for(Object[] produit: produits) {
	    	int quantiteDemandee = (int)produit[0];
	    	Produit prod = (Produit)produit[1];
	    	produitsParClient.put(prod, quantiteDemandee);
	    }
		return produitsParClient;
	}
}
