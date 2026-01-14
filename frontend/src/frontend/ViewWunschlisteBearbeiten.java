package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ViewWunschlisteBearbeiten extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Wunschliste wunschliste;

	private JPanel contentPane;
	private JLabel lblWunschlisteName;
	private JLabel lblWunschlisteDescription;
	private JLabel lblWunschname;
	private JList<Wunschliste> listEintraege;
	private JButton btnWunschHinzufügen;
	private JButton btnWunschDelete;
	private JTextField txtWunschname;

	public ViewWunschlisteBearbeiten()
	{
		setTitle("Wunschliste");
		initialize();
	}
	
	public void setzeWunschliste(Wunschliste wunschliste)
	{
		this.wunschliste = wunschliste;
		this.lblWunschlisteName.setText(wunschliste.getName());
		this.lblWunschlisteDescription.setText(wunschliste.getBeschreibung());
	}
	
	public void reset()
	{
		wunschliste = null;
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 150, 100, 20, 0};
		gbl_contentPane.rowHeights = new int[]{20, 12, 20, 10, 150, 20, 10, 31, 10, 31, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_lblWunschlisteName = new GridBagConstraints();
		gbc_lblWunschlisteName.gridwidth = 2;
		gbc_lblWunschlisteName.fill = GridBagConstraints.BOTH;
		gbc_lblWunschlisteName.insets = new Insets(0, 0, 5, 5);
		gbc_lblWunschlisteName.gridx = 1;
		gbc_lblWunschlisteName.gridy = 1;
		contentPane.add(getLblWunschlisteName(), gbc_lblWunschlisteName);
		GridBagConstraints gbc_lblWunschlisteDescription = new GridBagConstraints();
		gbc_lblWunschlisteDescription.gridwidth = 2;
		gbc_lblWunschlisteDescription.anchor = GridBagConstraints.SOUTH;
		gbc_lblWunschlisteDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWunschlisteDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblWunschlisteDescription.gridx = 1;
		gbc_lblWunschlisteDescription.gridy = 2;
		contentPane.add(getLblWunschlisteDescription(), gbc_lblWunschlisteDescription);
		GridBagConstraints gbc_listEintraege = new GridBagConstraints();
		gbc_listEintraege.gridwidth = 2;
		gbc_listEintraege.fill = GridBagConstraints.BOTH;
		gbc_listEintraege.insets = new Insets(0, 0, 5, 5);
		gbc_listEintraege.gridx = 1;
		gbc_listEintraege.gridy = 4;
		contentPane.add(getListEintraege(), gbc_listEintraege);
		GridBagConstraints gbc_lblWunschname = new GridBagConstraints();
		gbc_lblWunschname.fill = GridBagConstraints.BOTH;
		gbc_lblWunschname.insets = new Insets(0, 0, 5, 5);
		gbc_lblWunschname.gridx = 1;
		gbc_lblWunschname.gridy = 6;
		contentPane.add(getLblWunschname(), gbc_lblWunschname);
		GridBagConstraints gbc_txtWunschname = new GridBagConstraints();
		gbc_txtWunschname.fill = GridBagConstraints.BOTH;
		gbc_txtWunschname.insets = new Insets(0, 0, 5, 5);
		gbc_txtWunschname.gridx = 2;
		gbc_txtWunschname.gridy = 6;
		contentPane.add(getTxtWunschname(), gbc_txtWunschname);
		GridBagConstraints gbc_btnWunschHinzufügen = new GridBagConstraints();
		gbc_btnWunschHinzufügen.fill = GridBagConstraints.BOTH;
		gbc_btnWunschHinzufügen.insets = new Insets(0, 0, 5, 5);
		gbc_btnWunschHinzufügen.gridwidth = 2;
		gbc_btnWunschHinzufügen.gridx = 1;
		gbc_btnWunschHinzufügen.gridy = 7;
		contentPane.add(getBtnWunschHinzufügen(), gbc_btnWunschHinzufügen);
		GridBagConstraints gbc_btnWunschDelete = new GridBagConstraints();
		gbc_btnWunschDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnWunschDelete.fill = GridBagConstraints.BOTH;
		gbc_btnWunschDelete.gridwidth = 2;
		gbc_btnWunschDelete.gridx = 1;
		gbc_btnWunschDelete.gridy = 9;
		contentPane.add(getBtnWunschDelete(), gbc_btnWunschDelete);
	}

	private JLabel getLblWunschlisteName() {
		if (lblWunschlisteName == null) {
			lblWunschlisteName = new JLabel("Titel");
			lblWunschlisteName.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return lblWunschlisteName;
	}
	private JButton getBtnWunschHinzufügen() {
		if (btnWunschHinzufügen == null) {
			btnWunschHinzufügen = new JButton("Wunsch hinzufügen");
			btnWunschHinzufügen.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return btnWunschHinzufügen;
	}
	private JLabel getLblWunschlisteDescription() {
		if (lblWunschlisteDescription == null) {
			lblWunschlisteDescription = new JLabel("description");
			lblWunschlisteDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return lblWunschlisteDescription;
	}
	private JButton getBtnWunschDelete() {
		if (btnWunschDelete == null) {
			btnWunschDelete = new JButton("Wunsch löschen");
			btnWunschDelete.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return btnWunschDelete;
	}
	private JList<Wunschliste> getListEintraege() {
		if (listEintraege == null) {
			listEintraege = new JList<Wunschliste>();
		}
		return listEintraege;
	}
	private JTextField getTxtWunschname() {
		if (txtWunschname == null) {
			txtWunschname = new JTextField();
			txtWunschname.setColumns(10);
		}
		return txtWunschname;
	}
	private JLabel getLblWunschname() {
		if (lblWunschname == null) {
			lblWunschname = new JLabel("Wunsch hinzufügen:");
			lblWunschname.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return lblWunschname;
	}
}
