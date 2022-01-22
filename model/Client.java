package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class Client extends Personne
{
	private int reduction;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="client")
	private Achat achat;
	
	public int getReduction()
	{
		return reduction;
	}
	
	public Achat getAchat()
	{
		return achat;
	}
	
	public void setReduction(int reduction)
	{
		this.reduction = reduction;
	}
	
	public void setAchat(Achat achat)
	{
		this.achat = achat;
	}
}
