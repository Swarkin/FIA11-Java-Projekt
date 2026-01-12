package frontend;

import java.util.HashMap;
import java.util.Map;

public class Model 
{
	private Map<Integer, Wunschliste> listen;
	
	public Model()
	{
		listen = new HashMap<Integer, Wunschliste>();
	}
}
