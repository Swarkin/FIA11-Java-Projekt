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

public class ViewWunschlisteErstellen extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNameDerWunschliste;
	private JTextField txtZbGeburtstag;
	private JLabel lblS;
	private JTextArea textArea;
	private JButton btnWunschlisteErstellen;
	private JButton btnZurueck;
	
	public ButtonModel btnZurueckModel;
	public ButtonModel btnErstellenModel;

	public ViewWunschlisteErstellen()
	{
		initialize();
	}

	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 260, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNameDerWunschliste());
		contentPane.add(getTxtZbGeburtstag());
		contentPane.add(getLblS());
		contentPane.add(getBtnWunschlisteErstellen());
		contentPane.add(getBtnZurueck());
		contentPane.add(getTextArea());
	}
	private JLabel getLblNewLabel()
	{
		if (lblNewLabel == null)
		{
			lblNewLabel = new JLabel("Erstelle deine Wunschliste");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
			lblNewLabel.setBounds(23, 23, 190, 23);
		}
		return lblNewLabel;
	}
	private JLabel getLblNameDerWunschliste()
	{
		if (lblNameDerWunschliste == null)
		{
			lblNameDerWunschliste = new JLabel("Name der Wunschliste");
			lblNameDerWunschliste.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNameDerWunschliste.setBounds(23, 57, 177, 23);
		}
		return lblNameDerWunschliste;
	}
	private JTextField getTxtZbGeburtstag()
	{
		if (txtZbGeburtstag == null)
		{
			txtZbGeburtstag = new JTextField();
			txtZbGeburtstag.setToolTipText("z.B. Geburtstag...");
			txtZbGeburtstag.setBounds(23, 78, 177, 18);
			txtZbGeburtstag.setColumns(10);
		}
		return txtZbGeburtstag;
	}
	private JLabel getLblS()
	{
		if (lblS == null)
		{
			lblS = new JLabel("Beschreibung");
			lblS.setFont(new Font("Arial", Font.PLAIN, 12));
			lblS.setBounds(23, 107, 81, 23);
		}
		return lblS;
	}
	private JTextArea getTextArea()
	{
		if (textArea == null)
		{
			textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setBounds(23, 128, 177, 53);
		}
		return textArea;
	}
	private JButton getBtnWunschlisteErstellen()
	{
		if (btnWunschlisteErstellen == null)
		{
			btnWunschlisteErstellen = new JButton("Wunschliste erstellen");
			btnWunschlisteErstellen.setFont(new Font("Arial", Font.PLAIN, 12));
			btnWunschlisteErstellen.setBounds(23, 199, 177, 23);
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
			btnZurueck.setBounds(23, 227, 177, 23);
			btnZurueckModel = btnZurueck.getModel();
		}
		return btnZurueck;
	}
}
