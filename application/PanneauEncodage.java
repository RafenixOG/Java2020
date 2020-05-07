package application;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement; 
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import accessBD.*;


public class PanneauEncodage extends JPanel{
	private JLabel idInstall, software, os, responsable, validation, dateValidation, commentaire, dureeInstall, refProcedure;
	private JTextField idInstallTF, dateValidationTF, commentaireTF, dureeInstallTF, refProcedureTF;
	private JComboBox softwareCB, osCB, responsableCB;
	private JRadioButton termine, enCours, aPrevoir;
	private ButtonGroup validationBG;
	private int typeInstall;
	private JPanel panneauRadio;
	private Object[] softwareListe, osListe, responsableListe, idInstallListe;
	private JButton valider, retour;
	private int idInstallInt;
	private String insertionSQL;
	private PreparedStatement myPrepStatInsertion;
	
	
	public PanneauEncodage() {
		
		try  {
			Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root"); 
			String softwareSQL = "select Nom from software"; 
			String osSQL = "select Libelle from os";
			String responsableSQL = "select NomPrenom from responsablereseaux";
			PreparedStatement  myPrepStatSoftware  = connection.prepareStatement(softwareSQL);
			PreparedStatement  myPrepStatOs  = connection.prepareStatement(osSQL);
			PreparedStatement  myPrepStatResponsable  = connection.prepareStatement(responsableSQL);
			softwareListe = AccessBDGen.creerListe1Colonne(myPrepStatSoftware);
			osListe = AccessBDGen.creerListe1Colonne(myPrepStatOs);
			responsableListe = AccessBDGen.creerListe1Colonne(myPrepStatResponsable);
		 
		
		
			setLayout(new GridLayout(10,2,10,10));
			
			Gestionnaire item = new Gestionnaire();
			Gestionnaire action = new Gestionnaire();
			
			idInstall = new JLabel("ID d'installation :");
			idInstall.setHorizontalAlignment(SwingConstants.RIGHT);
			add(idInstall);
			idInstallTF = new JTextField(3);
			idInstallTF.setEditable(false);
			add(idInstallTF);
			typeInstall = 1;
			commentaire = new JLabel("Commentaire :");
			commentaire.setHorizontalAlignment(SwingConstants.RIGHT);
			add(commentaire);
			commentaireTF = new JTextField(100);
			add(commentaireTF);
			dureeInstall = new JLabel("Dur�e de l'installation : ");
			dureeInstall.setHorizontalAlignment(SwingConstants.RIGHT);
			add(dureeInstall);
			dureeInstallTF = new JTextField(4);		//Il faudra transformer en int + gestion erreur pas possible de transformer en int
			add(dureeInstallTF);
			refProcedure = new JLabel("R�f�rence de la proc�dure d'installation :");
			refProcedure.setHorizontalAlignment(SwingConstants.RIGHT);
			add(refProcedure);
			refProcedureTF = new JTextField(50);
			add(refProcedureTF);
			validation = new JLabel("Validation :");
			validation.setHorizontalAlignment(SwingConstants.RIGHT);
			add(validation);
			panneauRadio = new JPanel();
			panneauRadio.setLayout(new GridLayout(3,1,0,5));
			termine = new JRadioButton("Termin�e");
			termine.addItemListener(item);
			panneauRadio.add(termine);
			enCours = new JRadioButton("En cours");
			enCours.addItemListener(item);
			panneauRadio.add(enCours);
			aPrevoir = new JRadioButton("� pr�voir");
			aPrevoir.addItemListener(item);
			panneauRadio.add(aPrevoir);
			validationBG = new ButtonGroup();
			validationBG.add(termine);
			validationBG.add(enCours);
			validationBG.add(aPrevoir);
			panneauRadio.setVisible(true);
			add(panneauRadio);
			dateValidation = new JLabel("Date de validation :");		//gestion d'erreur si la date n'� pas �t� entr�e au format dd/mm/YYYY
			dateValidation.setHorizontalAlignment(SwingConstants.RIGHT);
			add(dateValidation);
			dateValidationTF = new JTextField(10);
			dateValidationTF.setEditable(false);
			add(dateValidationTF);
			software = new JLabel("Code Software :");
			software.setHorizontalAlignment(SwingConstants.RIGHT);
			add(software);
			softwareCB = new JComboBox(softwareListe);		//Trouver un moyen de r�cup�rer toutes les valeurs de la colone CodeSoftware et les ajouter � softwareListe
			add(softwareCB);
			responsable = new JLabel("Matricule du responsable :");
			responsable.setHorizontalAlignment(SwingConstants.RIGHT);
			add(responsable);
			responsableCB = new JComboBox(responsableListe);	//Trouver un moyen de r�cup�rer toutes les valeurs de la colone CodeSoftware et les ajouter � softwareListe
			add(responsableCB);
			os = new JLabel("Code OS :");
			os.setHorizontalAlignment(SwingConstants.RIGHT);
			add(os);
			osCB = new JComboBox(osListe);		//Trouver un moyen de r�cup�rer toutes les valeurs de la colone CodeSoftware et les ajouter � softwareListe
			add(osCB);
			valider = new JButton("Valider");
			valider.addActionListener(action);
			add(valider);
			retour = new JButton("Retour");
			add(retour);
			
			setVisible(true);
			
			insertionSQL = "insert into installation (IdInstallation, DateInstallation, TypeInstallation, Commentaires, DureeInstallation, RefProcedureInstallation, Validation, DateValidation, CodeSoftware, Matricule, CodeOS) values (?,?,?,?,?,?,?,?,?,?,?)";
			myPrepStatInsertion = connection.prepareStatement(insertionSQL);
			
			String idInstallSQL = "select IdInstallation from installation";
			PreparedStatement myPrepStatIdInstall = connection.prepareStatement(idInstallSQL);
			idInstallListe = AccessBDGen.creerListe1Colonne(myPrepStatIdInstall);
			idInstallInt = idInstallListe.length + 1;
			idInstallTF.setText(String.valueOf(idInstallInt));
			
			
		} 
		
		catch (SQLException e) {  
			System.out.println(e.getMessage()); 
		} 
		
	}
	 
