package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class ViewalleWunschlisten extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;

	public ViewalleWunschlisten()
	{

		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Meine Listen");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			lblNewLabel.setBounds(28, 36, 94, 12);
		}
		return lblNewLabel;
	}
}
