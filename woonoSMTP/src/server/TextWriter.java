package server;

public class TextWriter {
	private String Helo;
	private String From;
	private String Rcpt;
	private String Data;

	public String getHelo() {
		return Helo;
	}

	public void setHelo(String helo) {
		Helo = helo;
	}

	public String getFrom() {
		return From;
	}

	public void setFrom(String from) {
		From = from;
	}

	public String getRcpt() {
		return Rcpt;
	}

	public void setRcpt(String rcpt) {
		Rcpt = rcpt;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}
}
