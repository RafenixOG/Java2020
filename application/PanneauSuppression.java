package application;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class PanneauSuppression extends JPanel {
	private JLabel labelFamilleSoftware, labelIdASupprimer; 
	private JButton confirmer, annuler; 
	private JComboBox comboLibelles, comboId;
	private Object[] familleSoftwareListe, idListe;
	private JTable visualisationJTable;
	private JScrollPane visualisationScrollPane;
	private JPanel panneauTable;
	
	public PanneauSuppression() {
		
		setLayout(new FlowLayout());
		GestionnaireAction item = new GestionnaireAction();
		
		try {
			Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
			String familleSoftSQL = "select libelle from famillesoftware"; 
			PreparedStatement  prepStatFamilleSoft  = connection.prepareStatement(familleSoftSQL);
			
			familleSoftwareListe = AccessBDGen.creerListe1Colonne(prepStatFamilleSoft);
			
			
			labelFamilleSoftware = new JLabel("Famille des softwares : ");
			add(labelFamilleSoftware);
			
			
			comboLibelles= new JComboBox (familleSoftwareListe);
			comboLibelles.addItemListener(item);
			add(comboLibelles);
			
			String visualisationSQL = "Select IdInstallation ,DateInstallation, software.CodeSoftware, Matricule from installation inner join software on installation.CodeSoftware = software.CodeSoftware "
					+ "inner join famillesoftware on software.IdFamSoft = famillesoftware.IdFamSoft WHERE famillesoftware.libelle =\"" + comboLibelles.getSelectedItem() + "\"";
			PreparedStatement  prepStatVisualisation  = connection.prepareStatement(visualisationSQL);
			TableModelGen tableVisualisation = AccessBDGen.creerTableModel(prepStatVisualisation);
			visualisationJTable = new JTable(tableVisualisation);
			visualisationJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //sert à ce que la JTable ne change pas de taille en fonction de ce qu'il y a dedans
			visualisationScrollPane = new JScrollPane(visualisationJTable);
			panneauTable = new JPanel();
			panneauTable.add(visualisationScrollPane);
			add(panneauTable);
			
			String recupIdSQL = "Select IdInstallation from installation inner join software on installation.CodeSoftware = software.CodeSoftware "
					+ "inner join famillesoftware on software.IdFamSoft = famillesoftware.IdFamSoft WHERE famillesoftware.libelle =\"" + comboLibelles.getSelectedItem() + "\"";
			PreparedStatement  prepStatId  = connection.prepareStatement(recupIdSQL);
			idListe =  AccessBDGen.creerListe1Colonne(prepStatId);
			
			labelIdASupprimer = new JLabel("Id à supprimer : ");
			add(labelIdASupprimer);
			comboId = new JComboBox (idListe);
			add(comboId);
			comboId.addItemListener(item);
			
			setVisible(true);
		}
		
		catch (SQLException e) {  
			System.out.println(e.getMessage()); 
		} 
	}
	
	private class GestionnaireAction implements ItemListener{
		
		public void itemStateChanged(ItemEvent e) {
			
			try {
				Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String visualisationSQL = "Select IdInstallation ,DateInstallation, software.CodeSoftware, Matricule from installation inner join software on installation.CodeSoftware = software.CodeSoftware "
							+ "inner join famillesoftware on software.IdFamSoft = famillesoftware.IdFamSoft WHERE famillesoftware.libelle =\"" + comboLibelles.getSelectedItem() + "\"";
					PreparedStatement  prepStatVisualisation  = connection.prepareStatement(visualisationSQL);
					TableModelGen tableVisualisation = AccessBDGen.creerTableModel(prepStatVisualisation);
					visualisationJTable = new JTable(tableVisualisation);
					visualisationJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //sert à ce que la JTable ne change pas de taille en fonction de ce qu'il y a dedans
					visualisationScrollPane = new JScrollPane(visualisationJTable);
					panneauTable.removeAll();
					panneauTable.add(visualisationScrollPane);
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
