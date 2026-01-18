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
import WunschlistenAPI.WunschlisteCreateEintrag;
import WunschlistenAPI.WunschlisteDeleteEintrag;
import WunschlistenAPI.WunschlisteEintragCreate;

public class Controller
{
	private Model model;

	private ViewAlleWunschlisten viewAlleWunschlisten;
	private ViewWunschlisteErstellen viewWunschlisteErstellen;
	private ViewWunschlisteBearbeiten viewWunschlisteBearbeiten;

	private int bearbeitendeWunschliste = -1;

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

					viewAlleWunschlisten.aktualisiereWunschlisten(model.getLokaleWunschlisten());
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
				bearbeitendeWunschliste = w.getId();
				viewWunschlisteBearbeiten.reset();
				viewWunschlisteBearbeiten.setzeWunschliste(w);
				viewWunschlisteBearbeiten.setVisible(true);
			}
		});
		viewAlleWunschlisten.btnWunschlisteLöschenModel.addActionListener(_e -> {
			Wunschliste w = viewAlleWunschlisten.getSelectedWunschliste();
			if (w != null)
			{
				int input = JOptionPane.showConfirmDialog(null, "Löschen der Wunschliste bestätigen?", "Wunschliste löschen", JOptionPane.CANCEL_OPTION);
				if (input == JOptionPane.YES_OPTION)
				{
					SwingWorker<Void, Void> worker = new SwingWorker<>()
					{
						@Override
						protected Void doInBackground() throws Exception
						{
							model.deleteWunschliste(w.getId());
							viewAlleWunschlisten.aktualisiereWunschlisten(model.getLokaleWunschlisten());
							return null;
						}

						@Override
						protected void done()
						{
							try
							{
								get();
								JOptionPane.showMessageDialog(null, "Wunschliste gelöscht.", "Wunschliste löschen", JOptionPane.INFORMATION_MESSAGE);
							} catch (InterruptedException | ExecutionException e)
							{
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "Wunschliste konnte nicht gelöscht werden:\n" + e.toString(), "Wunschliste löschen",
									JOptionPane.ERROR_MESSAGE);
							}
						}
					};

					worker.execute();
				}
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
			viewAlleWunschlisten.aktualisiereWunschlisten(model.getLokaleWunschlisten());
			viewAlleWunschlisten.setVisible(true);
		});
		viewWunschlisteErstellen.btnErstellenModel.addActionListener(_e -> {
			String name = viewWunschlisteErstellen.getWunschlisteName();
			String description = viewWunschlisteErstellen.getWunschlisteBeschreibung();
			List<WunschlisteEintrag> items = new ArrayList<WunschlisteEintrag>();

			if (name.isEmpty() || description.isEmpty())
			{
				viewWunschlisteErstellen.setStatusText("Name/Beschreibung fehlt!");
				viewWunschlisteErstellen.btnWunschlisteErstellenKonfiguieren("Erneut versuchen", true);
				return;
			} else
			{
				viewWunschlisteErstellen.setStatusText("");
				viewWunschlisteErstellen.btnWunschlisteErstellenKonfiguieren("Wird erstellt...", false);
			}

			WunschlisteCreate wc = new WunschlisteCreate(name, description, items);

			SwingWorker<Void, Void> worker = new SwingWorker<>()
			{
				@Override
				protected Void doInBackground() throws Exception
				{
					model.createWunschliste(wc);
					return null;
				}

				@Override
				protected void done()
				{
					try
					{
						get();
						viewWunschlisteErstellen.btnWunschlisteErstellenKonfiguieren("Wunschliste erstellt!", false);
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
		viewWunschlisteBearbeiten.btnZurueckModel.addActionListener(_e -> {
			viewWunschlisteBearbeiten.setVisible(false);
			bearbeitendeWunschliste = -1;
			viewAlleWunschlisten.setVisible(true);
		});
		viewWunschlisteBearbeiten.btnWunschHinzufügenModel.addActionListener(_e -> {
			String wunsch = viewWunschlisteBearbeiten.getWunschErstellenName();

			if (wunsch.isEmpty())
			{
				viewWunschlisteBearbeiten.setStatusText("Name/Beschreibung fehlt!");
				return;
			} else
			{
				viewWunschlisteBearbeiten.setStatusText("");
				viewWunschlisteBearbeiten.btnWunschErstellenKonfiguieren("Wird erstellt...", false);
			}

			WunschlisteCreateEintrag wec = new WunschlisteCreateEintrag(bearbeitendeWunschliste, new WunschlisteEintragCreate(wunsch));

			SwingWorker<WunschlisteEintrag, Void> worker = new SwingWorker<>()
			{
				@Override
				protected WunschlisteEintrag doInBackground() throws Exception
				{
					WunschlisteEintrag we = model.createWunschlisteEintrag(wec);
					return we;
				}

				@Override
				protected void done()
				{
					try
					{
						WunschlisteEintrag we = get();
						viewWunschlisteBearbeiten.setStatusText("Eintrag erstellt!");
						viewWunschlisteBearbeiten.listEintraegeModel.addElement(we);
					} catch (InterruptedException | ExecutionException e)
					{
						e.printStackTrace();
						viewWunschlisteBearbeiten.setStatusText(e.toString());
					} finally
					{
						viewWunschlisteBearbeiten.btnWunschErstellenKonfiguieren("Wunsch hinzufügen", true);
					}
				}
			};

			worker.execute();
		});
		viewWunschlisteBearbeiten.btnWunschDeleteModel.addActionListener(_e -> {
			WunschlisteEintrag we = viewWunschlisteBearbeiten.getSelectedWunschlisteEintrag();

			if (we == null)
			{
				viewWunschlisteBearbeiten.setStatusText("Kein Wunsch ausgewählt");
				return;
			} else
			{
				viewWunschlisteBearbeiten.setStatusText("");
			}

			WunschlisteDeleteEintrag wde = new WunschlisteDeleteEintrag(bearbeitendeWunschliste, we.getId());

			SwingWorker<Void, Void> worker = new SwingWorker<>()
			{
				@Override
				protected Void doInBackground() throws Exception
				{
					model.deleteWunschlisteEintrag(wde);
					return null;
				}

				@Override
				protected void done()
				{
					try
					{
						get();
						viewWunschlisteBearbeiten.setStatusText("Eintrag entfernt!");
						viewWunschlisteBearbeiten.listEintraegeModel.removeElement(we);
					} catch (InterruptedException | ExecutionException e)
					{
						e.printStackTrace();
						viewWunschlisteBearbeiten.setStatusText(e.toString());
					} finally
					{
						viewWunschlisteBearbeiten.btnWunschEntfernenKonfiguieren("Wunsch entfernen", true);
					}
				}
			};

			worker.execute();
		});
	}
}