	private class Gestionnaire implements ItemListener, ActionListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == aPrevoir && e.getStateChange( ) == ItemEvent.SELECTED) {
				dateValidationTF.setEditable(true);
			}
			else {
				dateValidationTF.setText("");
				dateValidationTF.setEditable(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == valider) {
				try {
					myPrepStatInsertion.setInt(1, idInstallInt);
					myPrepStatInsertion.setDate(2, new java.sql.Date( new GregorianCalendar(GregorianCalendar.getInstance().get(GregorianCalendar.YEAR),GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)-1,GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH)).getTimeInMillis()));
					myPrepStatInsertion.setInt(3, typeInstall);
					if(commentaireTF.getText().equals("") == false) {
						myPrepStatInsertion.setString(4, commentaireTF.getText());
					}
					else {
						myPrepStatInsertion.setNull(4, Types.VARCHAR);
					}
					if(dureeInstallTF.getText().equals("") == false) {
						myPrepStatInsertion.setInt(5, Integer.parseInt(dureeInstallTF.getText()));
					}
					else {
						myPrepStatInsertion.setNull(5, Types.INTEGER);
					}
					if(refProcedureTF.getText().equals("") == false) {
						myPrepStatInsertion.setString(6, refProcedureTF.getText());
					}
					else {
						myPrepStatInsertion.setNull(6, Types.VARCHAR);
					}
					if(termine.isSelected()) {
						myPrepStatInsertion.setString(7, "Terminee");
					}
					if(enCours.isSelected()) {
						myPrepStatInsertion.setString(7, "En cours");
					}
					if(aPrevoir.isSelected()) {
						myPrepStatInsertion.setString(7, "A prevoir");
					}
					if(dateValidationTF.getText().equals("") == false) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date date = null;
					    try {
							date = (Date) dateFormat.parse(dateValidationTF.getText());
							myPrepStatInsertion.setDate(8, date);
						} catch (ParseException parse) {
							parse.printStackTrace();
						}
					}
					else {
						myPrepStatInsertion.setNull(8, Types.TIMESTAMP);
					}
					myPrepStatInsertion.setString(9, String.valueOf(softwareCB.getSelectedItem()));
					myPrepStatInsertion.setString(10, String.valueOf(responsableCB.getSelectedItem()));
					myPrepStatInsertion.setString(11, String.valueOf(osCB.getSelectedItem()));
					System.out.println("Valid�");
				} 
				catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
	}

}
