package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Personne 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String nom;
	private String prenom;
	private String adresse;
	private String ville;
	
	public int getId()
	{
		return id;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public String getAdresse()
	{
		return adresse;
	}
	
	public String getVille()
	{
		return ville;
	}
		
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}
	
	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}
	
	public void setVille(String ville)
	{
		this.ville = ville;
	}
}
