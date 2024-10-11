package src.Metier;

public class Tour extends Piece
{
	public Tour (int lig, char col, char coul, String type)
	{
		super (lig,col, coul, type);
	}

	public boolean deplacer (int ligD, char colD,int ligF,char colF, Piece[] tab)
	{
		System.out.println("("+this.getType()+") "+colD+""+ligD+" --> "+colF+ligF);
		
		for (int i=0; i<tab.length;i++)
		{
			if (tab[i].getLig()==ligF && tab[i].getCol()==colF)
			{
				if (this.manger(this, tab[i])){return true;}
				System.out.println("erreur : il y a deja une piece");
				return false;
			}
		}

		if (!((ligD==ligF && colD!=colF)||(ligD!=ligF && colD==colF))){System.out.println("erreur : mouvement non autorisé");return false;}

		if (ligD>ligF)
			for (int i=ligD; i>ligF; i--)
				for (int k=0; k<tab.length; k++)
					if (tab[k].getLig()==i && tab[k].getCol()==colF && tab[k]!=this)
					{
						System.out.println("erreur :  "+tab[k].getClass().getName()+" sur le chemin en "+tab[k].getCol()+tab[k].getLig());
						return false;
					}
		
		if (ligD<ligF)
			for (int i=ligD; i<ligF; i++)
				for (int k=0; k<tab.length; k++)
					if (tab[k].getLig()==i && tab[k].getCol()==colF && tab[k]!=this)
					{
						System.out.println("erreur :  "+tab[k].getClass().getName()+" sur le chemin en "+tab[k].getCol()+tab[k].getLig());
						return false;
					}
		
		if ((int)colD<(int)colF)
			for (int i=(int)colD; i<(int)colF; i++)
				for (int k=0; k<tab.length; k++)
					if ((int)tab[k].getCol()==i && tab[k].getLig()==ligF && tab[k]!=this)
					{
						System.out.println("erreur :  "+tab[k].getClass().getName()+" sur le chemin en "+tab[k].getCol()+tab[k].getLig());
						return false;
					}
		
		if ((int)colD>(int)colF)
			for (int i=(int)colD; i>(int)colF; i--)
				for (int k=0; k<tab.length; k++)
					if ((int)tab[k].getCol()==i && tab[k].getLig()==ligF && tab[k]!=this)
					{
						System.out.println("erreur :  "+tab[k].getClass().getName()+" sur le chemin en "+tab[k].getCol()+tab[k].getLig());
						return false;
					}


		super.setPosition(ligF,colF);
		return true;
	}
}
