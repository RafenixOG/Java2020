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
import java.util.ArrayList;

import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class PanneauRechercheAnnee extends JPanel {
	private JComboBox anneeCB;
	private JTable annee, table;
	private ArrayList<String> anneeSection;
	private JPanel panneauTable;
	private JScrollPane defilant;
	private GridBagConstraints comboGBC, panneauTableGBC;
	
	public PanneauRechercheAnnee() {
		
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
			String sqlAnneeSection = "select annee, codesection from anneeetude";
			PreparedStatement prepStatAnneeSection = connection.prepareStatement(sqlAnneeSection);
			TableModelGen tableAnneeSection = AccessBDGen.creerTableModel(prepStatAnneeSection);
			annee = new JTable(tableAnneeSection);
			anneeSection = new ArrayList<>();
			for(int i = 0; i < annee.getRowCount(); i++) {
				anneeSection.add(annee.getValueAt(i, 0).toString() + annee.getValueAt(i, 1));
			}
			anneeCB = new JComboBox(anneeSection.toArray());
			anneeCB.addItemListener(item);
			add(anneeCB, comboGBC);
			
			String sqlDefaut = "select distinct installation.* from installation inner join software on "
					+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
					+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
					+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
					+ "1 and anneeetude.CodeSection = \"TI\"";
			PreparedStatement prepStatDefaut = connection.prepareStatement(sqlDefaut);
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
	
	private class Gestionnaire implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			try {
				Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
				if(e.getSource() == anneeCB) {
					String sqlInstruction = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ annee.getValueAt(anneeCB.getSelectedIndex(), 0).toString() + " and anneeetude.CodeSection = \"" 
							+ annee.getValueAt(anneeCB.getSelectedIndex(), 1) + "\"";
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
