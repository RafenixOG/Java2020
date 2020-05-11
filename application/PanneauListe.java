package application;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;


public class PanneauListe extends JPanel{
	private JComboBox comboDB;
	private Object[] tableDB;
	private JPanel panneauListe;
	private JTable anneeetude, editeur, famillesoftware, fournisseur, installation, os, professeur, responsablereseaux, section, software, softwarepreinstalle, typepc, utilisationsoftware;
	private JScrollPane anneeetudeSP, editeurSP, famillesoftwareSP, fournisseurSP, installationSP, osSP, professeurSP, responsablereseauxSP, sectionSP, softwareSP, softwarepreinstalleSP, typepcSP, utilisationsoftwareSP;
	
	public PanneauListe() {
		setLayout(new FlowLayout());
		
		GestionnaireAction item = new GestionnaireAction();
		
		try {
			Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
			String sqlToutesTable = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='dbinstallations'";
			PreparedStatement prepStatToutesTables = connection.prepareStatement(sqlToutesTable);
			tableDB = AccessBDGen.creerListe1Colonne(prepStatToutesTables);
			comboDB = new JComboBox(tableDB);
			comboDB.setSelectedIndex(4);
			comboDB.addItemListener(item);
			add(comboDB);
			
			//Inintialisation de la table par défaut
			String sqlDefaut = "select * from installation";
			PreparedStatement prepStatDefaut = connection.prepareStatement(sqlDefaut);
			TableModelGen tableDefaut = AccessBDGen.creerTableModel(prepStatDefaut);
			installation = new JTable(tableDefaut);
			installation.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			installationSP = new JScrollPane(installation);
			
			panneauListe = new JPanel();
			panneauListe.add(installationSP);
			
			add(panneauListe);
			
			
		}
		catch(SQLException e) {
			
		}
		
		setVisible(true);
	}
	
	private class GestionnaireAction implements ItemListener {
		
