package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInitializer implements Runnable {
	private final static int SMTP_PORT = 2525;

	public static void main(String[] args) {
		System.out.println("SMTP Servr ON: " + SMTP_PORT);
		ServerInitializer server = new ServerInitializer();
		server.run();
	}

	@Override
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(SMTP_PORT);
			Dispatcher dispatcher = new Dispatcher();
			while (true) {
				dispatcher.dispatch(serverSocket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void serviceReady220(OutputStream os) throws IOException {
		String message = "220 Woonohyo SMTP Service Ready\n";
		os.write(message.getBytes());
	}

}
