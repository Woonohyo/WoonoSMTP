package client;

import java.net.*;
import java.io.*;

public class SmtpClient {

	public static void sendMail(String smtpServer, String sender,
			String recipient, String content) throws Exception {
		Socket socket = new Socket(smtpServer, 2525);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
		System.out.println("서버에 연결되었습니다.");

		String line = br.readLine();
		System.out.println("응답:" + line);
		if (!line.startsWith("220"))
			throw new Exception("SMTP서버가 아닙니다:" + line);

		System.out.println("HELO 명령을 전송합니다.");
		pw.println("HELO www.nhnnext.org\n");
		line = br.readLine();
		System.out.println("응답:" + line);
		if (!line.startsWith("250"))
			throw new Exception("HELO 실패했습니다:" + line);

		System.out.println("MAIL FROM 명령을 전송합니다.");
		pw.println("MAIL FROM:" + sender);
		line = br.readLine();
		System.out.println("응답:" + line);
		if (!line.startsWith("250"))
			throw new Exception("MAIL FROM 에서 실패했습니다:" + line);

		System.out.println("RCPT 명령을 전송합니다.");
		pw.println("RCPT TO:" + recipient);
		line = br.readLine();
		System.out.println("응답:" + line);
		if (!line.startsWith("250"))
			throw new Exception("RCPT TO 에서 실패했습니다:" + line);

		System.out.println("DATA 명령을 전송합니다.");
		pw.println("DATA");
		line = br.readLine();
		System.out.println("응답:" + line);
		if (!line.startsWith("354"))
			throw new Exception("DATA 에서 실패했습니다:" + line);

		System.out.println("본문을 전송합니다.");
		pw.println(content);
		line = br.readLine();
		System.out.println("응답:" + line);
		if (!line.startsWith("250"))
			throw new Exception("내용전송에서 실패했습니다:" + line);

		System.out.println("접속 종료합니다.");
		pw.println("QUIT");

		br.close();
		pw.close();
		socket.close();
	}

	public static void main(String args[]) {
		try {
			SmtpClient.sendMail("127.0.0.1", "sender",
					"recipient", "MOBILE COMPUTING REPORT - IMPLEMENTING SMTP SERVER USING JAVA\n");
			System.out.println("==========================");
			System.out.println("메일이 전송되었습니다.");
		} catch (Exception e) {
			System.out.println("==========================");
			System.out.println("메일이 발송되지 않았습니다.");
			System.out.println(e.toString());
		}
	}

}