package application;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import java.sql.Connection;

import accessBD.AccessBDGen;


public class FenetrePrincipale extends JFrame{
	
	private Container cont;
	private PanneauAccueil panneauAccueil;
	private PanneauEncodage panneauEncodage;
	private PanneauInfo panneauInfo;
	private PanneauListe panneauListe;
	private PanneauRechercheAnnee panneauRechercheAnnee;
	private PanneauRecherchePc panneauRecherchePc;
	private PanneauSuppression panneauSuppression;
	private JMenuBar barreMenu;
	private JMenu options, aide;
	private JMenuItem encoder, lister, supprimer, rechercherTypePc, rechercherParAnnee, support, information;
	private Connection connection;
	private FenetreSupport fenetreSupport;
	
	public FenetrePrincipale () {
		
		super("Projet sÃ©rie 5");
	
		try {
			connection = AccessBDGen.connecter("DbInstallations", "root", "root");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage()); 
		}
		
		setSize(500,565);	//taille tant que le problème de taille du JScrollPane n'a pas été résolu
		//setBounds(600,200,500,550);
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					getConnection().close();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage()); 
				}
				System.out.println("Connection fermée");
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
		rechercherParAnnee = new JMenuItem("Rechercher par annÃ©e");
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
		panneauEncodage = new PanneauEncodage(this);
		panneauInfo = new PanneauInfo();
		panneauListe = new PanneauListe(this);
		panneauRechercheAnnee = new PanneauRechercheAnnee(this);
		panneauRecherchePc = new PanneauRecherchePc(this);
		panneauSuppression = new PanneauSuppression(this);
		cont = getContentPane();
		cont.add(panneauAccueil);
		
		panneauEncodage.getRetour().addActionListener(g);
		panneauSuppression.getAnnuler().addActionListener(g);
		
		setJMenuBar(barreMenu);
		setVisible(true);
		
	}
	
	public Connection getConnection() {
		return connection;
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
				fenetreSupport = new FenetreSupport();
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
			
			if (event.getSource() == rechercherTypePc) {
				cont.removeAll();
                cont.add(panneauRecherchePc);
                cont.validate();
                cont.repaint();
			}
			
			if (event.getSource() == rechercherParAnnee) {
				cont.removeAll();
                cont.add(panneauRechercheAnnee);
                cont.validate();
                cont.repaint();
      }

			if (event.getSource() == supprimer) {
				cont.removeAll();
                cont.add(panneauSuppression);
                cont.validate();
                cont.repaint();
			}
			
			if(event.getSource() == panneauEncodage.getRetour()) {
				cont.removeAll();
                cont.add(panneauAccueil);
                cont.validate();
                cont.repaint();
			}
			
			if(event.getSource() == panneauSuppression.getAnnuler()) {
				cont.removeAll();
                cont.add(panneauAccueil);
                cont.validate();
                cont.repaint();
			}
		}
	}
}
