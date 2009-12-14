package rf.pegaso.gui;

import java.awt.Graphics;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class DecoratedDesktopPane extends JDesktopPane {

	private ImageIcon image;

	private MediaTracker tracker;

	public DecoratedDesktopPane() {
		super();
	}

	public DecoratedDesktopPane(String immagine) {

		image = new ImageIcon(immagine);

		tracker = new MediaTracker(this);
		tracker.addImage(image.getImage(), 0);

		try {
			tracker.waitForID(0);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);

		graphics.drawImage(image.getImage(), this.getWidth() / 2
				- image.getIconWidth() / 2, this.getHeight() / 2
				- image.getIconHeight() / 2, image.getIconWidth(), image
				.getIconHeight(), this.getBackground(), this);
	}

}
