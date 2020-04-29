package application;

import java.awt.*;
import javax.swing.*;

public class PanneauAccueil extends JPanel {
	
	private JLabel texteAccueil, texteAccueil2;
    private int style = Font.BOLD;
    private String police = "Verdana";

    public PanneauAccueil()
    {
        setLayout(new GridBagLayout());

        texteAccueil = new JLabel("Bienvenue dans notre projet java !");
        texteAccueil.setFont(new Font(police, style, 14));
        texteAccueil2 = new JLabel("texte à mettre en dessous");
        texteAccueil2.setFont(new Font(police, style, 12));
        add(texteAccueil);
        add(texteAccueil2);

        setVisible(true);
    }
	
}
