package application;

import java.awt.*;
import javax.swing.*;

public class PanneauInfo extends JPanel {
	
	private JLabel texteInfo;
	private int style = Font.PLAIN;
	private String police = "Verdana";

	public PanneauInfo(){
		
		setLayout(new GridBagLayout());
		texteInfo = new JLabel("<html>Projet r�alis� pour le cour de Java de 2020<br><br>D�velopp� par Ramo et Robin</html>");
		texteInfo.setFont(new Font(police, style, 14));
		add(texteInfo);
		
		setVisible(true);
	    }
}
