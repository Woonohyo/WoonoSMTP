package eventHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DataEventHandler implements EventHandler {
	private static int DATA_SIZE = 1024;
	private static String DATAresponse = "354 Start mail input; end with <CRLF>\n";
	private static String endResponse = "250 OK\n";

	@Override
	public void handleEvent(Socket socket) {
		System.out.println("Handling DATA...");
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			byte[] buffer = new byte[DATA_SIZE];
			is.read(buffer);
			String data = new String(buffer);
			System.out.println(data);
			os.write(DATAresponse.getBytes());

			is.read(buffer);
			data = new String(buffer);
			System.out.println(data);

			os.write(endResponse.getBytes());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
