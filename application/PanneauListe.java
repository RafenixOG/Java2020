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
	private JPanel panneauSP;
	private JTable table;
	private JScrollPane tableSP;
	private GridBagConstraints comboGBC, panneauSP_GBC;
	
	public PanneauListe() {
		setLayout(new GridBagLayout());
		
		GestionnaireAction item = new GestionnaireAction();
		
		panneauSP = new JPanel();
		
		comboGBC = new GridBagConstraints();
		//comboGBC.fill = GridBagConstraints.HORIZONTAL;
		comboGBC.weightx = 0.5;
		comboGBC.gridx = 2;
		comboGBC.gridy = 0;
		comboGBC.insets = new Insets(0,0,0,20);
		comboGBC.anchor = GridBagConstraints.NORTHEAST;
		
		panneauSP_GBC = new GridBagConstraints();
		panneauSP_GBC.fill = GridBagConstraints.HORIZONTAL;
		panneauSP_GBC.ipady = 350;
		panneauSP_GBC.weightx = 0.5;
		panneauSP_GBC.gridx = 0;
		panneauSP_GBC.gridy = 1;
		panneauSP_GBC.gridwidth = 3;
		
		try {
			Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
			String sqlToutesTable = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='dbinstallations'";
			PreparedStatement prepStatToutesTables = connection.prepareStatement(sqlToutesTable);
			tableDB = AccessBDGen.creerListe1Colonne(prepStatToutesTables);
			comboDB = new JComboBox(tableDB);
			comboDB.setSelectedIndex(4);
			comboDB.addItemListener(item);
			add(comboDB, comboGBC);
			
			//Inintialisation de la table par défaut
			String sqlDefaut = "select * from installation";
			PreparedStatement prepStatDefaut = connection.prepareStatement(sqlDefaut);
			TableModelGen tableDefaut = AccessBDGen.creerTableModel(prepStatDefaut);
			table = new JTable(tableDefaut);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableSP = new JScrollPane(table);
			tableSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			tableSP.setMaximumSize(new Dimension(10,10));
			
			panneauSP.add(tableSP);
			
			add(panneauSP, panneauSP_GBC);
			
			
		}
		catch(SQLException e) {
			
		}
		
		setVisible(true);
	}
	
	private class GestionnaireAction implements ItemListener {
		
		public void itemStateChanged(ItemEvent e) {
			try {
				Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
				if(e.getSource() == comboDB) {
					String sqlInstruction = "select * from " + comboDB.getSelectedItem();
					PreparedStatement prepStat = connection.prepareStatement(sqlInstruction);
					TableModelGen tableDemande = AccessBDGen.creerTableModel(prepStat);
					table = new JTable(tableDemande);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					tableSP = new JScrollPane(table);
					panneauSP.removeAll();
					panneauSP.add(tableSP);
    				panneauSP.validate();
    				panneauSP.repaint();
	    		}
			
			}
			catch(SQLException e1) {
				
			}
		}
	}
}
