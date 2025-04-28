package blogapplication.Payloads;

public class Responseapi {


	private String messagee;
	
	private boolean success;

	public Responseapi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Responseapi(String messagee, boolean success) {
		super();
		this.messagee = messagee;
		this.success = success;
	}

	public String getMessagee() {
		return messagee;
	}

	public void setMessagee(String messagee) {
		this.messagee = messagee;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
