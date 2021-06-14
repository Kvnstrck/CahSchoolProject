
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.event.*;
import javax.swing.*;

public class CAH {

	// Name ändern-Button (ausgeblendet)
	public static JButton changeNameButton;

	// weiße Karten
	public static String begriff1 = null;
	public static String begriff2 = null;
	public static String begriff3 = null;
	public static String begriff4 = null;
	public static String begriff5 = null;
	public static String begriff6 = null;

	// Spieler freischalten
	public static boolean spieler1 = true;
	public static boolean spieler2 = false;
	public static boolean spieler3 = false;

	// gegebene Antworten weiße Karte
	public static String antwort1 = null;
	public static String antwort2 = null;
	public static String antwort3 = null;

	// Spieler 1 nach 1. Runde sperren
	public static boolean sp1sperren = false;

	// Spieler haben gewählt
	public static JLabel sp1gewaehlt;
	public static JLabel sp2gewaehlt;
	public static JLabel sp3gewaehlt;

	// Spielernamen
	public static String name1 = null;
	public static String name2 = null;
	public static String name3 = null;
	public static String name4 = null;

	// Punkte
	public static int punkte1 = 0;
	public static int punkte2 = 0;
	public static int punkte3 = 0;
	public static int punkte4 = 0;

	// Punkte darstellen
	public static JLabel labelpunkte1;
	public static JLabel labelpunkte2;
	public static JLabel labelpunkte3;
	public static JLabel labelpunkte4;

	// Anzeige aktueller/nächster Spieler
	public static JLabel aktSpieler;
	public static JLabel naechsterSpieler;

	// neue schwarze Karte
	public static JButton neuSchwarz;

