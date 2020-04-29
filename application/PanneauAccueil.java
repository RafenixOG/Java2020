package application;

import java.awt.*;
import javax.swing.*;

public class PanneauAccueil extends JPanel {
	
	private JLabel texteAccueil;
    private int style = Font.BOLD;
    private String police = "Verdana";

    public PanneauAccueil()
    {
        setLayout(new GridBagLayout());

        texteAccueil = new JLabel("<html>Bienvenue dans notre projet java !<br>Naviguez à travers les différents menus.</html>");
        texteAccueil.setFont(new Font(police, style, 14));
        add(texteAccueil);


        setVisible(true);
    }
	
}
