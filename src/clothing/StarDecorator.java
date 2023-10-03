//Decorator for clothing objects

package clothing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class StarDecorator extends ClothingDecorator {

	public StarDecorator(Clothing c, int x, int y, double s) {
		super(c, x, y,s);
		img = ImageLoader.loadImage("assets/star.png");
	}
	
	public void decorate(Graphics2D g2) {
		super.decorate(g2);
		decorateWithStar(g2);
	}
	
	private void decorateWithStar(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(1, 1);
		g2.drawImage(img,  -img.getWidth()/2,  -img.getHeight()/2, null);
		g2.setTransform(at);
	}



}
