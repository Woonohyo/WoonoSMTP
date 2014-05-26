package server;

public class TextWriter {
	private String rcpt;
	private String data;
	private static TextWriter m_theInstance = null;

	private TextWriter() {

	}

	public static TextWriter getInstance() {
		if (m_theInstance == null)
			m_theInstance = new TextWriter();
		return m_theInstance;
	}

	public String getRcpt() {
		return rcpt;
	}

	public void setRcpt(String rcpt) {
		this.rcpt = rcpt;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public void setClear() {
		this.rcpt = null;
		this.data = null;
	}

}
