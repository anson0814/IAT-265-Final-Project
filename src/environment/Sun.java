//Class for background images like the start, end screens, and the store

package environment;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.SimulationPanel;
import util.ImageLoader;
import util.Util;

public class Sun {
	private BufferedImage img;

	public Sun(String file) {
		img = ImageLoader.loadImage(file);
	}

	public void drawStart(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(750-Util.random(20), 50);
		g2.scale(0.08, 0.1);
		g2.drawImage(img, 0, 0, SimulationPanel.W_WIDTH, SimulationPanel.W_HEIGHT, null);
		
		g2.setTransform(at);
	}

}
