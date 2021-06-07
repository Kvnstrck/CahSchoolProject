
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.event.*;
import javax.swing.*;

public class CAH {

	public static JButton changeNameButton;

	public static String begriff1 = null;
	public static String begriff2 = null;
	public static String begriff3 = null;
	public static String begriff4 = null;
	public static String begriff5 = null;
	public static String begriff6 = null;

	public static String wortGedrückt = "---";

	public static int rundenIndikator = 0;

	public static void main(String[] args) throws IOException {
		// Grundgerüst
		JFrame f = new JFrame();
		f.setSize(1000, 700);
		f.setLayout(null);
		f.setTitle("Cards Against Humanity");
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setBackground(Color.BLACK);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Programm-Icon
		ImageIcon programmIcon = new ImageIcon("icon.ico");
		f.setIconImage(programmIcon.getImage());

		// Methoden aufrufen
		schwarzeKarte(f);
		weisseKarte(f);
		score(f);
		menue(f);

		// Titel
		JLabel titel = new JLabel("<html><h1 span style='color:#fff;'>Cards Against Humanity</h1></html>");
		titel.setBounds(300, 5, 400, 100);
		f.add(titel);

		// Linie unter Titel
		JLabel linie = new JLabel(
				"<html><h1 span style='color:#000;'>PlatzhalterPlatzhalterPlatzhalterPlatzhalterPlatzhalterPlatzhalterPlatzhalterPlatzhalter <hr></h1></html>");
		linie.setBounds(20, 5, 950, 120);
		f.add(linie);

		// Button neue schwarze Karte
		JButton neu = new JButton(
				"<html><span style='word-wrap:break-word;color:black;'>neue schwarze Karte</span></html>");
		neu.setBounds(820, 250, 150, 50);
		f.add(neu);

		JLabel schwarzeKarte = new JLabel("<html><span style='color:#fff; font-size: 20px;font-weight: 40;'>"
				+ Methoden.begriffausdatei + "</span></html>");
		schwarzeKarte.setBounds(200, 50, 500, 400);
		f.add(schwarzeKarte);

		neu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kartentext = null;
				try {
					kartentext = schwarzeKarte(f);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				schwarzeKarte.setBounds(200, 50, 500, 300);
				f.add(schwarzeKarte);
				schwarzeKarte.setText("<html><span style='color:#fff; font-size: 20px;font-weight: 40;'>" + kartentext
						+ "</span></html>");
			}
		});

		// aktueller Spieler-Anzeige
		String aktSpielerName = "XXX";
		JLabel aktSpieler = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 40;'>aktueller Spieler: " + aktSpielerName
						+ "</span></html>");
		aktSpieler.setBounds(100, 400, 200, 100);
		f.add(aktSpieler);

		f.setVisible(true);

	}

	public static String schwarzeKarte(JFrame f) throws IOException {
		// schwarze Karte
		Methoden.fileaccess();
		return Methoden.begriffausdatei;
	}

	public static void weisseKarte(JFrame f) throws IOException {
		// weiße Karten

		// Begriffe aus Datei laden
		RandomAccessFile begriffe = null;
		try {
			begriffe = new RandomAccessFile("white.txt", "r");
		} catch (FileNotFoundException e1) {
			System.out.println("Datei wurde nicht gefunden!");
		}

		int anzBegriffe = 0;

		// Anzahl Zeilen herausfinden
		try {
			while (begriffe.readLine() != null) {
				anzBegriffe++;
			}

			begriffe.close();

			begriffe = new RandomAccessFile("white.txt", "r");
		} catch (FileNotFoundException e) {
			System.out.println("Datei wurde nicht gefunden!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Zufallszahl für Begriff bestimmen
		int randomZahl1 = (int) (Math.random() * anzBegriffe + 1);
		int randomZahl2 = 0;
		int randomZahl3 = 0;
		int randomZahl4 = 0;
		int randomZahl5 = 0;
		int randomZahl6 = 0;

		do {
			randomZahl2 = (int) (Math.random() * anzBegriffe + 1);
		} while (randomZahl2 == randomZahl1);

		do {
			randomZahl3 = (int) (Math.random() * anzBegriffe + 1);
		} while (randomZahl3 == randomZahl2 || randomZahl3 == randomZahl1);

		do {
			randomZahl4 = (int) (Math.random() * anzBegriffe + 1);
		} while (randomZahl4 == randomZahl1);

		do {
			randomZahl5 = (int) (Math.random() * anzBegriffe + 1);
		} while (randomZahl5 == randomZahl1);

		do {
			randomZahl6 = (int) (Math.random() * anzBegriffe + 1);
		} while (randomZahl6 == randomZahl1);

		// Begriffe auslesen

		// Begriff 1 auslesen
		for (int i = 0; i < randomZahl1; i++) {
			try {
				begriff1 = begriffe.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			begriffe.close();
			begriffe = new RandomAccessFile("white.txt", "r");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Begriff 2 auslesen
		for (int i = 0; i < randomZahl2; i++) {
			try {
				begriff2 = begriffe.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			begriffe.close();
			begriffe = new RandomAccessFile("white.txt", "r");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Begriff 3 auslesen
		for (int i = 0; i < randomZahl3; i++) {
			try {
				begriff3 = begriffe.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			begriffe.close();
			begriffe = new RandomAccessFile("white.txt", "r");
		} catch (IOException e) {
			e.printStackTrace();
		}

//		// Begriff 4 auslesen
		for (int i = 0; i < 5; i++) {
			try {
				begriff4 = begriffe.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			begriffe.close();
			begriffe = new RandomAccessFile("white.txt", "r");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Begriff 5 auslesen
		for (int i = 0; i < randomZahl5; i++) {
			try {
				begriff5 = begriffe.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			begriffe.close();
			begriffe = new RandomAccessFile("white.txt", "r");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Begriff 6 auslesen
		for (int i = 0; i < randomZahl6; i++) {
			try {
				begriff6 = begriffe.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			begriffe.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Antwortmöglichkeiten
		JButton button1 = new JButton("<html><span style='word-wrap:break-word;'>" + begriff1 + "</span></html>");
		button1.setBounds(50, 500, 250, 60);
		button1.setBackground(Color.WHITE);
		JButton button2 = new JButton("<html><span style='word-wrap:break-word;'>" + begriff2 + "</span></html>");
		button2.setBounds(315, 500, 250, 60);
		button2.setBackground(Color.WHITE);
		JButton button3 = new JButton("<html><span style='word-wrap:break-word;'>" + begriff3 + "</span></html>");
		button3.setBounds(580, 500, 250, 60);
		button3.setBackground(Color.WHITE);
		JButton button4 = new JButton("<html><span style='word-wrap:break-word;'>" + begriff4 + "</span></html>");
		button4.setBounds(50, 575, 250, 60);
		button4.setBackground(Color.WHITE);
		JButton button5 = new JButton("<html><span style='word-wrap:break-word;'>" + begriff5 + "</span></html>");
		button5.setBounds(315, 575, 250, 60);
		button5.setBackground(Color.WHITE);
		JButton button6 = new JButton("<html><span style='word-wrap:break-word;'>" + begriff6 + "</span></html>");
		button6.setBounds(580, 575, 250, 60);
		button6.setBackground(Color.WHITE);

		// Spieler 1 Antwort Button
		JButton buttonS1Antwort = new JButton(wortGedrückt);
		buttonS1Antwort.setBounds(50, 350, 250, 60);
		buttonS1Antwort.setBackground(Color.WHITE);

		// Spieler 2 Antwort Button
		JButton buttonS2Antwort = new JButton(wortGedrückt);
		buttonS2Antwort.setBounds(315, 350, 250, 60);
		buttonS2Antwort.setBackground(Color.WHITE);

		// Spieler 3 Antwort Button
		JButton buttonS3Antwort = new JButton(wortGedrückt);
		buttonS3Antwort.setBounds(580, 350, 250, 60);
		buttonS3Antwort.setBackground(Color.WHITE);

		// Rundenende Button
		JButton rundenendeButton = new JButton("Rundenende");
		rundenendeButton.setBounds(850, 320, 120, 30);
		rundenendeButton.setBackground(Color.WHITE);

		rundenendeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rundenIndikator++;
			}

		});

		boolean spieler1 = true;
		boolean spieler2 = false;
		boolean spieler3 = false;

		if (spieler1 == true) {
			spieler1Antwort(button1, button2, button3, button4, button5, button6, buttonS1Antwort);
			spieler1 = false;
			spieler2 = true;
		}
		if (spieler2 == true) {
			spieler2Antwort(button1, button2, button3, button4, button5, button6, buttonS2Antwort);
			spieler2 = false;
			spieler3 = true;
		}
		if (spieler3 == true) {
			spieler3Antwort(button1, button2, button3, button4, button5, button6, buttonS3Antwort);
			spieler3 = false;
		}

//  	Add Buttons
		f.add(button1);
		f.add(button2);
		f.add(button3);
		f.add(button4);
		f.add(button5);
		f.add(button6);
		f.add(buttonS1Antwort);
		f.add(buttonS2Antwort);
		f.add(buttonS3Antwort);
		f.add(rundenendeButton);
	}

	public static void score(JFrame f) throws IOException {
		// Score
		int punkte1 = 6;
		int punkte2 = 2;
		int punkte3 = 0;

		// Punktestände anzeigen
		JLabel labelpunkte1 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + punkte1 + "</span></html>");
		labelpunkte1.setBounds(920, 100, 800, 150);
		JLabel labelpunkte2 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + punkte2 + "</span></html>");
		labelpunkte2.setBounds(920, 100, 800, 190);
		JLabel labelpunkte3 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + punkte3 + "</span></html>");
		labelpunkte3.setBounds(920, 100, 800, 230);

		f.add(labelpunkte1);
		f.add(labelpunkte2);
		f.add(labelpunkte3);

		// Titel Score
		JLabel scoretitel = new JLabel("<html><span style='color:#fff; font-size: 15px;'>Score:</span></html>");
		scoretitel.setBounds(850, 100, 400, 100);
		f.add(scoretitel);

		// Button Namen ändern (nicht sichtbar, Button wird über Menü aufgerufen)
		changeNameButton = new JButton("Namen ändern");
		RandomAccessFile namen = new RandomAccessFile("namen.txt", "rw");
//		changeNameButton.setBounds(700, 400, 100, 20);
//		f.add(changeNameButton);
		String name1 = namen.readLine();
		String name2 = namen.readLine();
		String name3 = namen.readLine();

		// Spielernamen anzeigen
		JLabel scorename1 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px; font-weight: 20;'>" + name1 + ":</span></html>");
		scorename1.setBounds(850, 100, 400, 150);
		JLabel scorename2 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + name2 + ":</span></html>");
		scorename2.setBounds(850, 100, 400, 190);
		JLabel scorename3 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + name3 + ":</span></html>");
		scorename3.setBounds(850, 100, 400, 230);

		f.add(scorename1);
		f.add(scorename2);
		f.add(scorename3);

		changeNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Spielernamen eingeben und in Datei abspeichern

				String name1 = null;
				String name2 = null;
				String name3 = null;

				boolean wiederholen = true;

				do {
					name1 = JOptionPane.showInputDialog(null, "Name Spieler 1:", "Name ändern",
							JOptionPane.QUESTION_MESSAGE);
					wiederholen = false;
					if (name1.length() < 1) {
						wiederholen = true;
						JOptionPane.showMessageDialog(null, "Bitte gib mindestens ein Zeichen ein!", "Fehler",
								JOptionPane.WARNING_MESSAGE);
					}
				} while (wiederholen);

				do {
					name2 = JOptionPane.showInputDialog(null, "Name Spieler 2:", "Name ändern",
							JOptionPane.QUESTION_MESSAGE);
					wiederholen = false;
					if (name2.length() < 1) {
						wiederholen = true;
						JOptionPane.showMessageDialog(null, "Bitte gib mindestens ein Zeichen ein!", "Fehler",
								JOptionPane.WARNING_MESSAGE);
					}
				} while (wiederholen);

				do {
					name3 = JOptionPane.showInputDialog(null, "Name Spieler 3:", "Name ändern",
							JOptionPane.QUESTION_MESSAGE);
					wiederholen = false;
					if (name3.length() < 1) {
						wiederholen = true;
						JOptionPane.showMessageDialog(null, "Bitte gib mindestens ein Zeichen ein!", "Fehler",
								JOptionPane.WARNING_MESSAGE);
					}
				} while (wiederholen);

				scorename1.setText("<html><span style='color:#fff; font-size: 12px; font-weight: 20;'>" + name1
						+ ":</span></html>");
				scorename2.setText("<html><span style='color:#fff; font-size: 12px; font-weight: 20;'>" + name2
						+ ":</span></html>");
				scorename3.setText("<html><span style='color:#fff; font-size: 12px; font-weight: 20;'>" + name3
						+ ":</span></html>");

				RandomAccessFile namen;
				try {
					namen = new RandomAccessFile("namen.txt", "rw");
					namen.setLength(0);
					namen.writeBytes(name1 + "\n" + name2 + "\n" + name3);
					namen.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});

		namen.close();

	}

	public static void menue(JFrame f) throws IOException {
		// Menüleiste erzeugen
		JMenuBar menueLeiste = new JMenuBar();

		// Menüelemente erzeugen
		JMenu datei = new JMenu("Datei");
		JMenu einstellungen = new JMenu("Einstellungen");

		// Untermenüelemente erzeugen
		JMenuItem beenden = new JMenuItem("Spiel schließen");
		beenden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		JMenuItem namenAendern = new JMenuItem("Spielernamen ändern");
		namenAendern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeNameButton.doClick();
			}
		});

		JMenuItem punkteReset = new JMenuItem("Punktestand zurücksetzen");
		punkteReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		// Menüelemente hinzufügen
		menueLeiste.add(datei);
		menueLeiste.add(einstellungen);
		menueLeiste.setBounds(0, 0, 1000, 20);

		// Untermenüelemente hinzufügen
		datei.add(beenden);
		einstellungen.add(namenAendern);
		einstellungen.add(punkteReset);

		f.add(menueLeiste);
	}

	public static void spieler1Antwort(JButton button1, JButton button2, JButton button3, JButton button4,
			JButton button5, JButton button6, JButton buttonS1Antwort) {
		// Spieler 1
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff1;
				buttonS1Antwort.setText(begriff1);
			}

		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff2;
				buttonS1Antwort.setText(begriff2);
			}

		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff3;
				buttonS1Antwort.setText(begriff3);
			}

		});
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff4;
				buttonS1Antwort.setText(begriff4);
			}

		});

		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff5;
				buttonS1Antwort.setText(begriff5);
			}

		});
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff6;
				buttonS1Antwort.setText(begriff6);
			}

		});
	}

	public static void spieler2Antwort(JButton button1, JButton button2, JButton button3, JButton button4,
			JButton button5, JButton button6, JButton buttonS2Antwort) {
		// Spieler 2
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff1;
				buttonS2Antwort.setText(begriff1);
			}

		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff2;
				buttonS2Antwort.setText(begriff2);
			}

		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff3;
				buttonS2Antwort.setText(begriff3);
			}

		});
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff4;
				buttonS2Antwort.setText(begriff4);
			}

		});

		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff5;
				buttonS2Antwort.setText(begriff5);
			}

		});
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff6;
				buttonS2Antwort.setText(begriff6);
			}

		});

	}

	public static void spieler3Antwort(JButton button1, JButton button2, JButton button3, JButton button4,
			JButton button5, JButton button6, JButton buttonS3Antwort) {
		// Spieler 3
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff1;
				buttonS3Antwort.setText(begriff1);
			}

		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff2;
				buttonS3Antwort.setText(begriff2);
			}

		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff3;
				buttonS3Antwort.setText(begriff3);
			}

		});
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff4;
				buttonS3Antwort.setText(begriff4);
			}

		});

		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff5;
				buttonS3Antwort.setText(begriff5);
			}

		});
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wortGedrückt = begriff6;
				buttonS3Antwort.setText(begriff6);
			}

		});
	}

}
