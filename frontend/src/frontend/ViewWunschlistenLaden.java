package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class ViewWunschlistenLaden extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblAbfragenText;
	private JLabel lblLadenFortschritt;

	public ViewWunschlistenLaden()
	{
		setResizable(false);
		setTitle("Wunschlisten laden...");
		initialize();
	}
	
	public void setzeFortschrittText(int erledigt, int gesamt)
	{
		lblLadenFortschritt.setText(erledigt + " von " + gesamt);
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 0, 20, 0};
		gbl_contentPane.rowHeights = new int[]{20, 0, 0, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_lblAbfragenText = new GridBagConstraints();
		gbc_lblAbfragenText.insets = new Insets(0, 0, 5, 5);
		gbc_lblAbfragenText.gridx = 1;
		gbc_lblAbfragenText.gridy = 1;
		contentPane.add(getLblAbfragenText(), gbc_lblAbfragenText);
		GridBagConstraints gbc_lblLadenFortschritt = new GridBagConstraints();
		gbc_lblLadenFortschritt.insets = new Insets(0, 0, 5, 5);
		gbc_lblLadenFortschritt.gridx = 1;
		gbc_lblLadenFortschritt.gridy = 2;
		contentPane.add(getLblLadenFortschritt(), gbc_lblLadenFortschritt);
	}
	private JLabel getLblAbfragenText() {
		if (lblAbfragenText == null) {
			lblAbfragenText = new JLabel("Wunschlisten werden abgefragt...");
			lblAbfragenText.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return lblAbfragenText;
	}
	private JLabel getLblLadenFortschritt() {
		if (lblLadenFortschritt == null) {
			lblLadenFortschritt = new JLabel("");
			lblLadenFortschritt.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return lblLadenFortschritt;
	}
}
