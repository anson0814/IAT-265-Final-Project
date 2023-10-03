/*References
 * 
 * Door retrieved from: https://www.pinclipart.com/pindetail/mRih_free-cellar-cliparts-download-on-door-clipart-png/
 * 
 * Shirt retrieved from: http://cliparts.co/clipart/2304924
 * 
 * All other art created by my in illustrator
 * 
 * IAT 265 Lab 10,11,12
 * 
 * Sounds
 * door opening: https://freesound.org/people/InspectorJ/sounds/431117/
 * curtain: https://freesound.org/people/Khanyi_190188/sounds/492619/
 * changing: https://freesound.org/people/pfranzen/sounds/331354/download/331354__pfranzen__getting-dressed-super-fast.ogg
 * background music: https://freesound.org/people/sonically_sound/sounds/624886/download/624886__sonically-sound__video-music-128-bpm.flac
 * 
 * Click sound: https://www.fesliyanstudios.com/royalty-free-sound-effects-download/mouse-click-2
 * 
 */


package main;

import static util.ImageLoader.loadImage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Timer;

import clothing.ClothingInterface;
import clothing.StarDecorator;
import clothing.BasicShirt;
import clothing.BasicHat;
import clothing.BasicPant;
import clothing.BasicShoe;
import clothing.Clothing;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import environment.Door;
import environment.GlowEffect;
import environment.RestartButton;
import environment.Background;
import environment.Curtain;
import environment.DoneButton;
import environment.StartButton;
import environment.Sun;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageLoader;
import util.MinimHelper;

public class SimulationPanel extends JPanel implements ActionListener {
	public static int W_WIDTH = 1280;
	public static int W_HEIGHT = 960;

	private double mouseX;
	private double mouseY;
	private int state = -1;
	private int END_GAME = 10;
	private int RESULT_SCREEN = 20;
	private Background startingEnvironment;
	private Background startScreen;
	private Background endScreen;
	private Background storeBkg;
	private Background storeOpenBkg;
	private Background dressingRoom;
	private Background resultRoom;
	private Sun sun;
	private Door door;
	private Timer timer;
	private Minim minim;
	
	//audio files
	private AudioPlayer bkgMusic, click, doorSound, curtain, changing;
	
	//Buttons
	private StartButton startButton;
	private DoneButton doneButton;
	private DoneButton doneButton2;
	private RestartButton restartButton;
	private Curtain curtainButton;
	
	
	private GlowEffect glowEffect;
	private GlowEffect glowEffect2;
	
	//timers
	private int doorTimer;
	private int glowTimer;
	private int storeTimer;
	private int dayLightTimer;
	private int moonLightTimer;
	
	//arraylist
	private ArrayList<Clothing> cList;
	
	
	
