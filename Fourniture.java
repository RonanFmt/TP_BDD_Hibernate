package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Fourniture 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="fourniture")
	private Fournisseur fournisseur;
	
	private double facture;
		
	@OneToMany(cascade=CascadeType.ALL, mappedBy="fourniture")
	private List<Produit> listeProduitsAchetes;
	
	public Fourniture() 
	{
		listeProduitsAchetes = new ArrayList<Produit>();
	}
	
	public void addProduitAchete(Produit produit)
	{
		listeProduitsAchetes.add(produit);
	}
	
	public int getId()
	{
		return id;
	}
	
	public Fournisseur getFournisseur()
	{
		return fournisseur;
	}
	
	public double getFacture()
	{
		return facture;
	}
	
	public List<Produit> getListeProduitsAchetes()
	{
		return listeProduitsAchetes;
	}
	
	public void setFacture(double facture)
	{
		this.facture = facture;
	}
	
	public void setFournisseur(Fournisseur fournisseur)
	{
		this.fournisseur = fournisseur;
	}
}
