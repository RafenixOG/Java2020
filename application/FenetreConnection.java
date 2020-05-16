package application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;
import accessBD.AccessBDGen;

public class FenetreConnection extends JFrame{
	private Container cont;
	private JPanel panneauConnection;
	private Connection connection;
	private String user, mdp; 
	private JLabel labelUser, labelMdp;
	private JTextField textUser, textMdp;
	private JButton Connection, Deconnection;
	
	public FenetreConnection() {
		super("Fenetre de connexion");
		this.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) { dispose();}});
		setBounds(600,200,300,200);
		
		GestionnaireAction action = new GestionnaireAction();
		
		panneauConnection = new JPanel();
		panneauConnection.setLayout(new FlowLayout());
		
		labelUser = new JLabel("Nom d'utilisateur :");
		panneauConnection.add(labelUser);
		textUser = new JTextField(30);
		panneauConnection.add(textUser);
		
		labelMdp = new JLabel("Mot de passe :");
		panneauConnection.add(labelMdp);
		textMdp = new JTextField(30);
		panneauConnection.add(textMdp);
		
		Connection = new JButton("Connexion");
		Connection.addActionListener(action);
		panneauConnection.add(Connection);
		
		Deconnection = new JButton ("Déconnexion");
		Deconnection.addActionListener(action);
		panneauConnection.add(Deconnection);
		
		cont = getContentPane();
        cont.add(panneauConnection);
		
		setVisible(true);
	}
	
	private class GestionnaireAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Connection) {
				try {
					connection = AccessBDGen.connecter("DbInstallations", textUser.getText(), textMdp.getText());
					System.out.println("Connexion réussie");
				}
				catch(SQLException e1) {
					System.out.println(e1.getMessage()); 
				}
			}
			if (e.getSource() == Deconnection) {
				try {
					connection.close();
					System.out.println("Déconnexion réussie");
				}
				catch(SQLException e1) {
					System.out.println(e1.getMessage()); 
				}
			}
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
}
