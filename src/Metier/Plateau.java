package src.Metier;

import src.Metier.*;

import iut.algo.*;

public class Plateau
{
	private Piece[] tabPiece;
	private boolean tourBlanc;

	public Plateau ()
	{
		this.tabPiece=new Piece[32];

		boolean 	fin=true ;
		String		rep1=""  ;
		String		rep2=""	 ;
		this.tourBlanc=true;

		for (int i=0; i<8; i++)
			this.tabPiece[i]=new Pion (7, (char)((int)'A'+i), 'b', "Pion");
		
		for (int i=0; i<8; i++)
			this.tabPiece[8+i]=new Pion (2, (char)((int)'A'+i), 'n', "Pion");
		
		this.tabPiece[16]=new Tour (8,'A','b', "Tour");
		this.tabPiece[17]=new Tour (8,'H','b', "Tour");
		this.tabPiece[18]=new Tour (1,'A','n', "Tour");
		this.tabPiece[19]=new Tour (1,'H','n', "Tour");

		this.tabPiece[20]=new Cavalier (8,'B','b', "Cavalier");
		this.tabPiece[21]=new Cavalier (8,'G','b', "Cavalier");
		this.tabPiece[22]=new Cavalier (1,'B','n', "Cavalier");
		this.tabPiece[23]=new Cavalier (1,'G','n', "Cavalier");

		this.tabPiece[24]=new Roi (1,'E','n', "Roi");
		this.tabPiece[25]=new Roi (8,'E','b', "Roi");
		this.tabPiece[26]=new Reine(1,'D','n', "Reine");
		this.tabPiece[27]=new Reine(8,'D','b', "Reine");

		this.tabPiece[28]=new Fou (1,'C','n', "Fou");
		this.tabPiece[29]=new Fou (1,'F','n', "Fou");
		this.tabPiece[30]=new Fou (8,'F','b', "Fou");
		this.tabPiece[31]=new Fou (8,'C','b', "Fou");
	
	}

	public Piece[] getPieces(){return this.tabPiece;}

	public boolean deplacer(int ligD, char colD,int ligF,char colF)
	{
		if (!this.finF()){System.out.println("--------------------------------------C'est finis ------------------------------------------");return false;}
		for(int i=0; i<this.tabPiece.length; i++)
		{
			if (colD==this.tabPiece[i].getCol() && ligD==this.tabPiece[i].getLig())
			{
				if (this.tourBlanc && this.tabPiece[i].getCoul()=='b' || !this.tourBlanc && this.tabPiece[i].getCoul()=='n')
				{
					if(this.tabPiece[i].deplacer(this.tabPiece[i].getLig(), this.tabPiece[i].getCol(), ligF, colF, this.tabPiece))
					{
						this.tourBlanc= !this.tourBlanc;
						return true;
					}
						
				}
				else

					System.out.println("Ce n'est pas a votre tour");
			}
		}
		return false;
	}

	public boolean changer ()
	{
		for (int i=0; i<this.tabPiece.length; i++)
		{
			if (this.tabPiece[i].getType().equals("Pion") && (this.tabPiece[i].getLig()==1 || this.tabPiece[i].getLig()==8) )
			{
				return true;
			}
		}
		return false;
	}

	public void changerPiece (String str)
	{
		int 	tempLig;
		char 	tempCol;

		System.out.println(str);
		if (str==null){return;}
		Piece 	 nvPiece=null	;
		
		for (int i=0; i<this.tabPiece.length; i++)
		{
			if (this.tabPiece[i].getType().equals("Pion") && (this.tabPiece[i].getLig()==1 || this.tabPiece[i].getLig()==8) )
			{
				tempLig=this.tabPiece[i].getLig();
				tempCol=this.tabPiece[i].getCol();

				if (str.equals("Tour"))
					nvPiece= new Tour (tempLig, tempCol,this.tabPiece[i].getCoul(), "Tour");

				if (str.equals("Reine"))
					nvPiece= new Reine (tempLig, tempCol,this.tabPiece[i].getCoul(), "Reine");
				
				if (str.equals("Cavalier"))
					nvPiece= new Cavalier (tempLig, tempCol,this.tabPiece[i].getCoul(), "Cavalier");

				if (str.equals("Fou"))
					nvPiece= new Fou (tempLig, tempCol,this.tabPiece[i].getCoul(), "Fou");
				
				this.tabPiece[i].setPosition(10,'Z');
				this.tabPiece[i]=nvPiece;
				this.tourBlanc=!tourBlanc;
			}
		}

		
	}

	public String toString(Piece[] tab)
	{
		String res="\n    +----+----+----+----+----+----+----+----+\n";
		boolean vide=true;

		for (int i=8; i>=1; i--)
		{
			for (int j=0; j<8; j++)
			{
				if(j==0){res+= i+"   ";}
				for (int k=0; k<tab.length; k++)
				{
					if (tab[k].getLig()==i && tab[k].getCol()==(char)((int)'A'+j))
					{
						res+="+ "+tab[k].getType().substring(0,2)+" ";
						vide=false;
					}
				}
				if (vide){res+="+    ";}
				vide=true;
				
			}
			res+="+\n    +----+----+----+----+----+----+----+----+\n";
		}
		res+="      A    B    C    D    E    F    G    H\n";
		return res;

	}

	public boolean finF()
	{
		for (int i=0; i<this.tabPiece.length; i++)
		{
			if (tabPiece[i].getType().equals("Roi"))
				if (tabPiece[i].getLig()==10 && tabPiece[i].getCol()=='Z')
				{
					System.out.print(toString(tabPiece));
					if (tabPiece[i].getCoul()=='b')
						System.out.println("Les noirs ont gagnés");
					else
						System.out.println("Les blancs ont gagnés");
					return false;
				}
					
		}

		return true;
	}

	public boolean getTourBlanc(){return this.tourBlanc;}
}