package application;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;

public class FenetreSupport extends JFrame {
	private PanneauSupport panneauSupport;
	private Container cont;
	
	public FenetreSupport() {
		
		super("Support");
		

		setSize(400, 200);
		
		this.setLocationRelativeTo(getParent());

		setResizable(false);
		
		panneauSupport = new PanneauSupport();
		cont = getContentPane();
		cont.add(panneauSupport);
		
		setVisible(true);
		
	}

}
