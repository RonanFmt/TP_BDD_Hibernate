package model;

import javax.persistence.Entity;


@Entity
public class Client extends Personne
{
	private int reduction;
		
	public void setReduction(int reduction)
	{
		this.reduction = reduction;
	}
	
	public int getReduction()
	{
		return reduction;
	}
}
