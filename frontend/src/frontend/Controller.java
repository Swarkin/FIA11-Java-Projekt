package frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

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
					int gesamt = model.config.getListenIDsAnzahl();
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
			viewAlleWunschlisten.listWunschlistenModel.removeAllElements();
			viewAlleWunschlisten.listWunschlistenModel.addAll(model.getLokaleWunschlisten());
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

			WunschlisteCreate wc = new WunschlisteCreate(name, description, items);

			SwingWorker<Void, Wunschliste> worker = new SwingWorker<>()
			{
				@Override
				protected Void doInBackground() throws Exception
				{
					Wunschliste w = model.createWunschliste(wc);
					publish(w);
					return null;
				}

				@Override
				protected void done()
				{
					try
					{
						get();
						viewWunschlisteErstellen.setStatusText("Wunschliste erstellt!");
					} catch (InterruptedException e)
					{
						e.printStackTrace();
						viewWunschlisteErstellen.setStatusText(e.toString());
					} catch (ExecutionException e)
					{
						Throwable t = e.getCause();
						if (t != null)
						{
							t.printStackTrace();
							viewWunschlisteErstellen.setStatusText(t.toString());
						}
					} catch (Exception e)
					{
						e.printStackTrace();
						viewWunschlisteErstellen.setStatusText(e.toString());
					}
				}
			};

			worker.execute();
		});

		viewWunschlisteBearbeiten = new ViewWunschlisteBearbeiten();
	}
}
