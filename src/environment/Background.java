//Class for background images like the start, end screens, and the store

package environment;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.SimulationPanel;
import util.ImageLoader;

public class Background {
	private BufferedImage img;

	public Background(String file) {
		img = ImageLoader.loadImage(file);
	}

	public void drawStart(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(0, 0);
		g2.scale(1, 1);
		g2.drawImage(img, 0, 0, SimulationPanel.W_WIDTH, SimulationPanel.W_HEIGHT, null);
		
		g2.setTransform(at);
	}

}