	public static void main(String[] args) throws IOException {
			// Grundgerüst
			JFrame f = new JFrame();
			f.setSize(1000, 700);
			f.setLayout(null);
			f.setTitle("Cards Against Humanity");
			// f.setResizable(false);
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
			JLabel titel = new JLabel(
					"<html><h1 span style='color:#fff;font-size:21px;'>Cards Against Humanity</h1></html>");
			titel.setBounds(300, 5, 400, 100);
			f.add(titel);

			// Linie unter Titel
			JLabel linie = new JLabel(
					"<html><h1 span style='color:#000;'>PlatzhalterPlatzhalterPlatzhalterPlatzhalterPlatzhalterPlatzhalterPlatzhalterPlatzhalter <hr></h1></html>");
			linie.setBounds(20, 5, 950, 120);
			f.add(linie);

			// Button neue schwarze Karte / nächste Runde
			neuSchwarz = new JButton(
					"<html><span style='word-wrap:break-word;color:black;'>schwarze Karte wechseln</span></html>");
			neuSchwarz.setBackground(Color.WHITE);
			neuSchwarz.setBounds(850, 280, 125, 40);
			f.add(neuSchwarz);

			// schwarze Karte anzeigen
			JLabel schwarzeKarte = new JLabel("<html><span style='color:#fff; font-size: 20px;font-weight: 40;'>"
					+ Methoden.begriffausdatei + "</span></html>");
			schwarzeKarte.setBounds(150, 100, 600, 250);
			schwarzeKarte.setHorizontalAlignment(SwingConstants.CENTER);
			schwarzeKarte.setVerticalAlignment(SwingConstants.CENTER);
			f.add(schwarzeKarte);

			neuSchwarz.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String kartentext = null;
					try {
						kartentext = schwarzeKarte(f);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					// schwarzeKarte.setBounds(200, 50, 500, 300);
					// f.add(schwarzeKarte);
					schwarzeKarte.setText("<html><span style='color:#fff; font-size: 20px;font-weight: 40;'>"
							+ kartentext + "</span></html>");
				}
			});

			// aktueller Spieler-Anzeige
			aktSpieler = new JLabel();
			aktSpieler.setText(
					"<html><span style='color:#fff; font-size: 12px;font-weight: 40;'><b>aktueller Spieler:</b> "
							+ name1 + "</span></html>");
			aktSpieler.setBounds(200, 400, 250, 100);
			aktSpieler.setVisible(false);
			f.add(aktSpieler);

			// nächster Spieler-Anzeige
			naechsterSpieler = new JLabel();
			naechsterSpieler.setText(
					"<html><span style='color:#fff; font-size: 12px;font-weight: 40;'><b>nächster Spieler:</b> " + name2
							+ "</span></html>");
			naechsterSpieler.setBounds(530, 400, 250, 100);
			naechsterSpieler.setVisible(false);
			f.add(naechsterSpieler);

			// Fenster sichtbar stellen
			f.setVisible(true);
	}

	public static String schwarzeKarte(JFrame f) throws IOException {
		// schwarze Karte Begriff auslesen
		Methoden.fileaccess();
		return Methoden.begriffausdatei;
	}

	public static void weisseKarte(JFrame f) throws IOException {
		// weiße Karten darstellen

		// Begriffe aus Datei laden
		RandomAccessFile begriffe = null;
		try {
			begriffe = new RandomAccessFile("white.txt", "r");
		} catch (FileNotFoundException e1) {
			System.out.println("Datei wurde nicht gefunden!");
		}

		// Anzahl Zeilen herausfinden
		int anzBegriffe = 0;
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

		// Zufallszahlen für Begriffe bestimmen
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
		} while (randomZahl4 == randomZahl1 || randomZahl4 == randomZahl2 || randomZahl4 == randomZahl3);

		do {
			randomZahl5 = (int) (Math.random() * anzBegriffe + 1);
		} while (randomZahl5 == randomZahl1 || randomZahl5 == randomZahl2 || randomZahl5 == randomZahl3
				|| randomZahl5 == randomZahl4);

		do {
			randomZahl6 = (int) (Math.random() * anzBegriffe + 1);
		} while (randomZahl6 == randomZahl1 || randomZahl6 == randomZahl2 || randomZahl6 == randomZahl3
				|| randomZahl6 == randomZahl4 || randomZahl6 == randomZahl5);

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

		// Begriff 4 auslesen
		for (int i = 0; i < randomZahl4; i++) {
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

		// Runde beginnen und 1x Spieler 1 aufrufen
		if (sp1sperren == false) {
			// neue Runde
			JButton rundeBeginnen = new JButton(
					"<html><span style='word-wrap:break-word; font-size:15px;'>Runde beginnen</span></html>");
			rundeBeginnen.setBounds(315, 500, 250, 60);
			rundeBeginnen.setBackground(Color.WHITE);
			f.add(rundeBeginnen);
			rundeBeginnen.setVisible(true);

			rundeBeginnen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						spieler1Antwort(f);
						sp1sperren = true;
						rundeBeginnen.setVisible(false);
						aktSpieler.setVisible(true);
						naechsterSpieler.setVisible(true);

						f.repaint();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});

		}

	}

	public static void score(JFrame f) throws IOException {
		// Score

		// Titel Score
		JLabel scoretitel = new JLabel("<html><span style='color:#fff; font-size: 15px;'>Score:</span></html>");
		scoretitel.setBounds(850, 70, 400, 100);
		f.add(scoretitel);

		// Punktestand einlesen
		RandomAccessFile punkte = new RandomAccessFile("score.txt", "rw");
		punkte1 = Integer.parseInt(punkte.readLine());
		punkte2 = Integer.parseInt(punkte.readLine());
		punkte3 = Integer.parseInt(punkte.readLine());
		punkte4 = Integer.parseInt(punkte.readLine());

		// Punktestände anzeigen
		labelpunkte1 = new JLabel();
		labelpunkte1.setText(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + punkte1 + "</span></html>");
		labelpunkte1.setBounds(920, 100, 800, 100);

		labelpunkte2 = new JLabel();
		labelpunkte2.setText(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + punkte2 + "</span></html>");
		labelpunkte2.setBounds(920, 100, 800, 140);

		labelpunkte3 = new JLabel();
		labelpunkte3.setText(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + punkte3 + "</span></html>");
		labelpunkte3.setBounds(920, 100, 800, 180);

		labelpunkte4 = new JLabel();
		labelpunkte4.setText(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + punkte4 + "</span></html>");
		labelpunkte4.setBounds(920, 100, 800, 220);

		f.add(labelpunkte1);
		f.add(labelpunkte2);
		f.add(labelpunkte3);
		f.add(labelpunkte4);

		// Button Namen ändern (nicht sichtbar, Button wird über Menü aufgerufen)
		changeNameButton = new JButton("Namen ändern");
		RandomAccessFile namen = new RandomAccessFile("namen.txt", "rw");
//		changeNameButton.setBounds(850, 230, 125, 20);
//		f.add(changeNameButton);
		name1 = namen.readLine();
		name2 = namen.readLine();
		name3 = namen.readLine();
		name4 = namen.readLine();

		// Spielernamen anzeigen
		JLabel scorename1 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + name1 + ":</span></html>");
		scorename1.setBounds(850, 140, 80, 20);

		JLabel scorename2 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + name2 + ":</span></html>");
		scorename2.setBounds(850, 160, 80, 20);

		JLabel scorename3 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + name3 + ":</span></html>");
		scorename3.setBounds(850, 180, 80, 20);

		JLabel scorename4 = new JLabel(
				"<html><span style='color:#fff; font-size: 12px;font-weight: 20;'>" + name4 + ":</span></html>");
		scorename4.setBounds(850, 200, 80, 20);

		f.add(scorename1);
		f.add(scorename2);
		f.add(scorename3);
		f.add(scorename4);

		changeNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Spielernamen eingeben und in Datei abspeichern

				String name1 = null;
				String name2 = null;
				String name3 = null;
				String name4 = null;

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

				do {
					name4 = JOptionPane.showInputDialog(null, "Name Spieler 4:", "Name ändern",
							JOptionPane.QUESTION_MESSAGE);
					wiederholen = false;
					if (name4.length() < 1) {
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
				scorename4.setText("<html><span style='color:#fff; font-size: 12px; font-weight: 20;'>" + name4
						+ ":</span></html>");

				RandomAccessFile namen;
				try {
					namen = new RandomAccessFile("namen.txt", "rw");
					namen.setLength(0);
					namen.writeBytes(name1 + "\n" + name2 + "\n" + name3 + "\n" + name4);
					namen.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});

		namen.close();
		punkte.close();

	}

	public static void menue(JFrame f) throws IOException {
		// Menüleiste erzeugen
		JMenuBar menueLeiste = new JMenuBar();

		// Menüelemente erzeugen
		JMenu datei = new JMenu("Datei");
		JMenu einstellungen = new JMenu("Einstellungen");

		// Untermenüelemente erzeugen
		// Spiel beenden
		JMenuItem beenden = new JMenuItem("Spiel schließen");
		beenden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		// Spielernamen ändern
		JMenuItem namenAendern = new JMenuItem("Spielernamen ändern");
		namenAendern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeNameButton.doClick();
			}
		});

		// Punkte zurücksetzen
		JMenuItem punkteReset = new JMenuItem("Punktestand zurücksetzen");
		punkteReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RandomAccessFile punkte;
				try {
					punkte = new RandomAccessFile("score.txt", "rw");
					punkte.setLength(0);
					punkte.writeBytes(0 + "\n" + 0 + "\n" + 0 + "\n" + 0);
					punkte.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					score(f);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Punktestand erfolgreich zurückgesetzt!",
						"Punktestand zurücksetzen", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// weiße Karten bearbeiten
		JMenuItem weissBearbeiten = new JMenuItem("weiße Karten bearbeiten");
		weissBearbeiten.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File("white.txt"));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Fehler: \nDie Datei konnte nicht geöffnet werden!\nDie Datei muss 'white.txt' heißen!",
							"Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// schwarze Karten bearbeiten
		JMenuItem schwarzBearbeiten = new JMenuItem("schwarze Karten bearbeiten");
		schwarzBearbeiten.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File("black.txt"));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Fehler: \nDie Datei konnte nicht geöffnet werden!\nDie Datei muss 'black.txt' heißen!",
							"Fehler", JOptionPane.ERROR_MESSAGE);
				}
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
		einstellungen.add(weissBearbeiten);
		einstellungen.add(schwarzBearbeiten);

		f.add(menueLeiste);
	}

	public static void spieler1Antwort(JFrame f) throws IOException {
		// Button "neue schwarze Karte" deaktivieren, nachdem Runde begonnen hat
		neuSchwarz.setVisible(false);

		// Antwortmöglichkeiten für Spieler 1

		aktSpieler.setVisible(true);
		aktSpieler.setText("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'><b>aktueller Spieler:</b> "
				+ name1 + "</span></html>");

		naechsterSpieler.setVisible(true);
		naechsterSpieler
				.setText("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'><b>nächster Spieler:</b> "
						+ name2 + "</span></html>");

		sp1gewaehlt = new JLabel("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'>" + name1
				+ " hat gewählt!</span></html>");
		sp1gewaehlt.setBounds(150, 300, 150, 50);
		sp1gewaehlt.setVisible(false);
		f.add(sp1gewaehlt);

		// Antwortmöglichkeiten Buttons
		JButton button1 = new JButton();
		button1.setText("<html><span style='word-wrap:break-word;'>" + begriff1 + "</span></html>");
		button1.setBounds(50, 500, 250, 60);
		button1.setBackground(Color.WHITE);

		JButton button2 = new JButton();
		button2.setText("<html><span style='word-wrap:break-word;'>" + begriff2 + "</span></html>");
		button2.setBounds(315, 500, 250, 60);
		button2.setBackground(Color.WHITE);

		JButton button3 = new JButton();
		button3.setText("<html><span style='word-wrap:break-word;'>" + begriff3 + "</span></html>");
		button3.setBounds(580, 500, 250, 60);
		button3.setBackground(Color.WHITE);

		JButton button4 = new JButton();
		button4.setText("<html><span style='word-wrap:break-word;'>" + begriff4 + "</span></html>");
		button4.setBounds(50, 575, 250, 60);
		button4.setBackground(Color.WHITE);

		JButton button5 = new JButton();
		button5.setText("<html><span style='word-wrap:break-word;'>" + begriff5 + "</span></html>");
		button5.setBounds(315, 575, 250, 60);
		button5.setBackground(Color.WHITE);

		JButton button6 = new JButton();
		button6.setText("<html><span style='word-wrap:break-word;'>" + begriff6 + "</span></html>");
		button6.setBounds(580, 575, 250, 60);
		button6.setBackground(Color.WHITE);

		f.add(button1);
		f.add(button2);
		f.add(button3);
		f.add(button4);
		f.add(button5);
		f.add(button6);

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort1 = begriff1;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp1gewaehlt.setVisible(true);

				weiterbutton1(f);

			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort1 = begriff2;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp1gewaehlt.setVisible(true);

				weiterbutton1(f);

			}
		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort1 = begriff3;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp1gewaehlt.setVisible(true);

				weiterbutton1(f);
			}
		});

		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort1 = begriff4;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp1gewaehlt.setVisible(true);

				weiterbutton1(f);
			}
		});

		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort1 = begriff5;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp1gewaehlt.setVisible(true);

				weiterbutton1(f);
			}
		});

		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort1 = begriff6;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp1gewaehlt.setVisible(true);

				weiterbutton1(f);
			}
		});

	}

	public static void weiterbutton1(JFrame f) {
		// Button "nächster Spieler", nachdem Spieler 1 gewählt hat

		JButton weiterbutton = new JButton();
		weiterbutton.setText("<html><span style='word-wrap:break-word;'>nächster Spieler</span></html>");
		weiterbutton.setBounds(350, 500, 150, 50);
		weiterbutton.setBackground(Color.WHITE);
		f.add(weiterbutton);

		weiterbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.remove(weiterbutton);
				f.repaint();
				spieler2Antwort(f);

			}
		});

	}

	public static void spieler2Antwort(JFrame f) {
		// Antwortmöglichkeiten für Spieler 2

		aktSpieler.setText("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'><b>aktueller Spieler:</b> "
				+ name2 + "</span></html>");
		naechsterSpieler
				.setText("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'><b>nächster Spieler:</b> "
						+ name3 + "</span></html>");
		f.repaint();

		sp2gewaehlt = new JLabel("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'>" + name2
				+ " hat gewählt!</span></html>");
		sp2gewaehlt.setBounds(400, 300, 150, 50);
		sp2gewaehlt.setVisible(false);
		f.add(sp2gewaehlt);

		try {
			weisseKarte(f);
		} catch (IOException e3) {
			e3.printStackTrace();
		}

		// Antwortmöglichkeiten Buttons
		JButton button1 = new JButton();
		button1.setText("<html><span style='word-wrap:break-word;'>" + begriff1 + "</span></html>");
		button1.setBounds(50, 500, 250, 60);
		button1.setBackground(Color.WHITE);

		JButton button2 = new JButton();
		button2.setText("<html><span style='word-wrap:break-word;'>" + begriff2 + "</span></html>");
		button2.setBounds(315, 500, 250, 60);
		button2.setBackground(Color.WHITE);

		JButton button3 = new JButton();
		button3.setText("<html><span style='word-wrap:break-word;'>" + begriff3 + "</span></html>");
		button3.setBounds(580, 500, 250, 60);
		button3.setBackground(Color.WHITE);

		JButton button4 = new JButton();
		button4.setText("<html><span style='word-wrap:break-word;'>" + begriff4 + "</span></html>");
		button4.setBounds(50, 575, 250, 60);
		button4.setBackground(Color.WHITE);

		JButton button5 = new JButton();
		button5.setText("<html><span style='word-wrap:break-word;'>" + begriff5 + "</span></html>");
		button5.setBounds(315, 575, 250, 60);
		button5.setBackground(Color.WHITE);

		JButton button6 = new JButton();
		button6.setText("<html><span style='word-wrap:break-word;'>" + begriff6 + "</span></html>");
		button6.setBounds(580, 575, 250, 60);
		button6.setBackground(Color.WHITE);

		f.add(button1);
		f.add(button2);
		f.add(button3);
		f.add(button4);
		f.add(button5);
		f.add(button6);

		button1.setVisible(true);
		button2.setVisible(true);
		button3.setVisible(true);
		button4.setVisible(true);
		button5.setVisible(true);
		button6.setVisible(true);

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort2 = begriff1;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp2gewaehlt.setVisible(true);

				weiterbutton2(f);
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort2 = begriff2;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp2gewaehlt.setVisible(true);

				weiterbutton2(f);
			}
		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort2 = begriff3;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp2gewaehlt.setVisible(true);

				weiterbutton2(f);
			}
		});

		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort2 = begriff4;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp2gewaehlt.setVisible(true);

				weiterbutton2(f);
			}
		});

		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort2 = begriff5;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp2gewaehlt.setVisible(true);

				weiterbutton2(f);
			}
		});

		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort2 = begriff6;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp2gewaehlt.setVisible(true);

				weiterbutton2(f);
			}
		});
	}

	public static void weiterbutton2(JFrame f) {
		// Button "nächster Spieler", nachdem Spieler 2 gewählt hat

		JButton weiterbutton2 = new JButton();
		weiterbutton2.setText("<html><span style='word-wrap:break-word;'>nächster Spieler</span></html>");
		weiterbutton2.setBounds(350, 500, 150, 50);
		weiterbutton2.setBackground(Color.WHITE);
		f.add(weiterbutton2);

		weiterbutton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.remove(weiterbutton2);
				f.repaint();
				spieler3Antwort(f);
				f.repaint();
			}
		});

	}

	public static void spieler3Antwort(JFrame f) {
		// Antwortmöglichkeiten für Spieler 3

		sp3gewaehlt = new JLabel("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'>" + name3
				+ " hat gewählt!</span></html>");
		sp3gewaehlt.setBounds(650, 300, 150, 50);
		sp3gewaehlt.setVisible(false);
		f.add(sp3gewaehlt);

		aktSpieler.setText("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'><b>aktueller Spieler:</b> "
				+ name3 + "</span></html>");
		naechsterSpieler
				.setText("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'><b>nächster Spieler:</b> "
						+ name4 + " (Zar)</span></html>");

		try {
			weisseKarte(f);
		} catch (IOException e3) {
			e3.printStackTrace();
		}

		// Antwortmöglichkeiten Buttons
		JButton button1 = new JButton();
		button1.setText("<html><span style='word-wrap:break-word;'>" + begriff1 + "</span></html>");
		button1.setBounds(50, 500, 250, 60);
		button1.setBackground(Color.WHITE);

		JButton button2 = new JButton();
		button2.setText("<html><span style='word-wrap:break-word;'>" + begriff2 + "</span></html>");
		button2.setBounds(315, 500, 250, 60);
		button2.setBackground(Color.WHITE);

		JButton button3 = new JButton();
		button3.setText("<html><span style='word-wrap:break-word;'>" + begriff3 + "</span></html>");
		button3.setBounds(580, 500, 250, 60);
		button3.setBackground(Color.WHITE);

		JButton button4 = new JButton();
		button4.setText("<html><span style='word-wrap:break-word;'>" + begriff4 + "</span></html>");
		button4.setBounds(50, 575, 250, 60);
		button4.setBackground(Color.WHITE);

		JButton button5 = new JButton();
		button5.setText("<html><span style='word-wrap:break-word;'>" + begriff5 + "</span></html>");
		button5.setBounds(315, 575, 250, 60);
		button5.setBackground(Color.WHITE);

		JButton button6 = new JButton();
		button6.setText("<html><span style='word-wrap:break-word;'>" + begriff6 + "</span></html>");
		button6.setBounds(580, 575, 250, 60);
		button6.setBackground(Color.WHITE);

		f.add(button1);
		f.add(button2);
		f.add(button3);
		f.add(button4);
		f.add(button5);
		f.add(button6);

		button1.setVisible(true);
		button2.setVisible(true);
		button3.setVisible(true);
		button4.setVisible(true);
		button5.setVisible(true);
		button6.setVisible(true);

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort3 = begriff1;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp3gewaehlt.setVisible(true);

				endeButton(f);
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort3 = begriff2;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp3gewaehlt.setVisible(true);

				endeButton(f);
			}
		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort3 = begriff3;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp3gewaehlt.setVisible(true);

				endeButton(f);
			}
		});

		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort3 = begriff4;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp3gewaehlt.setVisible(true);

				endeButton(f);
			}
		});

		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort3 = begriff5;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp3gewaehlt.setVisible(true);

				endeButton(f);
			}
		});

		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antwort3 = begriff6;
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				button6.setVisible(false);
				sp3gewaehlt.setVisible(true);

				endeButton(f);
			}
		});
	}

	public static void endeButton(JFrame f) {
		// Button "Karten aufdecken", nachdem Spieler 3 gewählt hat

		aktSpieler.setVisible(true);
		aktSpieler.setText("<html><span style='color:#fff; font-size: 12px;font-weight: 40;'><b>aktueller Spieler:</b> "
				+ name4 + " (Zar)</span></html>");

		naechsterSpieler.setVisible(false);

		JButton aufdecken = new JButton();
		aufdecken.setText("<html><span style='word-wrap:break-word;font-size:12px;'>Karten aufdecken</span></html>");
		aufdecken.setBounds(315, 500, 250, 60);
		aufdecken.setBackground(Color.WHITE);
		f.add(aufdecken);

		aufdecken.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.remove(aufdecken);
				f.repaint();

				kartenAufdecken(f);
			}
		});

	}

	public static void kartenAufdecken(JFrame f) {
		// Antworten der Spieler anzeigen, Gewinnbekanntgabe nach Auswahl von Zar

		aktSpieler.setVisible(false);
		f.remove(sp1gewaehlt);
		f.remove(sp2gewaehlt);
		f.remove(sp3gewaehlt);

		// Spieler 1 hat gewonnen
		JLabel sp1gewonnen = new JLabel();
		sp1gewonnen.setText("<html><span style='color:#fff; font-size: 20px; font-weight: 40;'><b>" + name1
				+ " hat gewonnen!</span></b></html>");
		sp1gewonnen.setBounds(300, 250, 350, 200);
		f.add(sp1gewonnen);
		sp1gewonnen.setVisible(false);

		JLabel antwortSp1 = new JLabel();
		antwortSp1.setText(
				"<html><span style='color:#fff; font-size: 15px;'>gegebene Antwort: <br> <span style='color:red;font-style:italic;'>"
						+ antwort1 + "</span></i></span></html>");
		antwortSp1.setBounds(320, 320, 350, 200);
		f.add(antwortSp1);
		antwortSp1.setVisible(false);

		// Spieler 2 hat gewonnen
		JLabel sp2gewonnen = new JLabel();
		sp2gewonnen.setText("<html><span style='color:#fff; font-size: 20px; font-weight: 40;'><b>" + name2
				+ " hat gewonnen!</span></b></html>");
		sp2gewonnen.setBounds(300, 250, 350, 200);
		f.add(sp2gewonnen);
		sp2gewonnen.setVisible(false);

		JLabel antwortSp2 = new JLabel();
		antwortSp2.setText(
				"<html><span style='color:#fff; font-size: 15px;'>gegebene Antwort: <br> <span style='color:red;font-style:italic;'>"
						+ antwort2 + "</span></i></span></html>");
		antwortSp2.setBounds(320, 320, 350, 200);
		f.add(antwortSp2);
		antwortSp2.setVisible(false);

		// Spieler 3 hat gewonnen
		JLabel sp3gewonnen = new JLabel();
		sp3gewonnen.setText("<html><span style='color:#fff; font-size: 20px; font-weight: 40;'><b>" + name3
				+ " hat gewonnen!</span></b></html>");
		sp3gewonnen.setBounds(300, 250, 350, 200);
		f.add(sp3gewonnen);
		sp3gewonnen.setVisible(false);

		JLabel antwortSp3 = new JLabel();
		antwortSp3.setText(
				"<html><span style='color:#fff; font-size: 15px;'>gegebene Antwort: <br> <span style='color:red;font-style:italic;'>"
						+ antwort3 + "</span></i></span></html>");
		antwortSp3.setBounds(320, 320, 350, 200);
		f.add(antwortSp3);
		antwortSp3.setVisible(false);

		JLabel hinweisZar = new JLabel("<html><span style='color:#fff; font-size: 12px;'>" + name4
				+ " (Zar): bitte wähle eine Karte aus:</span></b></html>");
		hinweisZar.setBounds(290, 220, 350, 200);
		f.add(hinweisZar);

		// Spieler 1 Antwort Button
		JButton buttonS1Antwort = new JButton(
				"<html><span style='word-wrap:break-word;'>" + antwort1 + "</span></html>");
		buttonS1Antwort.setBounds(50, 350, 250, 60);
		buttonS1Antwort.setBackground(Color.WHITE);
		f.add(buttonS1Antwort);

		// Spieler 2 Antwort Button
		JButton buttonS2Antwort = new JButton(
				"<html><span style='word-wrap:break-word;'>" + antwort2 + "</span></html>");
		buttonS2Antwort.setBounds(315, 350, 250, 60);
		buttonS2Antwort.setBackground(Color.WHITE);
		f.add(buttonS2Antwort);

		// Spieler 3 Antwort Button
		JButton buttonS3Antwort = new JButton(
				"<html><span style='word-wrap:break-word;'>" + antwort3 + "</span></html>");
		buttonS3Antwort.setBounds(580, 350, 250, 60);
		buttonS3Antwort.setBackground(Color.WHITE);
		f.add(buttonS3Antwort);

		// neue Runde
		JButton naechsteRunde = new JButton(
				"<html><span style='word-wrap:break-word; font-size:15px;'>neue Runde</span></html>");
		naechsteRunde.setBounds(315, 500, 250, 60);
		naechsteRunde.setBackground(Color.WHITE);
		f.add(naechsteRunde);
		naechsteRunde.setVisible(false);

		buttonS1Antwort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp1gewonnen.setVisible(true);
				antwortSp1.setVisible(true);

				hinweisZar.setVisible(false);
				buttonS1Antwort.setVisible(false);
				buttonS2Antwort.setVisible(false);
				buttonS3Antwort.setVisible(false);

				naechsteRunde.setVisible(true);

				punkte1++;
				System.out.println("Punkte Spieler 1: " + punkte1);

			}
		});

		buttonS2Antwort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp2gewonnen.setVisible(true);
				antwortSp2.setVisible(true);

				hinweisZar.setVisible(false);
				buttonS1Antwort.setVisible(false);
				buttonS2Antwort.setVisible(false);
				buttonS3Antwort.setVisible(false);

				naechsteRunde.setVisible(true);

				punkte2++;
				System.out.println("Punkte Spieler 2: " + punkte2);
			}
		});

		buttonS3Antwort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp3gewonnen.setVisible(true);
				antwortSp3.setVisible(true);

				hinweisZar.setVisible(false);
				buttonS1Antwort.setVisible(false);
				buttonS2Antwort.setVisible(false);
				buttonS3Antwort.setVisible(false);

				naechsteRunde.setVisible(true);

				punkte3++;
				System.out.println("Punkte Spieler 3: " + punkte3);
			}
		});

		naechsteRunde.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				naechsteRunde.setVisible(false);
				sp1gewonnen.setVisible(false);
				antwortSp1.setVisible(false);
				sp2gewonnen.setVisible(false);
				antwortSp2.setVisible(false);
				sp3gewonnen.setVisible(false);
				antwortSp3.setVisible(false);
				sp1sperren = false;
				try {
					schwarzeKarte(f);
					weisseKarte(f);
					neuSchwarz.setVisible(true);
					neuSchwarz.doClick();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				f.repaint();
			}
		});

	}

}
