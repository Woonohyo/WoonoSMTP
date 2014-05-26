package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import eventHandler.DataEventHandler;
import eventHandler.EventHandler;
import eventHandler.HeloEventHandler;
import eventHandler.MailEventHandler;
import eventHandler.QuitEventHandler;
import eventHandler.RcptEventHandler;

public class Dispatcher {
	private final static int CODE_SIZE = 4;
	private static String code;
	private static HashMap<String, EventHandler> handlerMap = new HashMap<String, EventHandler>();

	public Dispatcher() {
		initHandlerMap();
	}

	private void initHandlerMap() {
		HeloEventHandler heloEventHandler = new HeloEventHandler();
		DataEventHandler dataEventHandler = new DataEventHandler();
		MailEventHandler mailEventHandler = new MailEventHandler();
		QuitEventHandler quitEventHandler = new QuitEventHandler();
		RcptEventHandler rcptEventHandler = new RcptEventHandler();

		handlerMap.put("HELO", heloEventHandler);
		handlerMap.put("DATA", dataEventHandler);
		handlerMap.put("MAIL", mailEventHandler);
		handlerMap.put("QUIT", quitEventHandler);
		handlerMap.put("RCPT", rcptEventHandler);

		System.out.println("EventHandlerMap Initialized");
	}

	public void dispatch(ServerSocket serverSocket) {
		try {
			Socket socket = serverSocket.accept();
			if (socket.isConnected()) {
				serviceReady220(socket);
				demultiplex(socket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void demultiplex(Socket socket) {
		try {

			System.out.println("Demultiplexing...");
			InputStream is = socket.getInputStream();
			byte[] buffer = new byte[CODE_SIZE];
			while (!socket.isClosed() && is.read(buffer) != -1) {
				code = new String(buffer);
				EventHandler handler = handlerMap.get(code);
				handler.handleEvent(socket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void serviceReady220(Socket socket) throws IOException {
		OutputStream os = socket.getOutputStream();
		String message = "220 Woonohyo Simple Mail Transfer Service Ready\n";
		System.out.println(message);
		os.write(message.getBytes());
	}
}