		public void itemStateChanged(ItemEvent e) {
			try {
				Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
	    		switch(comboDB.getSelectedIndex()) {
	    		case 0:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlAnneeEtude = "select * from anneeetude";
	    			PreparedStatement prepStatAnneeEtude = connection.prepareStatement(sqlAnneeEtude);
	    			TableModelGen tableAnneeEtude = AccessBDGen.creerTableModel(prepStatAnneeEtude);
	    			anneeetude = new JTable(tableAnneeEtude);
	    			anneeetude.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			anneeetudeSP = new JScrollPane(anneeetude);
    				panneauListe.removeAll();
    				panneauListe.add(anneeetudeSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
    				}
	    		case 1:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlEditeur = "select * from editeur";
	    			PreparedStatement prepStatEditeur = connection.prepareStatement(sqlEditeur);
	    			TableModelGen tableEditeur = AccessBDGen.creerTableModel(prepStatEditeur);
	    			editeur = new JTable(tableEditeur);
	    			editeur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			editeurSP = new JScrollPane(editeur);
    				panneauListe.removeAll();
    				panneauListe.add(editeurSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
	    		}
	    		case 2:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlFamilleSoftware = "select * from famillesoftware";
	    			PreparedStatement prepStatFamilleSoftware = connection.prepareStatement(sqlFamilleSoftware);
	    			TableModelGen tableFamilleSoftware = AccessBDGen.creerTableModel(prepStatFamilleSoftware);
	    			famillesoftware = new JTable(tableFamilleSoftware);
	    			famillesoftware.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			famillesoftwareSP = new JScrollPane(famillesoftware);
    				panneauListe.removeAll();
    				panneauListe.add(famillesoftwareSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
	    		}
	    		case 3:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlFournisseur = "select * from fournisseur";
	    			PreparedStatement prepStatFournisseur = connection.prepareStatement(sqlFournisseur);
	    			TableModelGen tableFournisseur = AccessBDGen.creerTableModel(prepStatFournisseur);
	    			fournisseur = new JTable(tableFournisseur);
	    			fournisseur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			fournisseurSP = new JScrollPane(fournisseur);
    				panneauListe.removeAll();
    				panneauListe.add(fournisseurSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
	    		}
	    		case 4:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlInstallation = "select * from installation";
	    			PreparedStatement prepStatInstallation = connection.prepareStatement(sqlInstallation);
	    			TableModelGen tableInstallation = AccessBDGen.creerTableModel(prepStatInstallation);
	    			installation = new JTable(tableInstallation);
	    			installation.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			installationSP = new JScrollPane(installation);
	    			panneauListe.removeAll();
    				panneauListe.add(installationSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
	    		}
	    		case 5:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlOs = "select * from os";
	    			PreparedStatement prepStatOs = connection.prepareStatement(sqlOs);
	    			TableModelGen tableOs = AccessBDGen.creerTableModel(prepStatOs);
	    			os = new JTable(tableOs);
	    			os.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			osSP = new JScrollPane(os);
    				panneauListe.removeAll();
    				panneauListe.add(osSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
	    		}
	    		case 6:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlProfesseur = "select * from professeur";
	    			PreparedStatement prepStatProfesseur = connection.prepareStatement(sqlProfesseur);
	    			TableModelGen tableProfesseur = AccessBDGen.creerTableModel(prepStatProfesseur);
	    			professeur = new JTable(tableProfesseur);
	    			professeur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			professeurSP = new JScrollPane(professeur);
    				panneauListe.removeAll();
    				panneauListe.add(professeurSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
	    		}
	    		case 7:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlResponsableReseaux = "select * from responsablereseaux";
	    			PreparedStatement prepStatResponsableReseaux = connection.prepareStatement(sqlResponsableReseaux);
	    			TableModelGen tableResponsableReseaux = AccessBDGen.creerTableModel(prepStatResponsableReseaux);
	    			responsablereseaux = new JTable(tableResponsableReseaux);
	    			responsablereseaux.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			responsablereseauxSP = new JScrollPane(responsablereseaux);
    				panneauListe.removeAll();
    				panneauListe.add(responsablereseauxSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
    	    	}
	    		case 8:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlSection = "select * from section";
	    			PreparedStatement prepStatSection = connection.prepareStatement(sqlSection);
	    			TableModelGen tableSection = AccessBDGen.creerTableModel(prepStatSection);
	    			section = new JTable(tableSection);
	    			section.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			sectionSP = new JScrollPane(section);
    				panneauListe.removeAll();
    				panneauListe.add(sectionSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
    	    	}
	    		case 9:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlSoftware = "select * from software";
	    			PreparedStatement prepStatSoftware = connection.prepareStatement(sqlSoftware);
	    			TableModelGen tableSoftware = AccessBDGen.creerTableModel(prepStatSoftware);
	    			software = new JTable(tableSoftware);
	    			software.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			softwareSP = new JScrollPane(software);
    				panneauListe.removeAll();
    				panneauListe.add(softwareSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
        	    }
	    		case 10:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlSoftwarePreinstalle = "select * from softwarepreinstalle";
	    			PreparedStatement prepStatSoftwarePreinstalle = connection.prepareStatement(sqlSoftwarePreinstalle);
	    			TableModelGen tableSoftwarePreinstalle = AccessBDGen.creerTableModel(prepStatSoftwarePreinstalle);
	    			softwarepreinstalle = new JTable(tableSoftwarePreinstalle);
	    			softwarepreinstalle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			softwarepreinstalleSP = new JScrollPane(softwarepreinstalle);
    				panneauListe.removeAll();
    				panneauListe.add(softwarepreinstalleSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
        	    }
	    		case 11:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlTypePC = "select * from typepc";
	    			PreparedStatement prepStatTypePC = connection.prepareStatement(sqlTypePC);
	    			TableModelGen tableTypePC = AccessBDGen.creerTableModel(prepStatTypePC);
	    			typepc = new JTable(tableTypePC);
	    			typepc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			typepcSP = new JScrollPane(typepc);
    				panneauListe.removeAll();
    				panneauListe.add(typepcSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
        	    }
	    		case 12:if(e.getStateChange() == ItemEvent.SELECTED) {
	    			String sqlUtilisationSoftware = "select * from utilisationsoftware";
	    			PreparedStatement prepStatUtilisationSoftware = connection.prepareStatement(sqlUtilisationSoftware);
	    			TableModelGen tableUtilisationSoftware = AccessBDGen.creerTableModel(prepStatUtilisationSoftware);
	    			utilisationsoftware = new JTable(tableUtilisationSoftware);
	    			utilisationsoftware.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    			utilisationsoftwareSP = new JScrollPane(utilisationsoftware);
    				panneauListe.removeAll();
    				panneauListe.add(utilisationsoftwareSP);
    				panneauListe.validate();
    				panneauListe.repaint();
    				System.out.println("fait");
    				break;
	    		}
	    		}
			
			}
			catch(SQLException e1) {
				
			}
		}
	}
}
