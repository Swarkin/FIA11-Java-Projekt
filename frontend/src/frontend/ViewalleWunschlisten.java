package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonModel;
import javax.swing.JButton;

public class ViewAlleWunschlisten extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnNeueWunschliste;
	
	public ButtonModel btnNeueWunschlisteModel;

	public ViewAlleWunschlisten()
	{
		initialize();
	}
	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnNeueWunschliste());
	}

	private JLabel getLblNewLabel()
	{
		if (lblNewLabel == null)
		{
			lblNewLabel = new JLabel("Meine Listen");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			lblNewLabel.setBounds(28, 36, 94, 12);
		}
		return lblNewLabel;
	}
	private JButton getBtnNeueWunschliste()
	{
		if (btnNeueWunschliste == null)
		{
			btnNeueWunschliste = new JButton("Neue Wunschliste");
			btnNeueWunschliste.setFont(new Font("Arial", Font.PLAIN, 12));
			btnNeueWunschliste.setBounds(10, 227, 150, 23);
			btnNeueWunschlisteModel = btnNeueWunschliste.getModel();
		}
		return btnNeueWunschliste;
	}
}
