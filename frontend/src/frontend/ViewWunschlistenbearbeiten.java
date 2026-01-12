package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

public class ViewWunschlistenbearbeiten extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblWunschlisteName;
	private JButton btnhinzufügen;
	private JLabel lblDescription;
	private JButton btnDelete;
	private JList list;
	private JTextField textFieldWunschname;
	private JLabel lblWunschname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					ViewWunschlistenbearbeiten frame = new ViewWunschlistenbearbeiten();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewWunschlistenbearbeiten()
	{

		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblWunschlisteName());
		contentPane.add(getBtnhinzufügen());
		contentPane.add(getLblDescription());
		contentPane.add(getBtnDelete());
		contentPane.add(getList());
		contentPane.add(getTextFieldWunschname());
		contentPane.add(getLblWunschname());
	}

	private JLabel getLblWunschlisteName() {
		if (lblWunschlisteName == null) {
			lblWunschlisteName = new JLabel("Titel");
			lblWunschlisteName.setFont(new Font("Arial", Font.PLAIN, 14));
			lblWunschlisteName.setBounds(29, 28, 77, 12);
		}
		return lblWunschlisteName;
	}
	private JButton getBtnhinzufügen() {
		if (btnhinzufügen == null) {
			btnhinzufügen = new JButton("Wunsch hinzufügen");
			btnhinzufügen.setFont(new Font("Arial", Font.PLAIN, 14));
			btnhinzufügen.setBounds(29, 291, 372, 31);
		}
		return btnhinzufügen;
	}
	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel("description");
			lblDescription.setFont(new Font("Arial", Font.PLAIN, 12));
			lblDescription.setBounds(28, 58, 77, 12);
		}
		return lblDescription;
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Wunsch löschen");
			btnDelete.setFont(new Font("Arial", Font.PLAIN, 14));
			btnDelete.setBounds(28, 325, 373, 31);
		}
		return btnDelete;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBounds(28, 97, 216, 181);
		}
		return list;
	}
	private JTextField getTextFieldWunschname() {
		if (textFieldWunschname == null) {
			textFieldWunschname = new JTextField();
			textFieldWunschname.setBounds(254, 120, 147, 19);
			textFieldWunschname.setColumns(10);
		}
		return textFieldWunschname;
	}
	private JLabel getLblWunschname() {
		if (lblWunschname == null) {
			lblWunschname = new JLabel("Wunsch hinzufügen:");
			lblWunschname.setFont(new Font("Arial", Font.PLAIN, 12));
			lblWunschname.setBounds(254, 98, 117, 12);
		}
		return lblWunschname;
	}
}
