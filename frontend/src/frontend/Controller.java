package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private ViewWunschlisteErstellen viewWunschlisteErstellen;
	private ViewalleWunschlisten viewAlleWunschlisten;
	private Model model;
	
	public Controller() {
		viewWunschlisteErstellen = new ViewWunschlisteErstellen();
		viewWunschlisteErstellen.btnZurueckModel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewWunschlisteErstellen.setVisible(false);
				viewAlleWunschlisten.setVisible(true);
			}
		});
		viewAlleWunschlisten = new ViewalleWunschlisten();
		model = new Model();
	}
	
	public void start() {
		viewWunschlisteErstellen.setVisible(true);
	}
}
