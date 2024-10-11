package src.Metier;

public class Piece
{
	private int		lig	;
	private char 	col	;
	private char	coul;
	private String	type;

	public Piece (int lig, char col, char coul, String type)
	{
		this.lig=lig;
		this.col=col;
		this.coul=coul;
		this.type=type;
	}

	public int 	getLig() {return this.lig	;}
	public char getCol() {return this.col	;}
	public char getCoul(){return this.coul	;}
	public String getType(){return this.type;}

	public void setPosition(int lig, char col)
	{
		this.lig=lig;
		this.col=col;
	}

	

	public boolean manger (Piece p1, Piece p2)
	{
		if (p1.getType()=="Pion")
		{
			if (p1.getCoul()=='b')
			{
				if (p2.getLig()==p1.getLig()-1 && Math.abs((int)p1.getCol()-(int)p2.getCol())==1)
				{
					p1.setPosition(p2.getLig(), p2.getCol());
					p2.setPosition(10,'Z');
					return true;
				}
			}
			else
				if (p2.getLig()==p1.getLig()+1 && Math.abs((int)p1.getCol()-(int)p2.getCol())==1)
				{
					p1.setPosition(p2.getLig(), p2.getCol());
					p2.setPosition(10,'Z');
					return true;
				}
		}
		else
			if (p1.getCoul() != p2.getCoul() )
			{
				p1.setPosition(p2.getLig(), p2.getCol());
				p2.setPosition(10,'Z');
				return true;
			}
		return false;
	}

	public boolean deplacer(int ligD, char colD,int ligF,char colF, Piece[] tab){return true;}

}