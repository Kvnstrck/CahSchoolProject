import java.io.IOException;
import java.io.RandomAccessFile;

public class BegriffeAusDatei {
	public static void main(String[] args) throws IOException {

		// Begriffe aus Datei laden
		RandomAccessFile begriffe = new RandomAccessFile("begriffe.txt", "rw");
		String begriffausdatei = null;
		int zeile = 4;
		
		for (int i = 0; i < zeile; i++) {
			begriffausdatei = begriffe.readLine();
		}
		
		System.out.println(begriffausdatei);

		begriffe.close();

	}

}
