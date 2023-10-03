//Interface for clothing items, holds methods shared 
package clothing;

import java.awt.Graphics2D;

public interface ClothingInterface {
	void decorate(Graphics2D g2);

	void switchColor(); 
	void switchColor1(); 
	void switchColor2(); 
	void switchColor3(); 
	
	void setPos(double x, double y);
}
