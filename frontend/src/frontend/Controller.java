package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Controller
{
	private ViewWunschlisteErstellen viewWunschlisteErstellen;
	private ViewalleWunschlisten viewAlleWunschlisten;
	private Model model;
	
	public Controller()
	{
		viewWunschlisteErstellen = new ViewWunschlisteErstellen();
		viewWunschlisteErstellen.btnZurueckModel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				viewWunschlisteErstellen.setVisible(false);
				viewAlleWunschlisten.setVisible(true);
			}
		});
		viewWunschlisteErstellen.btnErstellenModel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		viewAlleWunschlisten = new ViewalleWunschlisten();
		model = new Model();
		
		model.createWunschliste(new Wunschliste(1, "Test", "Description"));
	}
	
	public void start()
	{
		viewWunschlisteErstellen.setVisible(true);
	}
}
