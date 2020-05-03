package application;

import java.awt.GridLayout;
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

public class PanneauEncodage extends JPanel{
	private JLabel idInstall, software, os, responsable, validation, dateValidation, commentaire, dureeInstall, refProcedure;
	private JTextField idInstallTF, dateValidationTF, commentaireTF, dureeInstallTF, refProcedureTF;
	private JComboBox softwareCB, osCB, responsableCB;
	private JRadioButton termine, enCours, aPrevoir;
	private ButtonGroup validationBG;
	private GregorianCalendar dateInstall;
	private int typeInstall;
	private JPanel panneauRadio;
	private String[] softwareListe = {""}, osListe = {""}, responsableListe = {""};
	private JButton confirmer, retour;
	
	
	public PanneauEncodage() {
		
		setLayout(new GridLayout(10,2,10,10));
		
		GestionnaireItem item = new GestionnaireItem();
		
		idInstall = new JLabel("ID d'installation :");
		idInstall.setHorizontalAlignment(SwingConstants.RIGHT);
		add(idInstall);
		idInstallTF = new JTextField(3);
		idInstallTF.setEditable(false);
		add(idInstallTF);
		dateInstall = new GregorianCalendar();
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
		dateValidationTF = new JTextField(10);
		dateValidationTF.setEditable(false);
		add(dateValidationTF);
		software = new JLabel("Code Software :");
		software.setHorizontalAlignment(SwingConstants.RIGHT);
		add(software);
		softwareCB = new JComboBox(softwareListe);		//Trouver un moyen de récupérer toutes les valeurs de la colone CodeSoftware et les ajouter à softwareListe
		add(softwareCB);
		responsable = new JLabel("Matricule du responsable :");
		responsable.setHorizontalAlignment(SwingConstants.RIGHT);
		add(responsable);
		responsableCB = new JComboBox(responsableListe);	//Trouver un moyen de récupérer toutes les valeurs de la colone CodeSoftware et les ajouter à softwareListe
		add(responsableCB);
		os = new JLabel("Code OS :");
		os.setHorizontalAlignment(SwingConstants.RIGHT);
		add(os);
		osCB = new JComboBox(osListe);		//Trouver un moyen de récupérer toutes les valeurs de la colone CodeSoftware et les ajouter à softwareListe
		add(osCB);
		confirmer = new JButton("Valider");
		add(confirmer);
		retour = new JButton("Retour");
		add(retour);
		
		setVisible(true);
		
	}
	
	private class GestionnaireItem implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == aPrevoir && e.getStateChange( ) == ItemEvent.SELECTED) {
				dateValidationTF.setEditable(true);
			}
			else {
				dateValidationTF.setText("");
				dateValidationTF.setEditable(false);
			}
		}
	}

}
