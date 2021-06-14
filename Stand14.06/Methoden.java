
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Methoden {
	public static String begriffausdatei = null;

	public static String fileaccess() throws IOException {
		// schwarze Karten anzeigen
		int anzBegriffe = 0;
		RandomAccessFile begriffe = new RandomAccessFile("black.txt", "r");

		// Anzahl Zeilen herausfinden
		try {
			while (begriffe.readLine() != null) {
				anzBegriffe++;
			}
			begriffe.close();
			begriffe = new RandomAccessFile("black.txt", "r");
		} catch (FileNotFoundException e) {
			System.out.println("Datei wurde nicht gefunden!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// zufällige Zeile auswählen
		int zeile = (int) (Math.random() * anzBegriffe + 1);

		do {
			for (int i = 0; i < zeile; i++) {
				begriffausdatei = begriffe.readLine();
			}
		} while (begriffausdatei == null);

		return begriffausdatei;
	}

}
