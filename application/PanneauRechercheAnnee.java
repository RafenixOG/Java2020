package application;

import java.awt.FlowLayout;
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
	
	public PanneauRechercheAnnee() {
		
		setLayout(new FlowLayout());
		
		Gestionnaire item = new Gestionnaire();
				
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
			add(anneeCB);
			
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
			
			panneauTable = new JPanel();
			panneauTable.add(defilant);
			add(panneauTable);
			
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
				switch(anneeCB.getSelectedIndex()) {
				case 0:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql1TI = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "1 and anneeetude.CodeSection = \"TI\"";
					PreparedStatement prepStat1TI = connection.prepareStatement(sql1TI);
					TableModelGen table1TI = AccessBDGen.creerTableModel(prepStat1TI);
					table = new JTable(table1TI);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 1:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql2TI = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "2 and anneeetude.CodeSection = \"TI\"";
					PreparedStatement prepStat2TI = connection.prepareStatement(sql2TI);
					TableModelGen table2TI = AccessBDGen.creerTableModel(prepStat2TI);
					table = new JTable(table2TI);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 2:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql3TI = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "3 and anneeetude.CodeSection = \"TI\"";
					PreparedStatement prepStat3TI = connection.prepareStatement(sql3TI);
					TableModelGen table3TI = AccessBDGen.creerTableModel(prepStat3TI);
					table = new JTable(table3TI);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 3:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql1IG = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "1 and anneeetude.CodeSection = \"IG\"";
					PreparedStatement prepStat1IG = connection.prepareStatement(sql1IG);
					TableModelGen table1IG = AccessBDGen.creerTableModel(prepStat1IG);
					table = new JTable(table1IG);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 4:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql2IG = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "2 and anneeetude.CodeSection = \"IG\"";
					PreparedStatement prepStat2IG = connection.prepareStatement(sql2IG);
					TableModelGen table2IG = AccessBDGen.creerTableModel(prepStat2IG);
					table = new JTable(table2IG);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 5:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql3IG = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "3 and anneeetude.CodeSection = \"IG\"";
					PreparedStatement prepStat3IG = connection.prepareStatement(sql3IG);
					TableModelGen table3IG = AccessBDGen.creerTableModel(prepStat3IG);
					table = new JTable(table3IG);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 6:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql1CP = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "1 and anneeetude.CodeSection = \"CP\"";
					PreparedStatement prepStat1CP = connection.prepareStatement(sql1CP);
					TableModelGen table1CP = AccessBDGen.creerTableModel(prepStat1CP);
					table = new JTable(table1CP);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 7:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql2CP = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "2 and anneeetude.CodeSection = \"CP\"";
					PreparedStatement prepStat2CP = connection.prepareStatement(sql2CP);
					TableModelGen table2CP = AccessBDGen.creerTableModel(prepStat2CP);
					table = new JTable(table2CP);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 8:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql3CP = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "3 and anneeetude.CodeSection = \"CP\"";
					PreparedStatement prepStat3CP = connection.prepareStatement(sql3CP);
					TableModelGen table3CP = AccessBDGen.creerTableModel(prepStat3CP);
					table = new JTable(table3CP);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 9:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql1MK = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "1 and anneeetude.CodeSection = \"MK\"";
					PreparedStatement prepStat1MK = connection.prepareStatement(sql1MK);
					TableModelGen table1MK = AccessBDGen.creerTableModel(prepStat1MK);
					table = new JTable(table1MK);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 10:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql2MK = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "2 and anneeetude.CodeSection = \"MK\"";
					PreparedStatement prepStat2MK = connection.prepareStatement(sql2MK);
					TableModelGen table2MK = AccessBDGen.creerTableModel(prepStat2MK);
					table = new JTable(table2MK);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 11:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql3MK = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "3 and anneeetude.CodeSection = \"MK\"";
					PreparedStatement prepStat3MK = connection.prepareStatement(sql3MK);
					TableModelGen table3MK = AccessBDGen.creerTableModel(prepStat3MK);
					table = new JTable(table3MK);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 12:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql1IR = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "1 and anneeetude.CodeSection = \"IR\"";
					PreparedStatement prepStat1IR = connection.prepareStatement(sql1IR);
					TableModelGen table1IR = AccessBDGen.creerTableModel(prepStat1IR);
					table = new JTable(table1IR);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 13:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql2IR = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "1 and anneeetude.CodeSection = \"IR\"";
					PreparedStatement prepStat2IR = connection.prepareStatement(sql2IR);
					TableModelGen table2IR = AccessBDGen.creerTableModel(prepStat2IR);
					table = new JTable(table2IR);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				case 14:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sql3IR = "select distinct installation.* from installation inner join software on "
							+ "installation.CodeSoftware = software.CodeSoftware inner join utilisationsoftware on "
							+ "software.CodeSoftware = utilisationsoftware.CodeSoftware inner join anneeetude on "
							+ "utilisationsoftware.IdAnneeEtude = anneeetude.IdAnneeEtude where anneeetude.Annee = "
							+ "1 and anneeetude.CodeSection = \"IR\"";
					PreparedStatement prepStat3IR = connection.prepareStatement(sql3IR);
					TableModelGen table3IR = AccessBDGen.creerTableModel(prepStat3IR);
					table = new JTable(table3IR);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					defilant = new JScrollPane(table);
					panneauTable.removeAll();
					panneauTable.add(defilant);
					panneauTable.validate();
					panneauTable.repaint();
					System.out.println("fait");
					break;
				}
				}
			}
			catch(SQLException e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
}
