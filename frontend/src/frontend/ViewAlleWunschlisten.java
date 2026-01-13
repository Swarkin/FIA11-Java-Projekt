package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;

public class ViewAlleWunschlisten extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblWunschlisten;
	private JList<Wunschliste> listWunschlisten;
	private JButton btnWunschlisteErstellen;
	private JButton btnWunschlisteBearbeiten;
	
	public ButtonModel btnNeueWunschlisteModel;

	public ViewAlleWunschlisten()
	{
		setTitle("Wunschlisten");
		initialize();
	}
	
	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 150, 20, 0};
		gbl_contentPane.rowHeights = new int[]{20, 12, 20, 0, 20, 0, 0, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_lblWunschlisten = new GridBagConstraints();
		gbc_lblWunschlisten.anchor = GridBagConstraints.WEST;
		gbc_lblWunschlisten.fill = GridBagConstraints.VERTICAL;
		gbc_lblWunschlisten.insets = new Insets(0, 0, 5, 5);
		gbc_lblWunschlisten.gridx = 1;
		gbc_lblWunschlisten.gridy = 1;
		contentPane.add(getLblWunschlisten(), gbc_lblWunschlisten);
		GridBagConstraints gbc_listWunschlisten = new GridBagConstraints();
		gbc_listWunschlisten.insets = new Insets(0, 0, 5, 5);
		gbc_listWunschlisten.fill = GridBagConstraints.BOTH;
		gbc_listWunschlisten.gridx = 1;
		gbc_listWunschlisten.gridy = 3;
		contentPane.add(getListWunschlisten(), gbc_listWunschlisten);
		GridBagConstraints gbc_btnWunschlisteErstellen = new GridBagConstraints();
		gbc_btnWunschlisteErstellen.insets = new Insets(0, 0, 5, 5);
		gbc_btnWunschlisteErstellen.fill = GridBagConstraints.BOTH;
		gbc_btnWunschlisteErstellen.gridx = 1;
		gbc_btnWunschlisteErstellen.gridy = 5;
		contentPane.add(getBtnWunschlisteErstellen(), gbc_btnWunschlisteErstellen);
		GridBagConstraints gbc_btnWunschlisteBearbeiten = new GridBagConstraints();
		gbc_btnWunschlisteBearbeiten.fill = GridBagConstraints.BOTH;
		gbc_btnWunschlisteBearbeiten.insets = new Insets(0, 0, 5, 5);
		gbc_btnWunschlisteBearbeiten.gridx = 1;
		gbc_btnWunschlisteBearbeiten.gridy = 6;
		contentPane.add(getBtnWunschlisteBearbeiten(), gbc_btnWunschlisteBearbeiten);
	}

	private JLabel getLblWunschlisten()
	{
		if (lblWunschlisten == null)
		{
			lblWunschlisten = new JLabel("Meine Listen");
			lblWunschlisten.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return lblWunschlisten;
	}
	private JButton getBtnWunschlisteErstellen()
	{
		if (btnWunschlisteErstellen == null)
		{
			btnWunschlisteErstellen = new JButton("Neu erstellen");
			btnWunschlisteErstellen.setFont(new Font("Arial", Font.PLAIN, 12));
			btnNeueWunschlisteModel = btnWunschlisteErstellen.getModel();
		}
		return btnWunschlisteErstellen;
	}
	private JList getListWunschlisten() {
		if (listWunschlisten == null) {
			listWunschlisten = new JList();
		}
		return listWunschlisten;
	}
	private JButton getBtnWunschlisteBearbeiten() {
		if (btnWunschlisteBearbeiten == null) {
			btnWunschlisteBearbeiten = new JButton("Bearbeiten");
			btnWunschlisteBearbeiten.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return btnWunschlisteBearbeiten;
	}
}
