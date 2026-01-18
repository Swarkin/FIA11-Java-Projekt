package frontend;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ViewWunschlisteBearbeiten extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblWunschlisteName;
	private JLabel lblWunschlisteDescription;
	private JLabel lblWunschname;
	private JList<WunschlisteEintrag> listEintraege;
	private JTextField txtWunschname;
	private JButton btnWunschHinzufügen;
	private JButton btnWunschDelete;
	private JLabel lblStatus;
	private JButton btnZurueck;

	public DefaultListModel<WunschlisteEintrag> listEintraegeModel;
	public ButtonModel btnWunschHinzufügenModel;
	public ButtonModel btnWunschDeleteModel;
	public ButtonModel btnZurueckModel;

	public ViewWunschlisteBearbeiten()
	{
		setTitle("Wunschliste bearbeiten");
		initialize();
	}

	public void setzeWunschliste(Wunschliste wunschliste)
	{
		lblWunschlisteName.setText(wunschliste.getName());
		lblWunschlisteDescription.setText(wunschliste.getBeschreibung());
		listEintraegeModel.addAll(wunschliste.getItems());
	}

	public void reset()
	{
		lblWunschlisteName.setText("");
		lblWunschlisteDescription.setText("");
		lblStatus.setText("");
		txtWunschname.setText("");
		listEintraegeModel.removeAllElements();
		btnWunschErstellenKonfiguieren("Wunsch hinzufügen", true);
	}

	public void setStatusText(String text)
	{
		lblStatus.setText(text);
	}

	public String getWunschErstellenName()
	{
		return txtWunschname.getText();
	}

	public void btnWunschErstellenKonfiguieren(String text, boolean enabled)
	{
		btnWunschHinzufügen.setText(text);
		btnWunschHinzufügen.setEnabled(enabled);
	}

	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 150, 150, 0 };
		gbl_contentPane.rowHeights = new int[] { 12, 20, 10, 150, 20, 10, 31, 10, 31, 0, 10, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_lblWunschlisteName = new GridBagConstraints();
		gbc_lblWunschlisteName.gridwidth = 2;
		gbc_lblWunschlisteName.fill = GridBagConstraints.BOTH;
		gbc_lblWunschlisteName.insets = new Insets(0, 0, 5, 0);
		gbc_lblWunschlisteName.gridx = 0;
		gbc_lblWunschlisteName.gridy = 0;
		contentPane.add(getLblWunschlisteName(), gbc_lblWunschlisteName);
		GridBagConstraints gbc_lblWunschlisteDescription = new GridBagConstraints();
		gbc_lblWunschlisteDescription.gridwidth = 2;
		gbc_lblWunschlisteDescription.anchor = GridBagConstraints.SOUTH;
		gbc_lblWunschlisteDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWunschlisteDescription.insets = new Insets(0, 0, 5, 0);
		gbc_lblWunschlisteDescription.gridx = 0;
		gbc_lblWunschlisteDescription.gridy = 1;
		contentPane.add(getLblWunschlisteDescription(), gbc_lblWunschlisteDescription);
		GridBagConstraints gbc_listEintraege = new GridBagConstraints();
		gbc_listEintraege.gridwidth = 2;
		gbc_listEintraege.fill = GridBagConstraints.BOTH;
		gbc_listEintraege.insets = new Insets(0, 0, 5, 0);
		gbc_listEintraege.gridx = 0;
		gbc_listEintraege.gridy = 3;
		contentPane.add(getListEintraege(), gbc_listEintraege);
		GridBagConstraints gbc_lblWunschname = new GridBagConstraints();
		gbc_lblWunschname.fill = GridBagConstraints.BOTH;
		gbc_lblWunschname.insets = new Insets(0, 0, 5, 5);
		gbc_lblWunschname.gridx = 0;
		gbc_lblWunschname.gridy = 5;
		contentPane.add(getLblWunschname(), gbc_lblWunschname);
		GridBagConstraints gbc_txtWunschname = new GridBagConstraints();
		gbc_txtWunschname.fill = GridBagConstraints.BOTH;
		gbc_txtWunschname.insets = new Insets(0, 0, 5, 0);
		gbc_txtWunschname.gridx = 1;
		gbc_txtWunschname.gridy = 5;
		contentPane.add(getTxtWunschname(), gbc_txtWunschname);
		GridBagConstraints gbc_btnWunschHinzufügen = new GridBagConstraints();
		gbc_btnWunschHinzufügen.fill = GridBagConstraints.BOTH;
		gbc_btnWunschHinzufügen.insets = new Insets(0, 0, 5, 0);
		gbc_btnWunschHinzufügen.gridwidth = 2;
		gbc_btnWunschHinzufügen.gridx = 0;
		gbc_btnWunschHinzufügen.gridy = 6;
		contentPane.add(getBtnWunschHinzufügen(), gbc_btnWunschHinzufügen);
		GridBagConstraints gbc_btnWunschDelete = new GridBagConstraints();
		gbc_btnWunschDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnWunschDelete.fill = GridBagConstraints.BOTH;
		gbc_btnWunschDelete.gridwidth = 2;
		gbc_btnWunschDelete.gridx = 0;
		gbc_btnWunschDelete.gridy = 8;
		contentPane.add(getBtnWunschDelete(), gbc_btnWunschDelete);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 9;
		contentPane.add(getLblStatus(), gbc_lblStatus);
		GridBagConstraints gbc_btnZurueck = new GridBagConstraints();
		gbc_btnZurueck.anchor = GridBagConstraints.EAST;
		gbc_btnZurueck.gridwidth = 2;
		gbc_btnZurueck.gridx = 0;
		gbc_btnZurueck.gridy = 11;
		contentPane.add(getBtnZurueck(), gbc_btnZurueck);
	}

	private JLabel getLblWunschlisteName()
	{
		if (lblWunschlisteName == null)
		{
			lblWunschlisteName = new JLabel("Titel");
			lblWunschlisteName.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return lblWunschlisteName;
	}

	private JButton getBtnWunschHinzufügen()
	{
		if (btnWunschHinzufügen == null)
		{
			btnWunschHinzufügen = new JButton("Wunsch hinzufügen");
			btnWunschHinzufügen.setFont(new Font("Arial", Font.PLAIN, 14));
			btnWunschHinzufügenModel = btnWunschHinzufügen.getModel();
		}
		return btnWunschHinzufügen;
	}

	private JLabel getLblWunschlisteDescription()
	{
		if (lblWunschlisteDescription == null)
		{
			lblWunschlisteDescription = new JLabel("Beschreibung");
			lblWunschlisteDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return lblWunschlisteDescription;
	}

	private JButton getBtnWunschDelete()
	{
		if (btnWunschDelete == null)
		{
			btnWunschDelete = new JButton("Wunsch löschen");
			btnWunschDelete.setFont(new Font("Arial", Font.PLAIN, 14));
			btnWunschDeleteModel = btnWunschDelete.getModel();
		}
		return btnWunschDelete;
	}

	private JList<WunschlisteEintrag> getListEintraege()
	{
		if (listEintraege == null)
		{
			listEintraegeModel = new DefaultListModel<WunschlisteEintrag>();
			listEintraege = new JList<WunschlisteEintrag>(listEintraegeModel);
		}
		return listEintraege;
	}

	private JTextField getTxtWunschname()
	{
		if (txtWunschname == null)
		{
			txtWunschname = new JTextField();
			txtWunschname.setColumns(10);
		}
		return txtWunschname;
	}

	private JLabel getLblWunschname()
	{
		if (lblWunschname == null)
		{
			lblWunschname = new JLabel("Wunsch hinzufügen:");
			lblWunschname.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return lblWunschname;
	}

	private JButton getBtnZurueck()
	{
		if (btnZurueck == null)
		{
			btnZurueck = new JButton("Zurück");
			btnZurueck.setFont(new Font("Arial", Font.PLAIN, 12));
			btnZurueckModel = btnZurueck.getModel();
		}
		return btnZurueck;
	}

	private JLabel getLblStatus()
	{
		if (lblStatus == null)
		{
			lblStatus = new JLabel("New label");
			lblStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return lblStatus;
	}
}
