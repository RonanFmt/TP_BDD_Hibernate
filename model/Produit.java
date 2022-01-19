package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Produit 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String nom;
	private String categorie;
	private String espece;
	private double prixUnitaire;
	private int quantiteDispo;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Fournisseur fournisseur;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Fourniture fourniture;
	
	public int getId()
	{
		return id;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getCategorie()
	{
		return categorie;
	}
	
	public String getEspece()
	{
		return espece;
	}
	
	public double getPrixUnitaire()
	{
		return prixUnitaire;
	}
	
	public int getQuantiteDispo()
	{
		return quantiteDispo;
	}
	
	public Fournisseur getFournisseur()
	{
		return fournisseur;
	}
	
	public Fourniture getFourniture()
	{
		return fourniture;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public void setCategorie(String categorie)
	{
		this.categorie = categorie;
	}
	
	public void setEspece(String espece)
	{
		this.espece = espece;
	}
	
	public void setPrixUnitaire(double prixUnitaire)
	{
		this.prixUnitaire = prixUnitaire;
	}
	
	public void setQuantiteDispo(int quantiteDispo)
	{
		this.quantiteDispo = quantiteDispo;
	}
	
	public void afficherProduit()
	{
		System.out.println("ID : " + id + ", nom : " + nom + ", categorie : " + categorie + ", espèce : " + espece + ", prix unitaire : " + prixUnitaire + ", quantité disponible : " + quantiteDispo);
	}
}
