package frontend;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Controller c = new Controller();
					c.start();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
