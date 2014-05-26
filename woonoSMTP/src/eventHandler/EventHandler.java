package eventHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface EventHandler {
	void handleEvent(Socket socket) throws IOException;
}
