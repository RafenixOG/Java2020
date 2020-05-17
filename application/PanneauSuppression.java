package application;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class PanneauSuppression extends JPanel {
	private JLabel labelFamilleSoftware, labelIdASupprimer; 
	private JButton supprimer, annuler; 
	private JComboBox comboLibelles, comboId;
	private Object[] familleSoftwareListe;
	private JTable visualisationJTable;
	private JScrollPane visualisationScrollPane;
	private JPanel panneauTable;
    private FenetrePrincipale fenetrePrincipale;

	
	public PanneauSuppression(FenetrePrincipale fenetrePrincipale) {
		
		this.fenetrePrincipale = fenetrePrincipale;
		
		setLayout(new FlowLayout());
		GestionnaireAction item = new GestionnaireAction();
		GestionnaireAction action = new GestionnaireAction();
		
		try {
			String familleSoftSQL = "select libelle from famillesoftware"; 
			PreparedStatement  prepStatFamilleSoft  = fenetrePrincipale.getConnection().prepareStatement(familleSoftSQL);
			
			familleSoftwareListe = AccessBDGen.creerListe1Colonne(prepStatFamilleSoft);
			
			
			labelFamilleSoftware = new JLabel("Famille des softwares : ");
			add(labelFamilleSoftware);
			
			
			comboLibelles= new JComboBox (familleSoftwareListe);
			comboLibelles.addItemListener(item);
			add(comboLibelles);
			
			String visualisationSQL = "Select IdInstallation ,DateInstallation, software.CodeSoftware, Matricule from installation inner join software on installation.CodeSoftware = software.CodeSoftware "
					+ "inner join famillesoftware on software.IdFamSoft = famillesoftware.IdFamSoft WHERE famillesoftware.libelle =\"" + comboLibelles.getSelectedItem() + "\"";
			PreparedStatement  prepStatVisualisation  = fenetrePrincipale.getConnection().prepareStatement(visualisationSQL);
			TableModelGen tableVisualisation = AccessBDGen.creerTableModel(prepStatVisualisation);
			visualisationJTable = new JTable(tableVisualisation);
			visualisationJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //sert à ce que la JTable ne change pas de taille en fonction de ce qu'il y a dedans
			visualisationScrollPane = new JScrollPane(visualisationJTable);
			panneauTable = new JPanel();
			panneauTable.add(visualisationScrollPane);
			add(panneauTable);
			
			labelIdASupprimer = new JLabel("Id installation à supprimer : ");
			add(labelIdASupprimer);
			
			ArrayList<Integer> listeId = new ArrayList<Integer>();
			for (int i = 0 ;visualisationJTable.getRowCount() > 0 && i < visualisationJTable.getRowCount(); i++ ) {
				listeId.add((Integer) (visualisationJTable.getValueAt(i,0)));
			}
			
			comboId = new JComboBox (listeId.toArray());
			add(comboId);
			
			supprimer = new JButton("Supprimer");
			supprimer.addActionListener(action);
			add(supprimer);
			
			annuler = new JButton ("Annuler");
			add(annuler);
			
			setVisible(true);
		}
		
		catch (SQLException e) {  
			System.out.println(e.getMessage()); 
		} 
	}
	
	public JButton getAnnuler() {
		return annuler;
	}
	
	private class GestionnaireAction implements ItemListener, ActionListener{
		
		public void itemStateChanged(ItemEvent e) {
			
			try {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String visualisationSQL = "Select IdInstallation ,DateInstallation, software.CodeSoftware, Matricule from installation inner join software on installation.CodeSoftware = software.CodeSoftware "
							+ "inner join famillesoftware on software.IdFamSoft = famillesoftware.IdFamSoft WHERE famillesoftware.libelle =\"" + comboLibelles.getSelectedItem() + "\"";
					PreparedStatement  prepStatVisualisation  = fenetrePrincipale.getConnection().prepareStatement(visualisationSQL);
					TableModelGen tableVisualisation = AccessBDGen.creerTableModel(prepStatVisualisation);
					visualisationJTable = new JTable(tableVisualisation);
					visualisationJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //sert à ce que la JTable ne change pas de taille en fonction de ce qu'il y a dedans
					visualisationScrollPane = new JScrollPane(visualisationJTable);
					panneauTable.removeAll();
					panneauTable.add(visualisationScrollPane);
					panneauTable.validate();
					panneauTable.repaint();
					ArrayList<Integer> listeId=new ArrayList<Integer>();
					for (int i = 0 ;visualisationJTable.getRowCount() > 0 && i < visualisationJTable.getRowCount(); i++ ) {
						listeId.add((Integer) (visualisationJTable.getValueAt(i,0)));
					}
					comboId.setModel(new DefaultComboBoxModel(listeId.toArray()));
				}
			}
			
			catch(SQLException e1) {
				System.out.println(e1.getMessage());
			}
		}
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == supprimer) {
				try {
					String deleteSQL = "DELETE FROM installation where IdInstallation = " + comboId.getSelectedItem();
					PreparedStatement  prepStatDelete  = fenetrePrincipale.getConnection().prepareStatement(deleteSQL);
					int n = JOptionPane.showConfirmDialog(getParent(),"Confirmez vous la suppression de l'id : " + comboId.getSelectedItem() + " ?","Confirmez votre choix",JOptionPane.YES_NO_OPTION);
					if(n == JOptionPane.YES_OPTION) {
						int  nbUpdatedLines = prepStatDelete.executeUpdate();
						System.out.println("Lignes supprimée : " + nbUpdatedLines);
						String visualisationSQL = "Select IdInstallation ,DateInstallation, software.CodeSoftware, Matricule from installation inner join software on installation.CodeSoftware = software.CodeSoftware "
								+ "inner join famillesoftware on software.IdFamSoft = famillesoftware.IdFamSoft WHERE famillesoftware.libelle =\"" + comboLibelles.getSelectedItem() + "\"";
						PreparedStatement  prepStatVisualisation  = fenetrePrincipale.getConnection().prepareStatement(visualisationSQL);
						TableModelGen tableVisualisation = AccessBDGen.creerTableModel(prepStatVisualisation);
						visualisationJTable = new JTable(tableVisualisation);
						visualisationJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //sert � ce que la JTable ne change pas de taille en fonction de ce qu'il y a dedans
						visualisationScrollPane = new JScrollPane(visualisationJTable);
						panneauTable.removeAll();
						panneauTable.add(visualisationScrollPane);
						panneauTable.validate();
						panneauTable.repaint();
						ArrayList<Integer> listeId=new ArrayList<Integer>();
						for (int i = 0 ;visualisationJTable.getRowCount() > 0 && i < visualisationJTable.getRowCount(); i++ ) {
							listeId.add((Integer) (visualisationJTable.getValueAt(i,0)));
						}
						comboId.setModel(new DefaultComboBoxModel(listeId.toArray()));
					}
				}
				catch(SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
	}
}
