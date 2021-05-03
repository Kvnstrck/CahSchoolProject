package framing;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class frameForGames extends Frame implements MouseListener {

	public static void main(String[] args) throws IOException {
		new frameForGames().setVisible(true);
	}

	public frameForGames() {
		setSize(650, 550);
		setTitle("Replace");
		// Add MouseListener
		addMouseListener(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
