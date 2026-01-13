package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;

public class Controller
{
	private Model model;
	
	private ViewWunschlisteErstellen viewWunschlisteErstellen;
	private ViewAlleWunschlisten viewAlleWunschlisten;
	
	public Controller()
	{
		model = new Model();
		
		// Dark mode
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
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
				String name = viewWunschlisteErstellen.getWunschlisteName();
				String description = viewWunschlisteErstellen.getWunschlisteBeschreibung();
				List<WunschlisteEintrag> items = new ArrayList<WunschlisteEintrag>();
				
				if (name.length() == 0 || description.length() == 0)
				{
					viewWunschlisteErstellen.setStatusText("Name/Beschreibung fehlt!");
					return;
				}
				else
				{
					viewWunschlisteErstellen.btnWunschlisteErstellenKonfiguieren("Wird erstellt...", false);
				}
				
				try
				{
					WunschlisteCreate wc = new WunschlisteCreate(name, description, items);
					Wunschliste w = model.createWunschliste(wc);
					viewWunschlisteErstellen.setStatusText("Wunschliste erstellt mit ID " + w.getId());
				}
				catch (IOException err)
				{
					viewWunschlisteErstellen.setStatusText(err.toString());
					err.printStackTrace();
				}
				catch (InterruptedException err)
				{
					err.printStackTrace();
					viewWunschlisteErstellen.setStatusText(err.toString());
				}
			}
		});
		
		viewAlleWunschlisten = new ViewAlleWunschlisten();
		viewAlleWunschlisten.btnNeueWunschlisteModel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				viewWunschlisteErstellen.reset();
				viewWunschlisteErstellen.setVisible(true);
				viewAlleWunschlisten.setVisible(false);
			}
		});
	}
	
	public void start()
	{
		viewAlleWunschlisten.setVisible(true);
	}
}
