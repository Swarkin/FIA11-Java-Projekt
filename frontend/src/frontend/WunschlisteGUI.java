package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class WunschlisteGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNameDerWunschliste;
	private JTextField txtZbGeburtstag;
	private JLabel lblS;
	private JButton btnNewButton;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WunschlisteGUI frame = new WunschlisteGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WunschlisteGUI() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 287, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNameDerWunschliste());
		contentPane.add(getTxtZbGeburtstag());
		contentPane.add(getLblS());
		contentPane.add(getBtnNewButton());
		contentPane.add(getTextField());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Erstelle deine Wunschliste");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
			lblNewLabel.setBounds(36, 40, 190, 46);
		}
		return lblNewLabel;
	}
	private JLabel getLblNameDerWunschliste() {
		if (lblNameDerWunschliste == null) {
			lblNameDerWunschliste = new JLabel("Name der Wunschliste");
			lblNameDerWunschliste.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNameDerWunschliste.setBounds(36, 96, 125, 23);
		}
		return lblNameDerWunschliste;
	}
	private JTextField getTxtZbGeburtstag() {
		if (txtZbGeburtstag == null) {
			txtZbGeburtstag = new JTextField();
			txtZbGeburtstag.setText("z.B. Geburtstag...");
			txtZbGeburtstag.setBounds(36, 117, 177, 18);
			txtZbGeburtstag.setColumns(10);
		}
		return txtZbGeburtstag;
	}
	private JLabel getLblS() {
		if (lblS == null) {
			lblS = new JLabel("Beschreibung");
			lblS.setFont(new Font("Arial", Font.PLAIN, 12));
			lblS.setBounds(36, 154, 81, 23);
		}
		return lblS;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Wunschliste erstellen");
			btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
			btnNewButton.setBounds(36, 261, 177, 23);
		}
		return btnNewButton;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(36, 175, 177, 68);
		}
		return textField;
	}
}
