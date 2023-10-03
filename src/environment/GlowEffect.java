//class for perlin noise glowing effect, used in windows

package environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import processing.core.PApplet;
import util.Util;

public class GlowEffect {
	private float xPos, yPos;
	private int width, height;

	private float xstart;
	private float xnoise;
	private float ynoise;
	private PApplet pa;
	
	public GlowEffect(float x , float y, int w, int h) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		xstart = Util.random(10);
		xnoise = xstart;
		ynoise = Util.random(10);
		pa = new PApplet();

	}
	
	public void drawMe(Graphics2D g2) {
		float noiseFactor;
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);

		for(int y=0; y <=height; y += 5) {
			ynoise += 5;
			xnoise = xstart;
			for(int x= 0; x<=width; x+=10) {
				xnoise+= 100;
				noiseFactor = pa.noise(xnoise,ynoise);

				AffineTransform at1 = g2.getTransform();
				g2.translate(x, y);
				g2.rotate(noiseFactor*Util.radians(540));
				float edgeSize = noiseFactor * 35;
				
				g2.setColor(new Color(255,255,153));
				g2.fill(new Ellipse2D.Float(-edgeSize/2, -edgeSize/4, edgeSize, edgeSize/2*noiseFactor));
				g2.setTransform(at1);
			}

		}
		g2.setTransform(at);
	}

}
