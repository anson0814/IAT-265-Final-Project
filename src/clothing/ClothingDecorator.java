//Decorator for clothing

package clothing;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class ClothingDecorator {
	Clothing baseClothing;
	protected BufferedImage img;
	protected int xPos, yPos;
	protected double scale;



public ClothingDecorator(Clothing _clothing, int x, int y, double s) {
	baseClothing = _clothing;
	xPos = x;
	yPos = y;
	scale = s;
}

public void decorate(Graphics2D g2) {
	baseClothing.decorate(g2);
}


}

