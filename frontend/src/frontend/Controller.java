package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Controller
{
	private Model model;
	
	private ViewWunschlisteErstellen viewWunschlisteErstellen;
	private ViewAlleWunschlisten viewAlleWunschlisten;
	
	public Controller()
	{
		model = new Model();
		
		viewWunschlisteErstellen = new ViewWunschlisteErstellen();
		viewWunschlisteErstellen.btnZurueckModel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				viewWunschlisteErstellen.setVisible(false);
				viewAlleWunschlisten.setVisible(true);
			}
		});
		viewWunschlisteErstellen.btnErstellenModel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				WunschlisteCreate w = new WunschlisteCreate(null, null); //
				System.out.println(w);
			}
		});
		
		viewAlleWunschlisten = new ViewAlleWunschlisten();
		viewAlleWunschlisten.btnNeueWunschlisteModel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				viewWunschlisteErstellen.setVisible(true);
				viewAlleWunschlisten.setVisible(false);
			}
		});
	}
	
	public void start()
	{
		viewWunschlisteErstellen.setVisible(true);
	}
}
