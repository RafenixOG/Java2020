package application;

import java.awt.*;
import javax.swing.*;

public class PanneauSupport extends JPanel{

	private JLabel texteSupport;
    private int style = Font.PLAIN;
    private String police = "Verdana";

    public PanneauSupport()
    {
        setLayout(new GridBagLayout());

        texteSupport = new JLabel("<html>Adresses mails :<br><br>Robin Lagneau : etu40041@henallux.be <br>Ramo Nuhic : etu41632@henallux.be</html>");
        texteSupport.setFont(new Font(police, style, 14));
        add(texteSupport);


        setVisible(true);
    }
}
