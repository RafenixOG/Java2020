package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetrePrincipale extends JFrame{
	private Container cont;
	private PanneauAccueil panneauAccueil;
	private JMenuBar barreMenu;
	private JMenu options, aide;
	private JMenuItem encoder, lister, supprimer, rechercherTypePc, rechercherParAnn�e, support, information;
	
	public FenetrePrincipale () {
		
		super("Projet s�rie 5");
		setBounds(600,300,500,500);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		barreMenu = new JMenuBar();
		options = new JMenu("Options");
		aide = new JMenu("Aide");
		barreMenu.add(options);
		barreMenu.add(aide);
		
		encoder = new JMenuItem("Encoder");
		lister = new JMenuItem("Lister");
		supprimer = new JMenuItem("Supprimer");
		rechercherTypePc = new JMenuItem("Rechercher Type PC");
		rechercherParAnn�e = new JMenuItem("Rechercher par ann�e");
		support = new JMenuItem("Support");
		information = new JMenuItem("Information");
		
		options.add(encoder);
		options.add(lister);
		options.add(supprimer);
		options.add(rechercherTypePc);
		options.add(rechercherParAnn�e);
		aide.add(support);
		aide.add(information);
		
		
		
		panneauAccueil = new PanneauAccueil();
		cont = getContentPane();
		cont.add(panneauAccueil);
		
		setJMenuBar(barreMenu);
		setVisible(true);
	}
}
