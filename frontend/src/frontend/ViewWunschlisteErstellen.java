package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ViewWunschlisteErstellen extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblTitel;
	private JLabel lblWunschlisteName;
	private JTextField txtWunschlisteName;
	private JLabel lblWunschlisteBeschreibung;
	private JTextArea txtWunschlisteBeschreibung;
	private JButton btnWunschlisteErstellen;
	private JButton btnZurueck;
	
	public ButtonModel btnZurueckModel;
	public ButtonModel btnErstellenModel;
	private JLabel lblStatus;

	public ViewWunschlisteErstellen()
	{
		setTitle("Wunschliste erstellen");
		setResizable(false);
		initialize();
	}

	public void reset() {
		txtWunschlisteName.setText("");
		txtWunschlisteBeschreibung.setText("");
		lblStatus.setText("");
		btnWunschlisteErstellenKonfiguieren("Wunschliste erstellen", true);
	}
	
	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 235, 20, 0};
		gbl_contentPane.rowHeights = new int[]{20, 23, 20, 0, 0, 10, 0, 0, 20, 23, 23, 0, 0, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_lblTitel = new GridBagConstraints();
		gbc_lblTitel.fill = GridBagConstraints.BOTH;
		gbc_lblTitel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitel.gridx = 1;
		gbc_lblTitel.gridy = 1;
		contentPane.add(getLblTitel(), gbc_lblTitel);
		GridBagConstraints gbc_lblWunschlisteName = new GridBagConstraints();
		gbc_lblWunschlisteName.fill = GridBagConstraints.BOTH;
		gbc_lblWunschlisteName.insets = new Insets(0, 0, 5, 5);
		gbc_lblWunschlisteName.gridx = 1;
		gbc_lblWunschlisteName.gridy = 3;
		contentPane.add(getLblWunschlisteName(), gbc_lblWunschlisteName);
		GridBagConstraints gbc_txtWunschlisteName = new GridBagConstraints();
		gbc_txtWunschlisteName.fill = GridBagConstraints.BOTH;
		gbc_txtWunschlisteName.insets = new Insets(0, 0, 5, 5);
		gbc_txtWunschlisteName.gridx = 1;
		gbc_txtWunschlisteName.gridy = 4;
		contentPane.add(getTxtWunschlisteName(), gbc_txtWunschlisteName);
		GridBagConstraints gbc_lblWunschlisteBeschreibung = new GridBagConstraints();
		gbc_lblWunschlisteBeschreibung.anchor = GridBagConstraints.WEST;
		gbc_lblWunschlisteBeschreibung.fill = GridBagConstraints.VERTICAL;
		gbc_lblWunschlisteBeschreibung.insets = new Insets(0, 0, 5, 5);
		gbc_lblWunschlisteBeschreibung.gridx = 1;
		gbc_lblWunschlisteBeschreibung.gridy = 6;
		contentPane.add(getLblWunschlisteBeschreibung(), gbc_lblWunschlisteBeschreibung);
		GridBagConstraints gbc_txtWunschlisteBeschreibung = new GridBagConstraints();
		gbc_txtWunschlisteBeschreibung.fill = GridBagConstraints.BOTH;
		gbc_txtWunschlisteBeschreibung.insets = new Insets(0, 0, 5, 5);
		gbc_txtWunschlisteBeschreibung.gridx = 1;
		gbc_txtWunschlisteBeschreibung.gridy = 7;
		contentPane.add(getTxtWunschlisteBeschreibung(), gbc_txtWunschlisteBeschreibung);
		GridBagConstraints gbc_btnWunschlisteErstellen = new GridBagConstraints();
		gbc_btnWunschlisteErstellen.fill = GridBagConstraints.BOTH;
		gbc_btnWunschlisteErstellen.insets = new Insets(0, 0, 5, 5);
		gbc_btnWunschlisteErstellen.gridx = 1;
		gbc_btnWunschlisteErstellen.gridy = 9;
		contentPane.add(getBtnWunschlisteErstellen(), gbc_btnWunschlisteErstellen);
		GridBagConstraints gbc_btnZurueck = new GridBagConstraints();
		gbc_btnZurueck.insets = new Insets(0, 0, 5, 5);
		gbc_btnZurueck.fill = GridBagConstraints.BOTH;
		gbc_btnZurueck.gridx = 1;
		gbc_btnZurueck.gridy = 10;
		contentPane.add(getBtnZurueck(), gbc_btnZurueck);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 1;
		gbc_lblStatus.gridy = 12;
		contentPane.add(getLblStatus(), gbc_lblStatus);
	}
	
	public String getWunschlisteName()
	{
		return txtWunschlisteName.getText();
	}
	
	public String getWunschlisteBeschreibung()
	{
		return txtWunschlisteBeschreibung.getText();
	}
	
	public void btnWunschlisteErstellenKonfiguieren(String text, boolean enabled)
	{
		btnWunschlisteErstellen.setText(text);
		btnWunschlisteErstellen.setEnabled(enabled);
	}
	
	public void setStatusText(String s)
	{
		lblStatus.setText(s);
	}
	
	private JLabel getLblTitel()
	{
		if (lblTitel == null)
		{
			lblTitel = new JLabel("Erstelle deine Wunschliste");
			lblTitel.setFont(new Font("Arial", Font.PLAIN, 16));
		}
		return lblTitel;
	}
	private JLabel getLblWunschlisteName()
	{
		if (lblWunschlisteName == null)
		{
			lblWunschlisteName = new JLabel("Name");
			lblWunschlisteName.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return lblWunschlisteName;
	}
	private JTextField getTxtWunschlisteName()
	{
		if (txtWunschlisteName == null)
		{
			txtWunschlisteName = new JTextField();
			txtWunschlisteName.setToolTipText("z.B. Geburtstag...");
			txtWunschlisteName.setColumns(10);
		}
		return txtWunschlisteName;
	}
	private JLabel getLblWunschlisteBeschreibung()
	{
		if (lblWunschlisteBeschreibung == null)
		{
			lblWunschlisteBeschreibung = new JLabel("Beschreibung");
			lblWunschlisteBeschreibung.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return lblWunschlisteBeschreibung;
	}
	private JTextArea getTxtWunschlisteBeschreibung()
	{
		if (txtWunschlisteBeschreibung == null)
		{
			txtWunschlisteBeschreibung = new JTextArea();
			txtWunschlisteBeschreibung.setLineWrap(true);
		}
		return txtWunschlisteBeschreibung;
	}
	private JButton getBtnWunschlisteErstellen()
	{
		if (btnWunschlisteErstellen == null)
		{
			btnWunschlisteErstellen = new JButton("Wunschliste erstellen");
			btnWunschlisteErstellen.setFont(new Font("Arial", Font.PLAIN, 12));
			btnErstellenModel = btnWunschlisteErstellen.getModel();
		}
		return btnWunschlisteErstellen;
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
	private JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("");
		}
		return lblStatus;
	}
}
