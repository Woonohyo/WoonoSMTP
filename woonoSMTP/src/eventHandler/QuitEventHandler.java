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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeToFile() {
		System.out.println("###Writing mail to txt file...###");
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime());
		TextWriter textWriter = TextWriter.getInstance();
		StringBuilder sb = new StringBuilder();

		sb.append(textWriter.getRcpt().replace("\n", "").substring(4));
		sb.append(timeStamp);
		sb.append(".txt");
		String fileName = sb.toString();

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
