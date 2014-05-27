package eventHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import server.TextWriter;
import sun.java2d.pipe.BufferedBufImgOps;

public class QuitEventHandler implements EventHandler {

	@Override
	public void handleEvent(Socket socket) {
		System.out.println("Handling QUIT...");
		try {
			writeToFile();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile() {
		System.out.println("### Writing the mail to txt file ###");
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(Calendar.getInstance().getTime());
		TextWriter textWriter = TextWriter.getInstance();
		StringBuilder sb = new StringBuilder();

		sb.append(textWriter.getRcpt().replaceAll("(\r\n|\n)", "").substring(4));
		sb.append(timeStamp);
		sb.append(".txt");
		String fileName = sb.toString();
		System.out.println(fileName);

		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(textWriter.getData());
			out.newLine();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		textWriter.setClear();
	}
}
