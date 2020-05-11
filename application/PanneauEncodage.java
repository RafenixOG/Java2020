package application;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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
	private JTextField idInstallTF, commentaireTF, dureeInstallTF, refProcedureTF;
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
	//Besoin du jdatepicker-1.3.4.jar, à ajouter dans les propriétés du projet comme pour le jar de la DB
	private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private Properties p;
	
	
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
			dureeInstall = new JLabel("Durée de l'installation : ");
			dureeInstall.setHorizontalAlignment(SwingConstants.RIGHT);
			add(dureeInstall);
			dureeInstallTF = new JTextField(4);		//Il faudra transformer en int + gestion erreur pas possible de transformer en int
			add(dureeInstallTF);
			refProcedure = new JLabel("Référence de la procédure d'installation :");
			refProcedure.setHorizontalAlignment(SwingConstants.RIGHT);
			add(refProcedure);
			refProcedureTF = new JTextField(50);
			add(refProcedureTF);
			validation = new JLabel("Validation :");
			validation.setHorizontalAlignment(SwingConstants.RIGHT);
			add(validation);
			panneauRadio = new JPanel();
			panneauRadio.setLayout(new GridLayout(3,1,0,5));
			termine = new JRadioButton("Terminée");
			termine.addItemListener(item);
			panneauRadio.add(termine);
			enCours = new JRadioButton("En cours");
			enCours.addItemListener(item);
			panneauRadio.add(enCours);
			aPrevoir = new JRadioButton("À prévoir");
			aPrevoir.addItemListener(item);
			panneauRadio.add(aPrevoir);
			validationBG = new ButtonGroup();
			validationBG.add(termine);
			validationBG.add(enCours);
			validationBG.add(aPrevoir);
			panneauRadio.setVisible(true);
			add(panneauRadio);
			dateValidation = new JLabel("Date de validation :");		//gestion d'erreur si la date n'à pas été entrée au format dd/mm/YYYY
			dateValidation.setHorizontalAlignment(SwingConstants.RIGHT);
			add(dateValidation);
			//Tout ce qui est nécessaire au JDatePicker--------
			p = new Properties();
	        p.put("text.today", "Ajourd'hui");
	        p.put("text.month", "Mois");
	        p.put("text.year", "Année");
	        model = new UtilDateModel();
	        datePanel = new JDatePanelImpl(model, p);
	        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	        //-------------------------------------------------
	        datePicker.getComponent(1).setEnabled(false);
	        add(datePicker);
			software = new JLabel("Software :");
			software.setHorizontalAlignment(SwingConstants.RIGHT);
			add(software);
			softwareCB = new JComboBox(softwareListe);		//Trouver un moyen de récupérer toutes les valeurs de la colone CodeSoftware et les ajouter à softwareListe
			add(softwareCB);
			responsable = new JLabel("Nom du responsable :");
			responsable.setHorizontalAlignment(SwingConstants.RIGHT);
			add(responsable);
			responsableCB = new JComboBox(responsableListe);	//Trouver un moyen de récupérer toutes les valeurs de la colone CodeSoftware et les ajouter à softwareListe
			add(responsableCB);
			os = new JLabel("Nom OS :");
			os.setHorizontalAlignment(SwingConstants.RIGHT);
			add(os);
			osCB = new JComboBox(osListe);		//Trouver un moyen de récupérer toutes les valeurs de la colone CodeSoftware et les ajouter à softwareListe
			add(osCB);
			valider = new JButton("Valider");
			valider.addActionListener(action);
			add(valider);
			retour = new JButton("Retour");
			add(retour);
			
			setVisible(true);
			
			insertionSQL = "insert into installation values (?,?,?,?,?,?,?,?,?,?,?)";
			myPrepStatInsertion = connection.prepareStatement(insertionSQL);
			
			String idInstallSQL = "select max(IdInstallation) from installation"; //SQL mettre "MAX" pour avoir la valeur la plus élevée
			PreparedStatement myPrepStatIdInstall = connection.prepareStatement(idInstallSQL);
			idInstallListe = AccessBDGen.creerListe1Colonne(myPrepStatIdInstall);
			idInstallInt = (int)idInstallListe[0] + 1;
			idInstallTF.setText(String.valueOf(idInstallInt));
			
			
		} 
		
		catch (SQLException e) {  
			System.out.println(e.getMessage()); 
		} 
		
	}
	 
	private class Gestionnaire implements ItemListener, ActionListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == aPrevoir && e.getStateChange( ) == ItemEvent.SELECTED) {
				datePicker.getComponent(1).setEnabled(true);
			}
			else {
    			datePicker.getJFormattedTextField().setText("");
				datePicker.getComponent(1).setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == valider) {
				try {
					myPrepStatInsertion.setInt(1, idInstallInt);
					myPrepStatInsertion.setDate(2, new java.sql.Date( new GregorianCalendar().getTimeInMillis()));
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
					if(datePicker.getJFormattedTextField().getText().equals("") == false) {
						String[] dateSepareeString = datePicker.getJFormattedTextField().getText().split("-", 3);
		    			int[] dateSepareeint = {0, 0, 0};
		    			for(int i = 0; i < dateSepareeString.length; i++) {
		    				dateSepareeint[i] = Integer.parseInt(dateSepareeString[i]);
		    			}
		    			GregorianCalendar ajd = new GregorianCalendar();
		    	        GregorianCalendar dateSelectionnee = new GregorianCalendar(dateSepareeint[0], dateSepareeint[1]-1, dateSepareeint[2]);
		    	        if(dateSelectionnee.getTimeInMillis() - ajd.getTimeInMillis() <= 0) {
		    	        	throw new DateException();
		    	        }
		    	        else {
							myPrepStatInsertion.setDate(8, new java.sql.Date(new GregorianCalendar(dateSepareeint[0], dateSepareeint[1]-1, dateSepareeint[2]+1).getTimeInMillis()));;
		    	        }
						 
					}
					else {
						myPrepStatInsertion.setNull(8, Types.TIMESTAMP);
					}
					Connection  connection  =  AccessBDGen.connecter("DbInstallations", "root", "root"); 
					String instructionCodeSoftware = "select CodeSoftware from software where Nom = \"" + softwareCB.getSelectedItem() + "\"";
					PreparedStatement myPrepStatCodeSoftware = connection.prepareStatement(instructionCodeSoftware);
					Object[] codeSoftware = AccessBDGen.creerListe1Colonne(myPrepStatCodeSoftware);
					String instructionCodeResponsable = "select Matricule from responsablereseaux where NomPrenom = \"" + responsableCB.getSelectedItem() + "\"";
					PreparedStatement myPrepStatCodeResponsable = connection.prepareStatement(instructionCodeResponsable);
					Object[] codeResponsable = AccessBDGen.creerListe1Colonne(myPrepStatCodeResponsable);
					String instructionCodeOs = "select CodeOS from os where Libelle = \"" + osCB.getSelectedItem() + "\"";
					PreparedStatement myPrepStatCodeOs = connection.prepareStatement(instructionCodeOs);
					Object[] codeOs = AccessBDGen.creerListe1Colonne(myPrepStatCodeOs);
					myPrepStatInsertion.setString(9, (String)codeSoftware[0]); //code du software
					myPrepStatInsertion.setString(10, (String)codeResponsable[0]); //code du responsable
					myPrepStatInsertion.setString(11, (String)codeOs[0]); //code de l'os
					System.out.println("passé toutes les instructions");
					int  nbUpdatedLines = myPrepStatInsertion.executeUpdate();
					System.out.println(nbUpdatedLines);
					idInstallInt++;
					idInstallTF.setText(String.valueOf(idInstallInt));

					
				} 
				catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(getParent(), "La durée que vous avez introduit n'est pas un entier.\nVeuillez introduire une durée en minutes.", "ERREUR TYPE VALEUR ÉRRONÉ", JOptionPane.ERROR_MESSAGE);
				}
				catch(DateException dateErronee) {
					JOptionPane.showMessageDialog(getParent(), dateErronee, "ERREUR DATE ÉRRONÉ", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