	SimulationPanel(JFrame frame) {
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));

		startingEnvironment = new Background("assets/Outside Background.png");
		startScreen = new Background("assets/Starting Screen.png");
		endScreen = new Background("assets/End Screen.png");
		storeBkg = new Background("assets/Store.png");
		storeOpenBkg = new Background("assets/Store_open.png");
		dressingRoom = new Background("assets/Dressingscreen.png");
		resultRoom = new Background("assets/resultScreen.png");
		sun = new Sun("assets/Sun.png");
	
		door = new Door(W_WIDTH / 2 +5, 650, 0.3);
		
		this.cList = new ArrayList<>();
		
		
		
		//clothing
		cList.add(new BasicShirt(W_WIDTH / 2, 600));
		cList.add(new BasicShoe(250, W_HEIGHT-250));
		cList.add(new BasicPant(250, W_HEIGHT-580));
		cList.add(new BasicHat(650, W_HEIGHT-700));
		
		//buttons
		startButton = new StartButton(W_WIDTH / 2 , 580, 0.3);
		doneButton = new DoneButton(1125, 800, 0.3);//1125 890
		doneButton2 = new DoneButton(1125, W_WIDTH/2-150, 0.3);
		restartButton = new RestartButton(W_WIDTH / 2+5 , 610, 0.195);
		curtainButton = new Curtain(337,175, 1);
		
		glowEffect = new GlowEffect(340,480,200,250);
		glowEffect2 = new GlowEffect(750,480,200,250);
		
		doorTimer = 120;
		glowTimer = 120;
		storeTimer = 120;
		dayLightTimer = 400;
		moonLightTimer = 400;
						
		
		minim = new Minim(new MinimHelper());

		//audio
		click = minim.loadFile("Audio/click.mp3");
		doorSound = minim.loadFile("Audio/doorOpen.mp3");
		bkgMusic = minim.loadFile("Audio/backgroundMusic.mp3");
		curtain = minim.loadFile("Audio/curtain.mp3");
		changing = minim.loadFile("Audio/changing.mp3");
		
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		
		MyMouseMotionListener mml = new MyMouseMotionListener();
		addMouseMotionListener(mml);
		
		//shirt decorator
		for (Clothing c : cList) { 
			if(c instanceof BasicShirt) {
				//c = new StarDecorator(c,0,0,1);
			}
		}

		timer = new Timer(30, this);
		timer.start();
		bkgMusic.loop();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font f = new Font("Arial", Font.BOLD, 30);
	    g.setFont(f);
		
	    
		if (state == -1) {
			//startingEnvironment.drawStart(g2);
			startScreen.drawStart(g2);
			//door.drawButton(g2);
			startButton.drawButton(g2);
		    g.drawString("Hey come get dressed!", 470, 300);
		    g.drawString("Press the play button to get started", 400, 350);
		    

		}  else if (state == 0) {
			//steamTimer--;
			startingEnvironment.drawStart(g2);
			door.drawButton(g2);
			g.setColor(new Color(0,200,255));
			g.fillRoundRect(430,60,480,85,25,25);
			g.setColor(new Color(255,255,255));
			g.drawString("Click the door to continue", 480, 110);
			if(glowTimer>0) {
				glowEffect.drawMe(g2);
				glowEffect2.drawMe(g2);
				g.setColor(new Color(0,153,0));
				g.fillOval(-30, 240, 330, 350);
				g.fillOval(1000, 280, 300, 330);
				g.setColor(new Color(100,50,0));
				
				drawFractalTree(g2,180,770,270,4,30,2);
				drawFractalTree(g2,1200,770,270,4,30,2);
				
				
				
			}
		}
		else if (state == 1) {
			storeTimer--;
			storeBkg.drawStart(g2);
			curtainButton.drawButton(g2);
			
			if(storeTimer>0) {
			g.setColor(new Color(0,200,255));
			g.fillRoundRect(260,410,650,85,25,25);
			g.setColor(new Color(255,255,255));
			g.drawString("Make your way to the changing room", 305, 445);
			g.drawString("in the upper left to try on some clothes", 300, 475);
			
			}
			
			
			//sun window
			dayLightTimer--;
			if(dayLightTimer<0) {
				moonLightTimer = 400;
				sun = new Sun("assets/Moon.png");
				g.fillRect(730, 43, 510, 256);
				moonLightTimer--;
			}
			if (moonLightTimer<0) {
				sun = new Sun("assets/Sun.png");
				dayLightTimer = 400;
			}
			sun.drawStart(g2);
			
			
			//window tree
			g.setColor(new Color(0,153,0));
			g.fillOval(990, 100, 150, 150);
			
			g.setColor(new Color(100,50,0));
			drawFractalTree(g2,1080,298,270,3,30,1);

			
			
		}
	
		else if (state == 2 || state == 3 || state == 4 || state == 5) {
			dressingRoom.drawStart(g2);
			doorTimer--;
			
			if(doorTimer>0) {
			g.setColor(new Color(0,200,255));
			g.fillRoundRect(20,10,350,85,25,25);
			g.setColor(new Color(255,255,255));
			g.drawString("Click the clothing item", 40, 45);
			g.drawString("to change its color", 60, 75);
			}
			
			for (Clothing c : cList) {
				c.decorate(g2);
			}
			
			doneButton.drawButton(g2);
		}
		else if(state == 50) {
			storeOpenBkg.drawStart(g2);
		}
		else if(state == RESULT_SCREEN) {
			resultRoom.drawStart(g2);
			
			for (Clothing c : cList) { //display screen repos
				if(c instanceof BasicShirt) {
					c.setPos(W_WIDTH/2, 280);
				}
				if(c instanceof BasicPant) {
					c.setPos(W_WIDTH/2, 600);
				}
				if(c instanceof BasicShoe) {
					c.setPos(W_WIDTH/2, 850);
				}
				if(c instanceof BasicHat) {
					c.setPos(W_WIDTH/2-300, 450);
				}
				
				
				c.decorate(g2);
			}
			
			
			doneButton2.drawButton(g2);
		}
		
		else if(state == END_GAME) {
			endScreen.drawStart(g2);
			restartButton.drawButton(g2);
		}

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		repaint();
	}

	public class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();

			if (state == -1 && startButton.clicked(mouseX, mouseY)) {
				click.play(0);
				state = 0;
			} else if(state == 0 && door.clicked(mouseX, mouseY) ) {
				doorSound.play(0);
				state = 1;
			} 
			else if(state == 1) { //switching to changing room
				if(curtainButton.clicked(mouseX, mouseY))
					curtain.play(0);
					state = 2;
			}
			else if(state == 2 ) {
				for (Clothing c : cList) {
					if(c.clicked(mouseX, mouseY)) {
						c.switchColor();
						changing.play(0);
					}
				}
				
				
				state = 3;
				
			}  else if(state == 3 ) {
				for (Clothing c : cList) {
					if(c.clicked(mouseX, mouseY)) {
						c.switchColor1();
						changing.play(0);
					}
				}
				
				
				state = 4;
				
			}  else if(state == 4 ) {
				for (Clothing c : cList) {
					if(c.clicked(mouseX, mouseY)) {
						c.switchColor2();
						changing.play(0);
					}
				}
				
				
				state = 5;
				
			}  else if(state == 5 ) {
				for (Clothing c : cList) {
					if(c.clicked(mouseX, mouseY)) {
						c.switchColor3();
						changing.play(0);
					}
				}
				
				state = 2;
			}
			if(doneButton.clicked(mouseX, mouseY)) {
				//state = END_GAME;
				state = RESULT_SCREEN;
				click.play(0);
			}
			if(doneButton2.clicked(mouseX, mouseY)) {
				state = END_GAME;
				click.play(0);
			}
				
			
			if(state == END_GAME && restartButton.clicked(mouseX, mouseY)) {
					state = -1;
					resetGame();
					click.play(0);
			}
			
			//if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {//double click
				
			//}
			
			
		}
		
		public void mouseEntered(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();	
			
			
			if(curtainButton.clicked(mouseX, mouseY)) {
				storeBkg = new Background("assets/Store_open.png");
			}
			else {
				storeBkg = new Background("assets/Store.png");
			}
				
		}
	}
	
	public class MyMouseMotionListener extends MouseMotionAdapter {
		
		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			
			if(state == 2 || state == 3 || state == 4 || state == 5) {
				for (Clothing c : cList) {
					if(c.clicked(mouseX, mouseY)) {
						c.setPos(mouseX, mouseY);
					}
				}
				

			}
			repaint();
		}
	}
	
	//fractal tree
	//function modified from: https://stackoverflow.com/questions/33946161/draw-fractal-with-recursion-algorithm
	public void drawFractalTree(Graphics2D g, int x1, int y1, double angle, int depth, double size, double factor) {
	    if (depth > 0) {
	        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * size * factor);
	        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * size * factor);

	        g.setStroke(new BasicStroke(1f * depth));
	        g.drawLine(x1, y1, x2, y2);

	        drawFractalTree(g, x2, y2, angle + 10, depth - 1, size, 1.0);
	        drawFractalTree(g, x2, y2, angle - 30, depth - 1, size, 0.3);
	        drawFractalTree(g, x2, y2, angle - 60, depth - 1, size, 1.0);
	    }
	}
	
	public void resetGame() {
		doorTimer = 120;
		glowTimer = 120;
		storeTimer = 120;
		dayLightTimer = 400;
		
		for (Clothing c : cList) { //display screen repos
			if(c instanceof BasicShirt) {
				c.setPos(W_WIDTH / 2, 600);
			}
			if(c instanceof BasicPant) {
				c.setPos(250, W_HEIGHT-580);
			}
			if(c instanceof BasicShoe) {
				c.setPos(250, W_HEIGHT-250);
			}
			if(c instanceof BasicHat) {
				c.setPos(650, W_HEIGHT-700);
			}
		}
		
	}
	
	
	public String readFirstLine(String url) throws FileNotFoundException {
	    try {
	        Scanner scanner = new Scanner(new File(url));
	        return scanner.nextLine();
	    } catch(FileNotFoundException ex) {
	        throw ex; 
	    }
	}
	
	
}
