package frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;

import WunschlistenAPI.ApiException;
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

		configLaden();
		darkMode();
		viewsVorbereiten();
	}

	public void start()
	{
		ListIterator<Integer> listen = model.config.getListenIDs();
		int gesamt = model.config.getListenIDsAnzahl();

		if (gesamt > 0)
		{
			int i = 1;
			ViewWunschlistenLaden viewWunschlistenLaden = new ViewWunschlistenLaden();
			viewWunschlistenLaden.setzeFortschrittText(i, gesamt);
			viewWunschlistenLaden.setVisible(true);

			SwingWorker<Void, Integer> worker = new SwingWorker<>()
			{
				@Override
				protected Void doInBackground() throws Exception
				{
					ListIterator<Integer> listen = model.config.getListenIDs();
					int i = 1;

					while (listen.hasNext())
					{
						Integer id = listen.next();
						try
						{
							model.getWunschliste(id);
						} catch (ApiException e)
						{
							if (e.getStatus() != 404)
							{
								e.printStackTrace();
							}
						}
						publish(i++);
					}
					return null;
				}

				@Override
				protected void process(List<Integer> chunks)
				{
					int aktuellerWert = chunks.get(chunks.size() - 1);
					viewWunschlistenLaden.setzeFortschrittText(aktuellerWert, gesamt);
				}

				@Override
				protected void done()
				{
					viewWunschlistenLaden.dispose();
					viewAlleWunschlisten.setVisible(true);
				}
			};

			worker.execute();
		} else
		{
			viewAlleWunschlisten.setVisible(true);
		}
	}

	private void configLaden()
	{
		try
		{
			model.ladeConfig();
		} catch (IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(), "Fehler beim Laden der Config", JOptionPane.ERROR_MESSAGE);
			model.config = new Config();
		}
	}

	private void darkMode()
	{
		try
		{
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}

	private void viewsVorbereiten()
	{
		viewAlleWunschlisten = new ViewAlleWunschlisten();
		viewAlleWunschlisten.btnWunschlisteErstellenModel.addActionListener(_e -> {
			viewAlleWunschlisten.setVisible(false);
			viewWunschlisteErstellen.reset();
			viewWunschlisteErstellen.setVisible(true);
		});
		viewAlleWunschlisten.btnWunschlisteBearbeitenModel.addActionListener(_e -> {
			Wunschliste w = viewAlleWunschlisten.getSelectedWunschliste();
			if (w != null)
			{
				viewAlleWunschlisten.setVisible(false);
				viewWunschlisteBearbeiten.setzeWunschliste(w);
				viewWunschlisteBearbeiten.setVisible(true);
			}
		});
		viewAlleWunschlisten.btnSpeichernModel.addActionListener(_e -> {
			try
			{
				model.speichereConfig();
			} catch (IOException e)
			{
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
			} else
			{
				viewWunschlisteErstellen.setStatusText("");
				viewWunschlisteErstellen.btnWunschlisteErstellenKonfiguieren("Wird erstellt...", false);
			}

			try
			{
				WunschlisteCreate wc = new WunschlisteCreate(name, description, items);
				Wunschliste w = model.createWunschliste(wc);
				viewWunschlisteErstellen.setStatusText("Wunschliste erstellt mit ID " + w.getId());
			} catch (ApiException | IOException | InterruptedException err)
			{
				err.printStackTrace();
				viewWunschlisteErstellen.setStatusText(err.toString());
			}
		});

		viewWunschlisteBearbeiten = new ViewWunschlisteBearbeiten();
	}
}
