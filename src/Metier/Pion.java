
package src.Metier;

public class Pion extends Piece
{
	public Pion (int lig, char col, char coul, String type)
	{
		super (lig,col, coul, type);
	}

	public boolean deplacer(int ligD, char colD,int ligF,char colF, Piece[] tab)
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

		if (colD!=colF){return false;}

		switch (super.getCoul())
		{
			case 'b' : 
			if (ligD==7)
			{
				if (ligF!=5 && ligF!=6)
				{
					System.out.println("erreur :  non autorisé1");
					return false;
				}
				break;
			}
			if (!(ligF == ligD-1))
			{
				System.out.println("erreur :  non autorisé5");
				return false;
			}
			break;

			case 'n' : if (ligD==2)
						{
							if (ligF >4 || ligF<2)
							{
								System.out.println("erreur :  non autorisé2");
								return false;
							}
						}
						else
							if (!(ligF == ligD+1))
							{
								System.out.println("erreur :  non autorisé3");
								return false;
							}
							break;
		}

		

		super.setPosition(ligF,colF);
		return true;
	}

}