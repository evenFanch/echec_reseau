package src.Ihm;

import src.Metier.*;
import src.Controleur;
import src.Ihm.ChangerPiece;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoard extends JFrame implements ActionListener
{
	private JLabel Tour;

	private JPanel panelD;
	private JPanel boardPanel;
	private JPanel colPanel;
	private JPanel ligPanel;

	private JButton[][] boardSquares;
	private Controleur ctrl;
	private ChangerPiece cp;

	private boolean clique;
	private int ligD, ligF;
	private char colD, colF;

	private final Color MARRON = new Color(240, 195, 128);
	private final Color BEIGE = new Color(109, 62, 23);

	public ChessBoard(Controleur ctrl)
	{

		this.ctrl = ctrl;
		this.clique = false;
		this.Tour = new JLabel(String.format("%-10.20s", "Tour des Blancs"), JLabel.CENTER);

		this.ligD = 0;
		this.ligF = 0;
		this.colD = 'Z';
		this.colF = 'Z';

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 800); // Set the size of the frame
		this.setTitle("Jeux d'Echec"); // Set the title of the frame
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Create the PiecePanel
		// piecePanel = new PiecePanel();
		// frame.add(piecePanel, BorderLayout.CENTER);

		this.colPanel = new JPanel(new GridLayout(1, 9));
		this.ligPanel = new JPanel(new GridLayout(8, 1));
		this.boardPanel = new JPanel(new GridLayout(8, 8));
		this.panelD = new JPanel(new GridLayout(8, 1));

		this.add(this.colPanel, BorderLayout.SOUTH);
		this.add(this.ligPanel, BorderLayout.WEST);
		this.add(this.boardPanel, BorderLayout.CENTER);
		this.add(this.panelD, BorderLayout.EAST);

		this.boardSquares = new JButton[8][8];

		this.colPanel.setBackground(MARRON);
		this.ligPanel.setBackground(MARRON);

		Tour.setFont(new Font("Arial", Font.BOLD, 24));
		this.panelD.setBackground(Color.WHITE);
		Tour.setForeground(Color.BLACK);
		Tour.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Tour.setPreferredSize(Tour.getPreferredSize());

		String[] pieces = { "To", "Ca", "Fo", "Re", "Ro", "Fo", "Ca", "To" }; // Letters
		// representing
		// the
		// pieces
		JLabel temp;

		for (int i = pieces.length; i >= 1; i--)
		{
			System.out.println(i);
			temp = new JLabel("", JLabel.CENTER);
			temp.setIcon(new ImageIcon("./src/images/" + i + ".png"));
			this.ligPanel.add(temp);
		}

		for (int i = 0; i < pieces.length; i++)
		{
			temp = new JLabel("", JLabel.CENTER);
			temp.setIcon(new ImageIcon("./src/images/" + (char) ('A' + i) + ".png"));
			this.colPanel.add(temp);
		}

		this.colPanel.add(new JLabel(""));
		this.panelD.add(this.Tour);

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				String sImage = "src/images/";
				boardSquares[i][j] = new JButton();
				boardPanel.add(boardSquares[i][j]);

				if ((i + j) % 2 == 0)
				{
					boardSquares[i][j].setBackground(this.BEIGE);
				}
				else
				{
					boardSquares[i][j].setBackground(this.MARRON);
				}

				switch (i)
				{
				case 0 -> {
					boardSquares[i][j].setIcon(new ImageIcon(sImage + pieces[j] + "B.png"));
				}
				case 1 -> {
					boardSquares[i][j].setIcon(new ImageIcon(sImage + "PiB.png"));
				}
				case 6 -> {
					boardSquares[i][j].setIcon(new ImageIcon(sImage + "PiN.png"));
				}
				case 7 -> {
					boardSquares[i][j].setIcon(new ImageIcon(sImage + pieces[j] + "N.png"));
				}
				}

				boardSquares[i][j].addActionListener(this);
			}
		}
		this.pack();
		this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		

		for (int i = 0; i < this.boardSquares.length; i++)
		{
			for (int j = 0; j < this.boardSquares.length; j++)
			{
				if (e.getSource() == this.boardSquares[i][j])
				{
					
					JButton b = this.boardSquares[i][j];
					if (!clique)
					{
						this.ligD = this.boardSquares.length - i;
						this.colD = (char) ('A' + j);
						this.clique = true;

					}
					else
					{
						this.ligF = this.boardSquares.length - i;
						this.colF = (char) ('A' + j);
						this.clique = false;
						this.ctrl.deplacer(this.ligD, this.colD, this.ligF, this.colF);

						

						this.IhmMaj();

						String msg = "Tour des ";
						if (this.ctrl.metier().getTourBlanc())
						{
							Tour.setForeground(Color.BLACK);
							this.panelD.setBackground(Color.WHITE);
							Tour.setText(String.format("%-10.20s", msg + "Blancs"));
						}
						else
						{
							Tour.setForeground(Color.WHITE);
							this.panelD.setBackground(Color.BLACK);
							Tour.setText(String.format("%-10.20s", msg + "Noirs"));
						}

					}

				}
			}
		}
		
		if (this.ctrl.changer())
		{
			if ((this.ctrl.metier().getTourBlanc() && this.ctrl.getCouleurJ().equals("Blanc")) || (!this.ctrl.metier().getTourBlanc() && this.ctrl.getCouleurJ().equals("Noir")))
			{
				this.cp = new ChangerPiece(this.ctrl);
				this.IhmMaj();
			}
		}
	}

	public String changerPiece()
	{
		System.out.println(this.cp.getChange());
		return this.cp.getChange();
	}

	public void IhmMaj()
	{
		

		boolean vide = true;

		for (int i = this.boardSquares.length - 1; i >= 0; i--)
		{
			for (int j = 0; j < this.boardSquares.length; j++)
			{
				for (int k = 0; k < this.ctrl.getTabPiece().length; k++)
				{
					if (this.ctrl.getTabPiece()[k].getLig() == this.boardSquares.length - i
							&& this.ctrl.getTabPiece()[k].getCol() == (char) ('A' + j))
					{
						vide = false;
						boardSquares[i][j].setIcon(
								new ImageIcon("src/images/" + this.ctrl.getTabPiece()[k].getType().substring(0, 2)
										+ Character.toUpperCase(this.ctrl.getTabPiece()[k].getCoul()) + ".png"));
					}
				}
				if (vide)
				{
					if (boardSquares[i][j].getBackground().equals(this.BEIGE))
						boardSquares[i][j].setIcon(new ImageIcon("src/images/Noir.png"));
					else
						boardSquares[i][j].setIcon(new ImageIcon("src/images/Blanc.png"));
				}

				vide = true;

			}
		}
		System.out.println(this.ctrl.metier().toString(this.ctrl.getTabPiece()));

		this.repaint();
	}

}