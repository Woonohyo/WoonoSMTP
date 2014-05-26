package eventHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HeloEventHandler implements EventHandler {
	private static int DATA_SIZE = 1024;
	private static String response = "250 OK\n";

	@Override
	public void handleEvent(Socket socket) throws IOException {
		System.out.println("Handling HELO...");
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		byte[] buffer = new byte[DATA_SIZE];
		is.read(buffer);
		String data = new String(buffer);
		System.out.println(data);
		
		os.write(response.getBytes());
		
	}
}