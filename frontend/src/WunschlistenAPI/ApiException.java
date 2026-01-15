package WunschlistenAPI;

public class ApiException extends Exception
{
	private static final long serialVersionUID = 1L;

	private Integer status;

	public ApiException(int status)
	{
		this.status = status;
	}

	public int getStatus()
	{
		return status;
	}

	@Override
	public String getMessage()
	{
		return status.toString();
	}
}
