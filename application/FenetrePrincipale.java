package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FenetrePrincipale extends JFrame{
	
	private Container cont;
	private PanneauAccueil panneauAccueil;
	private PanneauEncodage panneauEncodage;
	private PanneauSupport panneauSupport;
	private PanneauInfo panneauInfo;
	private PanneauListe panneauListe;
	private PanneauRechercheAnnee panneauRechercheAnnee;
	private PanneauRecherchePc panneauRecherchePc;
	private PanneauSuppression panneauSuppression;
	private JMenuBar barreMenu;
	private JMenu options, aide;
	private JMenuItem encoder, lister, supprimer, rechercherTypePc, rechercherParAnnee, support, information;
	
	public FenetrePrincipale () {
		
		super("Projet série 5");
		setBounds(600,300,500,500);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		GestionnaireAction g = new GestionnaireAction();
		
		barreMenu = new JMenuBar();
		options = new JMenu("Options");
		aide = new JMenu("Aide");
		barreMenu.add(options);
		barreMenu.add(aide);
		
		encoder = new JMenuItem("Encoder");
		lister = new JMenuItem("Lister");
		supprimer = new JMenuItem("Supprimer");
		rechercherTypePc = new JMenuItem("Rechercher par type de PC");
		rechercherParAnnee = new JMenuItem("Rechercher par année");
		support = new JMenuItem("Support");
		information = new JMenuItem("Information");
		
		options.add(encoder);
		options.add(lister);
		options.add(supprimer);
		options.add(rechercherTypePc);
		options.add(rechercherParAnnee);
		aide.add(support);
		aide.add(information);
		
		encoder.addActionListener(g);
		lister.addActionListener(g);
		supprimer.addActionListener(g);
		rechercherTypePc.addActionListener(g);
		rechercherParAnnee.addActionListener(g);
		support.addActionListener(g);
		information.addActionListener(g);
		
		
		panneauAccueil = new PanneauAccueil();
		panneauEncodage = new PanneauEncodage();
		panneauSupport = new PanneauSupport();
		panneauInfo = new PanneauInfo();
		panneauListe = new PanneauListe();
		panneauRechercheAnnee = new PanneauRechercheAnnee();
		panneauRecherchePc = new PanneauRecherchePc();
		panneauSuppression = new PanneauSuppression();
		cont = getContentPane();
		cont.add(panneauAccueil);
		
		setJMenuBar(barreMenu);
		setVisible(true);
		
	}
	
	private class GestionnaireAction implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == encoder) {
				cont.removeAll();
                cont.add(panneauEncodage);
                cont.validate();
                cont.repaint();
			}
			
			if (event.getSource() == support) {
				cont.removeAll();
                cont.add(panneauSupport);
                cont.validate();
                cont.repaint();
			}
			
			if (event.getSource() == information) {
				cont.removeAll();
                cont.add(panneauInfo);
                cont.validate();
                cont.repaint();
			}
			
			if (event.getSource() == lister) {
				cont.removeAll();
                cont.add(panneauListe);
                cont.validate();
                cont.repaint();
			}
		}
	}
}
