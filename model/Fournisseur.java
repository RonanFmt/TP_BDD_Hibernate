package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Fournisseur extends Personne
{		
	@OneToMany(cascade=CascadeType.ALL, mappedBy="fournisseur")
	private List<Produit> listeProduitsFournis;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Fourniture fourniture;
	
	public Fournisseur() 
	{
		listeProduitsFournis = new ArrayList<Produit>();
	}
	
	public void addProduit(Produit produit)
	{
		listeProduitsFournis.add(produit);
	}
		
	public List<Produit> getListeProduits()
	{
		return listeProduitsFournis;
	}
	
	public Fourniture getFourniture()
	{
		return fourniture;
	}
}
