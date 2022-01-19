package controller;

import javax.persistence.EntityManager;
import model.Client;


public class ClientDAO
{
	public static Client createClient(EntityManager em, String nom, String prenom, String adresse, String ville, int reduction)
	{
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setAdresse(adresse);
		client.setVille(ville);
		client.setReduction(reduction);
						
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
		
		return client;
	}
}
