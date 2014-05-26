package eventHandler;

import java.io.IOException;
import java.net.Socket;

public class QuitEventHandler implements EventHandler {

	@Override
	public void handleEvent(Socket socket) {
		System.out.println("Handling QUIT...");
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
