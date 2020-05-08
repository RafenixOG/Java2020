package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PanneauListe extends JPanel{
	private JRadioButton buttonDbInstall, buttonTableDb;
	private ButtonGroup ButGroupChoix;
	
	public PanneauListe() {
		setLayout(new GridLayout(10,2,10,10));
		
		GestionnaireAction g = new GestionnaireAction();
		
		buttonDbInstall = new JRadioButton("Lister table installation");
		add(buttonDbInstall);
		buttonDbInstall.addItemListener(g);
		
		
		buttonTableDb = new JRadioButton("Lister toutes les tables");
		add(buttonTableDb);
		buttonTableDb.addItemListener(g);
		
		ButGroupChoix = new ButtonGroup();
		ButGroupChoix.add(buttonDbInstall);
		ButGroupChoix.add(buttonTableDb);
		
		setVisible(true);
	}
	
	private class GestionnaireAction implements ItemListener {
		
		public void itemStateChanged(ItemEvent e) {
			
			if (buttonDbInstall.isSelected()) {
				JOptionPane.showMessageDialog(null,"Allez au secrétariat","",JOptionPane.WARNING_MESSAGE);	
			}
			if (buttonTableDb.isSelected()) {
				JOptionPane.showMessageDialog(null,"NAN","",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}
}
