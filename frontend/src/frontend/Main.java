package frontend;

public class Main
{
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        WunschlisteGUI view = new WunschlisteGUI();
	        WunschlisteService service = new WunschlisteService();
	        new WunschlisteController(service, view);
	        view.setVisible(true);
	    });
	}
}
