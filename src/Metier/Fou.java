package src.Metier;

public class Fou extends Piece
{
	public Fou (int lig, char col, char coul, String type)
	{
		super (lig,col, coul, type);
	}

	public boolean deplacer (int ligD, char colD,int ligF,char colF, Piece[] tab)
	{
		System.out.println("("+this.getType()+") "+colD+""+ligD+" --> "+colF+ligF);
		

		int comp=ligF-ligD;
		int comp2= (int)colF-(int)colD;
		int cpt=0;

		if (Math.abs(comp)!=Math.abs(comp2)){System.out.println("erreur : mouvement non autorisé");return false;}

		if (comp>0 && comp2>0) 
		{
			for (int i=ligD; i<ligF; i++)
			{
				for (int k=0; k<tab.length;k++)
				{

					if (tab[k].getLig()==i && (int)tab[k].getCol()==(char)((int)colD+cpt) && tab[k] != this)
					{
						System.out.println("erreur :  "+tab[k].getClass().getName()+" sur le chemin en "+tab[k].getCol()+tab[k].getLig());
						return false;
					}
				}
				cpt++;
			}
		}

		cpt=0;
		if (comp>0 && comp2<0) 
		{
			for (int i=ligD; i<ligF; i++)
			{
				for (int k=0; k<tab.length;k++)
				{
					if (tab[k].getLig()==i && tab[k].getCol()==(char)((int)colD-cpt) && tab[k] != this)
					{
						System.out.println("erreur :  "+tab[k].getClass().getName()+" sur le chemin en "+tab[k].getCol()+tab[k].getLig());
						return false;
					}

					
				}
				cpt++;
			}
		}

		cpt=0;
		if (comp<0 && comp2>0) 
		{
			for (int i=ligD; i>ligF; i--)
			{
				for (int k=0; k<tab.length;k++)
				{
					if (tab[k].getLig()==i && (int)tab[k].getCol()==(char)((int)colD+cpt) && tab[k] != this )
					{
						System.out.println("erreur :  "+tab[k].getClass().getName()+" sur le chemin en "+tab[k].getCol()+tab[k].getLig());
						return false;
					}
				}
				cpt++;
			}
		}
		
		cpt=0;
		if (comp<0 && comp2<0) 
		{
			for (int i=ligD; i>ligF; i--)
			{
				for (int k=0; k<tab.length;k++)
					if (tab[k].getLig()==i && (int)tab[k].getCol()==(char)((int)colD-cpt) && tab[k]!=this)
					{
						System.out.println("erreur :  "+tab[k].getClass().getName()+" sur le chemin en "+tab[k].getCol()+tab[k].getLig());
						return false;
					}
				cpt++;
			}
		}

		for (int i=0; i<tab.length;i++)
		{
			if (tab[i].getLig()==ligF && tab[i].getCol()==colF)
			{
				if (this.manger(this, tab[i])){return true;}
				System.out.println("erreur : il y a deja une piece");
				return false;
			}
				
		}

		super.setPosition(ligF,colF);
		return true;
		


	}
}