package application;

import java.awt.GridLayout;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PanneauEncodage extends JPanel{
	private JLabel idInstall, software, os, responsable, validation, dateValidation, commentaire, dureeInstall, refProcedure;
	private JTextField idInstallTF, dateValidationTF, commentaireTF, dureeInstallTF, refProcedureTF;
	private JComboBox softwareCB, osCB, responsableCB;
	private JRadioButton termine, enCours, aPrevoir;
	private ButtonGroup validationBG;
	private GregorianCalendar dateInstall;
	private int typeInstall;
	private JPanel panneauRadio;
	private String[] softwareListe, osListe, responsableListe;
	
	
	public PanneauEncodage() {
		
		setLayout(new GridLayout(8,2,10,10));
		
		idInstall = new JLabel("ID d'installation :");
		add(idInstall);
		idInstallTF = new JTextField(3);
		idInstallTF.setEditable(false);
		add(idInstallTF);
		dateInstall = new GregorianCalendar();
		typeInstall = 1;
		commentaire = new JLabel("Commentaire :");
		add(commentaire);
		commentaireTF = new JTextField(100);
		add(commentaireTF);
		dureeInstall = new JLabel("Durée de l'installation : ");
		add(dureeInstall);
		dureeInstallTF = new JTextField("4");
		add(dureeInstallTF);
		refProcedure = new JLabel("Référence de la procédure d'installation :");
		add(refProcedure);
		refProcedureTF = new JTextField(50);
		add(refProcedure);
		validation = new JLabel("Validation :");
		add(validation);
		panneauRadio = new JPanel();
		panneauRadio.setLayout(new GridLayout(3,1,0,10));
		termine = new JRadioButton("Terminée");
		panneauRadio.add(termine);
		enCours = new JRadioButton("En cours");
		panneauRadio.add(enCours);
		aPrevoir = new JRadioButton("À prévoir");
		panneauRadio.add(aPrevoir);
		validationBG = new ButtonGroup();
		validationBG.add(termine);
		validationBG.add(enCours);
		validationBG.add(aPrevoir);
		add(panneauRadio);
		dateValidation = new JLabel("Date de validation :");
		add(dateValidation);
		dateValidationTF = new JTextField(10);
		add(dateValidationTF);
		software = new JLabel("Code Software :");
		add(software);
		softwareCB = new JComboBox(softwareListe);		//Trouver un moyen de récupérer toutes les valeurs différentes de la colone CodeSoftware et les ajouter à softwareListe
		add(softwareCB);
		responsable = new JLabel("Matricule du responsable :");
		add(responsable);
		responsableCB = new JComboBox(responsableListe);	//Trouver un moyen de récupérer toutes les valeurs différentes de la colone CodeSoftware et les ajouter à softwareListe
		add(responsableCB);
		os = new JLabel("Code OS :");
		add(os);
		osCB = new JComboBox(osListe);		//Trouver un moyen de récupérer toutes les valeurs différentes de la colone CodeSoftware et les ajouter à softwareListe
		add(osCB);
		
	}

}
