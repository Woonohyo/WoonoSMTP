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

	private void handleClient(Socket socket) {
		System.out.println("요청을 처리합니다.");
		try {
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			byte[] buffer = new byte[4];
			String clientData;

			// 클라이언트에게 서비스가 준비됨을 알리는 220 코드 전송.
			serviceReady220(os);

			while (is.read(buffer) != -1) {
				clientData = new String(buffer);
				System.out.println("clientData: " + clientData);
			}

			socket.close();

			/*
			 * 
			 * // 클라이언트의 HELO 명령 읽은 뒤, // 무사히 완료했음을 알리는 250 코드 전송.
			 * readClientData(is, buffer); requestComplete250(os);
			 * 
			 * // 클라이언트의 MAIL FROM 명령을 읽은 뒤, // 무사히 완료했음을 알리는 250 코드 전송.
			 * readClientData(is, buffer); requestComplete250(os);
			 * 
			 * // 클라이언트의 RCPT 명령을 읽은 뒤, // 무사히 완료했음을 알리는 250 코드 전송.
			 * readClientData(is, buffer); requestComplete250(os);
			 * 
			 * // 클라이언트의 DATA 명령을 읽은 뒤, // 본문 전송을 요구하는 354 코드 전송.
			 * readClientData(is, buffer); startMailInput354(os);
			 * 
			 * // 클라이언트의 본문 내용을 읽은 뒤, // 무사히 완료했음을 알리는 250 코드 전송.
			 * readClientData(is, buffer); requestComplete250(os);
			 * 
			 * readClientData(is, buffer); readClientData(is, buffer);
			 */

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void startMailInput354(OutputStream os) throws IOException {
		String message = "354\n";
		os.write(message.getBytes());
	}

	private void requestComplete250(OutputStream os) throws IOException {
		String message = "250\n";
		os.write(message.getBytes());
	}

	private void readClientData(InputStream is, byte[] buffer)
			throws IOException {
		is.read(buffer);
		String fromClient = new String(buffer);
		System.out.println(fromClient);

	}

	private void serviceReady220(OutputStream os) throws IOException {
		String message = "220 Woonohyo SMTP Service Ready\n";
		os.write(message.getBytes());
	}

}
