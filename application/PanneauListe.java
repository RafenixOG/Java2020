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
	private JPanel panneauTable;
	private JTable table;
	private JScrollPane defilant;
	private GridBagConstraints comboGBC, panneauTableGBC;
    private FenetrePrincipale fenetrePrincipale;
	
	public PanneauListe(FenetrePrincipale fenetrePrincipale) {
		
		this.fenetrePrincipale = fenetrePrincipale;
		
		setLayout(new GridBagLayout());
		
		Gestionnaire item = new Gestionnaire();
				
		comboGBC = new GridBagConstraints();
		comboGBC.weightx = 0.5;
		comboGBC.gridx = 2;
		comboGBC.gridy = 0;
		comboGBC.insets = new Insets(0,0,0,20);
		comboGBC.anchor = GridBagConstraints.NORTHEAST;
		
		panneauTableGBC = new GridBagConstraints();
		panneauTableGBC.fill = GridBagConstraints.HORIZONTAL;
		panneauTableGBC.ipady = 410;	//taille min tant que j'ai pas trouv� comment redimensionn� le JScrollPane
		panneauTableGBC.weightx = 0.5;
		panneauTableGBC.gridx = 0;
		panneauTableGBC.gridy = 1;
		panneauTableGBC.gridwidth = 3;
		
		try {
			String sqlToutesTable = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='dbinstallations'";
			PreparedStatement prepStatToutesTables = fenetrePrincipale.getConnection().prepareStatement(sqlToutesTable);
			tableDB = AccessBDGen.creerListe1Colonne(prepStatToutesTables);
			comboDB = new JComboBox(tableDB);
			comboDB.addItemListener(item);
			add(comboDB, comboGBC);
			
			//Inintialisation de la table par d�faut
			String sqlDefaut = "select * from " + comboDB.getSelectedItem();
			PreparedStatement prepStatDefaut = fenetrePrincipale.getConnection().prepareStatement(sqlDefaut);
			TableModelGen tableDefaut = AccessBDGen.creerTableModel(prepStatDefaut);
			table = new JTable(tableDefaut);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			defilant = new JScrollPane(table);
			defilant.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			panneauTable = new JPanel();
			panneauTable.add(defilant);
			
			add(panneauTable, panneauTableGBC);
			
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage()); 
		}
		
		setVisible(true);
	}
	
	public void setPanneauSP(JPanel panneauSP) {
		this.panneauTable = panneauSP;
	}
	
	private class Gestionnaire implements ItemListener {
		
		public void itemStateChanged(ItemEvent e) {
			try {
				if(e.getSource() == comboDB) {
					String sqlInstruction = "select * from " + comboDB.getSelectedItem();
					PreparedStatement prepStat = fenetrePrincipale.getConnection().prepareStatement(sqlInstruction);
					TableModelGen tableDemande = AccessBDGen.creerTableModel(prepStat);
					table = new JTable(tableDemande);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					defilant.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					panneauTable.removeAll();
					panneauTable.add(defilant);
    				panneauTable.validate();
    				panneauTable.repaint();
	    		}
			
			}
			catch(SQLException e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
}
