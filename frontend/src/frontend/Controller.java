package frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;

import WunschlistenAPI.WunschlisteCreate;

public class Controller
{
	private Model model;
	
	private ViewAlleWunschlisten viewAlleWunschlisten;
	private ViewWunschlisteErstellen viewWunschlisteErstellen;
	private ViewWunschlisteBearbeiten viewWunschlisteBearbeiten;
	
	public Controller()
	{
		model = new Model();
		
		// Konfiguration laden
		try
		{
			model.ladeConfig();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		// Dark Mode durch externe Bibliothek
		try
		{
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		}
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		
		// Views vorbereiten
		
		viewAlleWunschlisten = new ViewAlleWunschlisten();
		viewAlleWunschlisten.btnWunschlisteErstellenModel.addActionListener(_e -> {
			viewAlleWunschlisten.setVisible(false);
			viewWunschlisteErstellen.reset();
			viewWunschlisteErstellen.setVisible(true);
		});
		viewAlleWunschlisten.btnWunschlisteBearbeitenModel.addActionListener(_e -> {
			Wunschliste w = viewAlleWunschlisten.getSelectedWunschliste();
			if (w != null) {
				viewAlleWunschlisten.setVisible(false);
				viewWunschlisteBearbeiten.setzeWunschliste(w);
				viewWunschlisteBearbeiten.setVisible(true);
			}
		});
		viewAlleWunschlisten.btnSpeichernModel.addActionListener(_e -> {
			try {
				model.speichereConfig();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString(), "Fehler beim Speichern", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		viewWunschlisteErstellen = new ViewWunschlisteErstellen();
		viewWunschlisteErstellen.btnZurueckModel.addActionListener(_e -> {
			viewWunschlisteErstellen.setVisible(false);
			viewAlleWunschlisten.setVisible(true);
		});
		viewWunschlisteErstellen.btnErstellenModel.addActionListener(_e -> {
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
		});
		
		viewWunschlisteBearbeiten = new ViewWunschlisteBearbeiten();
	}
	
	public void start()
	{
		viewAlleWunschlisten.setVisible(true);
	}
}
