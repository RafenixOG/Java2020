package application;

import java.awt.FlowLayout;
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
	
	public PanneauRecherchePc() {
		
		setLayout(new FlowLayout());
		
		Gestionnaire item = new Gestionnaire();
		
		try {
			Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root");
			
			String sqlDescription = "select Description from typepc";
			PreparedStatement prepStatDescription = connection.prepareStatement(sqlDescription);
			description = AccessBDGen.creerListe1Colonne(prepStatDescription);
			descriptionCB = new JComboBox(description);
			descriptionCB.addItemListener(item);
			add(descriptionCB);
			
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
				switch(descriptionCB.getSelectedIndex()) {
				case 0:if(e.getStateChange() == ItemEvent.SELECTED) {
					String sqlAthlonIIX3 = "select distinct software.* from software inner join softwarepreinstalle on software.CodeSoftware"
							+ "= softwarepreinstalle.CodeSoftware inner join typepc on softwarepreinstalle.IdTypePC = typepc.IdTypePC "
							+ "where software.CleInstallation is null and typepc.Description = \"Athlon II X3\"";
					PreparedStatement prepStatAthlonIIX3 = connection.prepareStatement(sqlAthlonIIX3);
					TableModelGen tableAthlonIIX3 = AccessBDGen.creerTableModel(prepStatAthlonIIX3);
					table = new JTable(tableAthlonIIX3);
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
					String sqlCoreI7_2600 = "select distinct software.* from software inner join softwarepreinstalle on software.CodeSoftware"
							+ "= softwarepreinstalle.CodeSoftware inner join typepc on softwarepreinstalle.IdTypePC = typepc.IdTypePC "
							+ "where software.CleInstallation is null and typepc.Description = \"Core I7 2600\"";
					PreparedStatement prepStatCoreI7_2600 = connection.prepareStatement(sqlCoreI7_2600);
					TableModelGen tableCoreI7_2600 = AccessBDGen.creerTableModel(prepStatCoreI7_2600);
					table = new JTable(tableCoreI7_2600);
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
					String sqlCeleron530 = "select distinct software.* from software inner join softwarepreinstalle on software.CodeSoftware"
							+ "= softwarepreinstalle.CodeSoftware inner join typepc on softwarepreinstalle.IdTypePC = typepc.IdTypePC "
							+ "where software.CleInstallation is null and typepc.Description = \"Celeron 530\"";
					PreparedStatement prepStatCeleron530 = connection.prepareStatement(sqlCeleron530);
					TableModelGen tableCeleron530 = AccessBDGen.creerTableModel(prepStatCeleron530);
					table = new JTable(tableCeleron530);
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
					String sqlPentium4_3GB = "select distinct software.* from software inner join softwarepreinstalle on software.CodeSoftware"
							+ "= softwarepreinstalle.CodeSoftware inner join typepc on softwarepreinstalle.IdTypePC = typepc.IdTypePC "
							+ "where software.CleInstallation is null and typepc.Description = \"Pentium 4 3GB\"";
					PreparedStatement prepStatPentium4_3GB = connection.prepareStatement(sqlPentium4_3GB);
					TableModelGen tablePentium4_3GB = AccessBDGen.creerTableModel(prepStatPentium4_3GB);
					table = new JTable(tablePentium4_3GB);
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
					String sqlDualCore2x_1GB8 = "select distinct software.* from software inner join softwarepreinstalle on software.CodeSoftware"
							+ "= softwarepreinstalle.CodeSoftware inner join typepc on softwarepreinstalle.IdTypePC = typepc.IdTypePC "
							+ "where software.CleInstallation is null and typepc.Description = \"Pentium 4 3GB\"";
					PreparedStatement prepStatDualCore2x_1GB8 = connection.prepareStatement(sqlDualCore2x_1GB8);
					TableModelGen tableDualCore2x_1GB8 = AccessBDGen.creerTableModel(prepStatDualCore2x_1GB8);
					table = new JTable(tableDualCore2x_1GB8);
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
