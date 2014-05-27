package eventHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import server.TextWriter;

public class RcptEventHandler implements EventHandler {
	private static int DATA_SIZE = 1024;
	private static String response = "250 OK\n";
	private static String errorResp = "550 No such user here\n";

	@Override
	public void handleEvent(Socket socket) {
		System.out.println("Handling RCPT...");
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			byte[] buffer = new byte[DATA_SIZE];
			int rcptSize = is.read(buffer);
			String data = new String(buffer);
			data = data.substring(0, rcptSize);
			System.out.println(data);
			os.write(response.getBytes());
			
			TextWriter textWriter = TextWriter.getInstance();
			textWriter.setRcpt(data);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
