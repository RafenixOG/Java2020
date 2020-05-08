package application;

import java.awt.*;
import javax.swing.*;

public class PanneauInfo extends JPanel {
	
	private JLabel texteInfo;
	private int style = Font.PLAIN;
	private String police = "Verdana";

	public PanneauInfo(){
		
		setLayout(new GridBagLayout());
		texteInfo = new JLabel("<html>Projet réalisé pour le cour de Java de 2020<br><br>Développé par Ramo et Robin</html>");
		texteInfo.setFont(new Font(police, style, 14));
		add(texteInfo);
		
		setVisible(true);
	    }
}
