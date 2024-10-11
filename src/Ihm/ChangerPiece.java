package src.Ihm;

import src.Metier.*;
import src.Ihm.ChessBoard;
import src.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangerPiece extends JFrame implements ActionListener
{
	private JPanel 			panelPiece	  ;
	private JButton[] 		boutonsPiece;
	private Controleur 		ctrl;
	private  String	pieceChangement;

	public ChangerPiece(Controleur ctrl)
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 200);
		this.ctrl=ctrl;

		this.pieceChangement="Reine";

		this.boutonsPiece = new JButton[4];

		this.panelPiece = new JPanel(new GridLayout(1, 4));
		this.add(this.panelPiece);

		Piece[] tab = this.ctrl.getTabPiece();


		for (int i=0; i<tab.length; i++)
		{
			if (tab[i].getType().equals("Pion") && (tab[i].getLig()==8 || tab[i].getLig()==1))
			{

				String[] strTab = {"src/images/Ca"+Character.toUpperCase(tab[i].getCoul())+".png","src/images/Fo"+Character.toUpperCase(tab[i].getCoul())+".png","src/images/Re"+Character.toUpperCase(tab[i].getCoul())+".png","src/images/To"+Character.toUpperCase(tab[i].getCoul())+".png"};

				
				for (int j=0; j<4; j++)
				{
					this.boutonsPiece [j]=new JButton();
					this.boutonsPiece [j].setIcon(new ImageIcon(strTab[j]));
					this.panelPiece.add(this.boutonsPiece [j]);
					this.boutonsPiece [j].addActionListener(this);
				}
			}
		}
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for (int i =0; i<4; i++)
		{
			if (e.getSource()==this.boutonsPiece[i])
			{
				switch(i)
				{
					case 0: this.pieceChangement="Cavalier" ;break;
					case 1: this.pieceChangement="Fou"		;break;
					case 2: this.pieceChangement="Reine"	;break;
					case 3: this.pieceChangement="Tour"		;break;
				}
				
			}
			

		}
		this.ctrl.changerPiece();
		this.hide();
	}

	public String getChange (){return this.pieceChangement;}
}

	