package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Achat
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Client client;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Produit produit;
	
	private int quantiteDemandee;
	
	public int getId()
	{
		return id;
	}
	
	public Client getClient()
	{
		return client;
	}
	
	public Produit getProduit()
	{
		return produit;
	}
	
	public int getQuantiteDemandee()
	{
		return quantiteDemandee;
	}
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	
	public void setProduit(Produit produit)
	{
		this.produit = produit;
	}
	
	public void setQuantiteDemandee(int quantiteDemandee)
	{
		this.quantiteDemandee = quantiteDemandee;
	}
}
