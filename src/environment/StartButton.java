//start button to begin the game

package environment;

import java.awt.Color;
import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import util.BaseButton;
import util.ImageLoader;

public class StartButton extends BaseButton  {

	private BufferedImage img;
	

	public StartButton(double x, double y,  double s) {
		super(x,y,s);
		img = loadImage("assets/Playbutton.png");
	}
 
	public void drawButton(Graphics2D g2) {

		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		//numbers from lab 10
		if(x > (xPos - ((double) img.getWidth())/2*scale) && 
				x < (xPos + ((double) img.getWidth())/2*scale) && 
				y > (yPos - ((double) img.getHeight())/2*scale) && 
				y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}
	

}
