package application;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class PanneauRecherchePc extends JPanel {
	private JComboBox descriptionCB;
	private Object[] description;
	private JScrollPane defilant;
	private JTable table;
	private JPanel panneauTable;
	private GridBagConstraints comboGBC, panneauTableGBC;
	
	public PanneauRecherchePc() {
		
		setLayout(new GridBagLayout());
		
		Gestionnaire item = new Gestionnaire();
		
		comboGBC = new GridBagConstraints();
		comboGBC.weightx = 0.5;
		comboGBC.gridx = 2;
		comboGBC.gridy = 0;
		comboGBC.gridwidth = 1;
		comboGBC.insets = new Insets(0,0,0,20);
		comboGBC.anchor = GridBagConstraints.NORTHEAST;
		
		panneauTableGBC = new GridBagConstraints();
		panneauTableGBC.fill = GridBagConstraints.HORIZONTAL;
		panneauTableGBC.ipady = 410;	//taille min tant que j'ai pas trouvé comment redimensionné le JScrollPane
		panneauTableGBC.weightx = 0.5;
		panneauTableGBC.gridx = 0;
		panneauTableGBC.gridy = 1;
		panneauTableGBC.gridwidth = 3;
		
		try {
			Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
			
			String sqlDescription = "select Description from typepc";
			PreparedStatement prepStatDescription = connection.prepareStatement(sqlDescription);
			description = AccessBDGen.creerListe1Colonne(prepStatDescription);
			descriptionCB = new JComboBox(description);
			descriptionCB.addItemListener(item);
			add(descriptionCB, comboGBC);
			
			String sqlDefaut = "select distinct software.* from software inner join softwarepreinstalle on software.CodeSoftware"
					+ "= softwarepreinstalle.CodeSoftware inner join typepc on softwarepreinstalle.IdTypePC = typepc.IdTypePC "
					+ "where software.CleInstallation is null and typepc.Description = \""+ descriptionCB.getSelectedItem() +"\"";
			PreparedStatement prepStatDefaut = connection.prepareStatement(sqlDefaut);
			TableModelGen tableDefaut = AccessBDGen.creerTableModel(prepStatDefaut);
			table = new JTable(tableDefaut);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			defilant = new JScrollPane(table);
			
			panneauTable = new JPanel();
			panneauTable.add(defilant);
			add(panneauTable, panneauTableGBC);
			
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		setVisible(true);
	}
	
	private class Gestionnaire implements ItemListener {
		
		public void itemStateChanged(ItemEvent e) {
			try {
				Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
				if(e.getSource() == descriptionCB) {
					String sqlInstruction = "select distinct software.* from software inner join softwarepreinstalle on software.CodeSoftware"
							+ "= softwarepreinstalle.CodeSoftware inner join typepc on softwarepreinstalle.IdTypePC = typepc.IdTypePC "
							+ "where software.CleInstallation is null and typepc.Description = \"" + descriptionCB.getSelectedItem() +"\"";
					PreparedStatement prepStat = connection.prepareStatement(sqlInstruction);
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
