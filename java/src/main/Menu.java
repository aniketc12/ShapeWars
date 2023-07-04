package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r;
	public static int where = 1;
	public static boolean enterPressed = false;
	public static boolean escPressed;
	private boolean shapeWarsEffectAdded = false;
	private boolean setWhereGameOver = false;
	private boolean setWhereStore = false;
	private boolean setWhereMenu = false;
	
	private boolean livesErrorMessage;
	private boolean carnageErrorMessage;
	private boolean bulletSpeedErrorMessage;
	private boolean playerSpeedErrorMessage;
	
	
	public Menu (Game game, Handler handler) {
		this.game=game;
		this.handler = handler;
		r = new Random();

		for (int i =0; i<10; i++) {
			handler.addObject(new MenuParticleEffect(Game.WIDTH/2,Game.HEIGHT/2, ID.MenuParticleEffect, handler));
		}
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == Game.STATE.Menu) {
			
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 180, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				startNewGame(handler);
			}
		
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 300, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				Game.gameState = Game.STATE.Store;
			}
			
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 420, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				Game.gameState = Game.STATE.Help;
				
			}
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 540, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				Control.checkHighScore();
				System.exit(1);
			}
		}
		else if(game.gameState == Game.STATE.GameOver){
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 420, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				Control.checkHighScore();
				startNewGame(handler);
			}
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 540, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				Control.checkHighScore();
				System.exit(1);
			}
		}
		else if(game.gameState == Game.STATE.Store){
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 590, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				where=1;
				game.gameState = Game.STATE.Menu;
				
			}
		}
		else if(game.gameState == Game.STATE.Help){
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 590, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				where=1;
				game.gameState = Game.STATE.Menu;
				
			}
		}
	}	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx>x && mx<x+width) {
			if(my>y && my < y+height) {
				return true;
			}
		}
		return false;
	}
	
	public void tick() {
		double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
        double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
        
        int relativeMouseX = (int) mouseX - game.frameX - 6;
        int relativeMouseY = (int) mouseY - game.frameY - 27;
        
        
        if(game.gameState == Game.STATE.Menu) {
				
			if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 180, 300, 80) ) {
				where = 1;
			}
			if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 300, 300, 80) ) {
				where = 2;
			}
			if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 420, 300, 80) ) {
				where = 3;
			}
			if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 540, 300, 80) ) {
				where = 4;
			}
			
			if(enterPressed == true) {
				enterPressed = false;
				if(where == 1) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					Control.checkHighScore();
					startNewGame(handler);
				}
				
				if(where == 2) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					Game.gameState = Game.STATE.Store;
				}
				
				if(where == 3) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					Game.gameState = Game.STATE.Help;
				}
				
				if(where == 4) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					Control.checkHighScore();
					System.exit(1);
				}
			}
        }
        else if(game.gameState == Game.STATE.GameOver){
        	
        	if(enterPressed == true) {
				enterPressed = false;
				if(where == 3) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);

					Control.checkHighScore();
					startNewGame(handler);
				}
				if(where == 4) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					Control.checkHighScore();
					System.exit(1);
				}
        	}
        	if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 420, 300, 80) ) {
				where = 3;
			}
        	if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 540, 300, 80) ) {
				where = 4;
			}
        }
        else if(game.gameState == Game.STATE.Store) {
        	where =5;
			if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 590, 300, 80) ) {
				
				where = 5;
			}
			
			if(enterPressed == true) {
				enterPressed = false;
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				where=1;
				game.gameState = Game.STATE.Menu;
			}
			
        }
        else if(game.gameState == Game.STATE.Help) {
        	
			if(enterPressed == true) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				enterPressed = false;
				where=1;
				game.gameState = Game.STATE.Menu;
			}
			
        }
		
	}
	
	public void render(Graphics g) {
		
		if(game.gameState == Game.STATE.Menu || game.gameState == Game.STATE.Game ) {
			
			Font font = new Font("comic sans ms", 1, 50);
			Font font2 = new Font("arial", 1, 30);
		
			
			
			if(shapeWarsEffectAdded == false) {
				handler.addObject(new ShapeWarsMenuEffect(20, -200, ID.ShapeWarsMenuEffect, handler));
				shapeWarsEffectAdded = true;
			}
			
			if(setWhereMenu==false) {
				where=1;
				setWhereMenu=true;
			}
			
			
			g.setFont(font2);
			
			g.setColor(Color.white);
			g.fillRect(Game.WIDTH/2 - 150, 180, 300, 80);
			g.setColor(Color.black);
			g.drawString("PLAY", Game.WIDTH/2-40, 230);
			
			g.setColor(Color.white);
			g.fillRect(Game.WIDTH/2 - 150, 300, 300, 80);
			g.setColor(Color.black);
			g.drawString("STORE", Game.WIDTH/2-50, 350);
			
			g.setColor(Color.white);
			g.fillRect(Game.WIDTH/2 - 150, 420, 300, 80);
			g.setColor(Color.black);
			g.drawString("HELP", Game.WIDTH/2-40, 470);
			
			g.setColor(Color.white);
			g.fillRect(Game.WIDTH/2 - 150, 540, 300, 80);
			g.setColor(Color.black);
			g.drawString("QUIT", Game.WIDTH/2-40, 590);
			
			
			
			renderTriangle(g, where);
			
			font = new Font("comic sand ms",1,10);
			g.setFont(font);
			g.setColor(new Color(2,16,66));
			g.fillRect(1108, 661, 154, 13);
			g.setColor(Color.white);
			g.drawString("Created By: Aniket Chakraborty", 1110, 670);
		}
		
		else if(game.gameState == Game.STATE.GameOver) {
			if(setWhereGameOver == false) {
				where=3;
				setWhereGameOver = true;
			}
			renderGameOver(g, where);
			
		}
		else if(game.gameState == Game.STATE.Store) {
			if(setWhereStore==false) {
				where = 1;
				setWhereStore=true;
			}
			
			renderStore(g, where);
		}
		else if(Game.gameState == Game.STATE.Help) {
			
			renderHelp(g);
		}
		
		
		
	}
	
	private void renderTriangle(Graphics g, int where) {
		
		
		Graphics2D g2 = (Graphics2D) g;
		
		if(where == 1) {
			
			g.setColor(Color.white); Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-170, 210); 
			polygon.addPoint(Game.WIDTH/2-160,220);
			polygon.addPoint(Game.WIDTH/2-170, 230); 
			g2.fillPolygon(polygon);
			 
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 150, 180, 300, 80);
			g.setColor(Color.black);
			g.drawString("PLAY", Game.WIDTH/2-40, 230);
		}
		if(where == 2) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-170, 330);
			polygon.addPoint(Game.WIDTH/2-160, 340);
			polygon.addPoint(Game.WIDTH/2-170, 350);
			g2.fillPolygon(polygon);
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 150, 300, 300, 80);
			g.setColor(Color.black);
			g.drawString("STORE", Game.WIDTH/2-50, 350);
		}
		if(where == 3) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-170, 450);
			polygon.addPoint(Game.WIDTH/2-160, 460);
			polygon.addPoint(Game.WIDTH/2-170, 470);
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 150, 420, 300, 80);
			g.setColor(Color.black);
			g.drawString("HELP", Game.WIDTH/2-40, 470);
		}
		if(where == 4) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-170, 570);
			polygon.addPoint(Game.WIDTH/2-160, 580);
			polygon.addPoint(Game.WIDTH/2-170, 590);
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 150, 540, 300, 80);
			g.setColor(Color.black);
			g.drawString("QUIT", Game.WIDTH/2-40, 590);
		}
		
		
	}
	
	private void renderGameOver(Graphics g, int where) {
		Font font = new Font("arial", 1, 50);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Game Over", Game.WIDTH/2-134, 200);
		g.drawString("Score: " +Control.score, Game.WIDTH/2-110, 285);
		
		if(Control.score<=Integer.parseInt(Game.highScore)) {
		g.drawString("High Score: " +Game.highScore, Game.WIDTH/2-160, 370);
		}
		else if(Control.score>Integer.parseInt(Game.highScore)) {
			g.drawString("High Score: " +Control.score, Game.WIDTH/2-160, 370);
			
		}
		
		Font font2 = new Font("arial", 1, 30);
		g.setFont(font2);
		
		g.setColor(Color.cyan);
		g.fillRect(Game.WIDTH/2 - 150, 420, 300, 80);
		g.setColor(Color.black);
		g.drawString("", Game.WIDTH/2-40, 470);
		
		g.setColor(Color.cyan);
		g.fillRect(Game.WIDTH/2 - 150, 540, 300, 80);
		g.setColor(Color.black);
		g.drawString("QUIT", Game.WIDTH/2-40, 590);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH/2 - 150, 420, 300, 80);
		g.setColor(Color.black);
		g.drawString("PLAY AGAIN", Game.WIDTH/2-90, 470);
		
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH/2 - 150, 540, 300, 80);
		g.setColor(Color.black);
		g.drawString("QUIT", Game.WIDTH/2-40, 590);
		
		if(where == 3) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-170, 450);
			polygon.addPoint(Game.WIDTH/2-160, 460);
			polygon.addPoint(Game.WIDTH/2-170, 470);
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 150, 420, 300, 80);
			g.setColor(Color.black);
			g.drawString("PLAY AGAIN", Game.WIDTH/2-90, 470);
		}
		if(where == 4) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-170, 570);
			polygon.addPoint(Game.WIDTH/2-160, 580);
			polygon.addPoint(Game.WIDTH/2-170, 590);
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 150, 540, 300, 80);
			g.setColor(Color.black);
			g.drawString("QUIT", Game.WIDTH/2-40, 590);
		}
	}
	
	private void startNewGame(Handler handler) {
		game.gameState = STATE.Game;
		Control.reset();

		Game.setHighScore();
		
		
		handler.object.clear();
		
		handler.addObject(new Player((Game.WIDTH/2-32),(Game.HEIGHT/2-32), ID.Player, handler));
		
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100)+30,r.nextInt(Game.HEIGHT-100)+30, ID.BasicEnemy, handler));
		
		handler.addObject(new ToughEnemy(Game.WIDTH+100,r.nextInt(Game.HEIGHT-200)+10, ID.ToughEnemy, handler, Game.toughEnemyHealth));
		handler.addObject(new PlayerFood(r.nextInt(Game.WIDTH-80)+35,r.nextInt(Game.HEIGHT-80)+35, ID.PlayerFood, 5, handler));
		//handler.addObject(new FollowEnemy(r.nextInt(Game.WIDTH-60)+25,r.nextInt(Game.HEIGHT-60)+25, ID.FollowEnemy, handler));
		 
	}

	private void renderStore(Graphics g, int where) {
		//add speed add carnage chips add number of lives add bullet speed
		
		Graphics2D g2 = (Graphics2D) g;
		
		Font font2 = new Font("comic sans ms", 1, 26);
		g.setFont(font2);
		
		 
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH/2 - 500, 300, 300, 80);
		g.setColor(Color.black);
		g.drawString("EXTRA LIVES: "+Control.numberOfLives, Game.WIDTH/2-454, 330); 
		g.drawString("5000 COINS", Game.WIDTH/2-428, 365);
		
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH/2 - 150, 300, 300, 80);
		g.setColor(Color.black);
		g.drawString("CARNAGE CHIPS: "+Control.numberOfCarnageModeChips, Game.WIDTH/2-130, 330);
		g.drawString("5000 COINS", Game.WIDTH/2-77, 365);
		
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH/2 +200, 300, 300, 80);
		g.setColor(Color.black);
		g.drawString("PLAYER SPEED: "+Control.playerSpeed+"/20", Game.WIDTH/2+212, 330);
		g.drawString("2000 COINS", Game.WIDTH/2+275, 365);
		
		g.setColor(Color.WHITE);
		g.fillRect(Game.WIDTH/2 - 150, 420, 300, 80);
		g.setColor(Color.black);
		g.drawString("BULLET SPEED: "+Control.bulletSpeed+"/40", Game.WIDTH/2-138, 451);
		g.drawString("1500 COINS", Game.WIDTH/2-77, 485);
		
		g.setColor(Color.WHITE);
		g.fillRect(Game.WIDTH/2 - 150, 590, 300, 80);
		g.setColor(Color.black);
		g.drawString("BACK", Game.WIDTH/2-40, 640);
		
		
		
		if(where == 1) {
			
			g.setColor(Color.white); Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-355, 280); 
			polygon.addPoint(Game.WIDTH/2-335,280);
			polygon.addPoint(Game.WIDTH/2-345, 295); 
			g2.fillPolygon(polygon);
			 
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 500, 300, 300, 80);
			g.setColor(Color.black);
			g.drawString("EXTRA LIFE", Game.WIDTH/2-430, 330); 
			g.drawString("2500 COINS", Game.WIDTH/2-428, 365);
		}
		if(where == 2) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-5, 280); 
			polygon.addPoint(Game.WIDTH/2+15,280);
			polygon.addPoint(Game.WIDTH/2+5, 295); 
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 150, 300, 300, 80);
			g.setColor(Color.black);
			g.drawString("CARNAGE CHIP", Game.WIDTH/2-102, 330);
			g.drawString("2500 COINS", Game.WIDTH/2-77, 365);
		}
		if(where == 3) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2+345, 280); 
			polygon.addPoint(Game.WIDTH/2+365,280);
			polygon.addPoint(Game.WIDTH/2+355, 295); 
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 +200, 300, 300, 80);
			g.setColor(Color.black);
			g.drawString("PLAYER SPEED: "+Control.playerSpeed+"/20", Game.WIDTH/2+212, 330);
			g.drawString("2000 COINS", Game.WIDTH/2+275, 365);
		}
		if(where == 4) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-5, 400); 
			polygon.addPoint(Game.WIDTH/2+15,400);
			polygon.addPoint(Game.WIDTH/2+5, 415); 
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);			
			g.fillRect(Game.WIDTH/2 - 150, 420, 300, 80);
			g.setColor(Color.black);
			g.drawString("BULLET SPEED: "+Control.bulletSpeed+"/40", Game.WIDTH/2-138, 451);
			g.drawString("1500 COINS", Game.WIDTH/2-77, 485);
		}
		if(where == 5) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-15, 570); 
			polygon.addPoint(Game.WIDTH/2+5,570);
			polygon.addPoint(Game.WIDTH/2-5, 585); 
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 150, 590, 300, 80);
			g.setColor(Color.black);
			g.drawString("BACK", Game.WIDTH/2-40, 640);
		}
	}
	
	private void renderHelp(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		Font font = new Font("comic sans ms",1,18);
		g.setFont(font);
		
		
		
		g.setColor(Color.white);
		g.fillRect(140, 165, 500, 40);
		g.setColor(Color.black);
		g.drawString("This is you. Use arrow keys to navigate", 200, 192);
		g.drawOval(150, 170, 32, 32);
		
		g.setColor(Color.white);
		g.fillRect(140, 215, 500, 46);
		g.setColor(Color.black);
		g.drawString("Type: Regular Shape. -10 health if you collide", 200, 233);
		g.drawString("New spawn every 3 levels", 200, 256);
		g.setColor(Color.red);
		g.fillRect(157	,230, 16, 16);
		
		g.setColor(Color.white);
		g.fillRect(140, 270, 500, 120);
		g.setColor(Color.black);
		g.drawString("Type: Shooter Shape. It moves up and down", 200, 300);
		g.drawString("Shoots red missiles at you (-5 health):", 200, 325);
		g.drawString("You shoot bullets. Try to hit this shape", 200, 350);
		g.drawString("+150 coins every time this shape is defeated", 200, 375);
		
		g.setColor(Color.red);
		g.fillRoundRect(146, 280, 40, 100, 10, 10);
		g.fillOval(550, 316, 30, 10);
		
		int w=120;
		int h=75;
		
		g.setColor(Color.white);
		g.fillRect(140, 400, 500, 90);
		
		g.setColor(new Color(31, 71, 176));
		Polygon polygon = new Polygon();
		polygon.addPoint(203, 406);
		polygon.addPoint(203-w/2, 406+h/2);
		polygon.addPoint(203, 406+h);
		polygon.addPoint(203+w/2, 406+h/2);
		g2.fillPolygon(polygon);
		w=10;
		h=30;
		int round=3;
		int xpos = 580;
		int ypos = 452;
		Polygon polygon2 = new Polygon();
		polygon2.addPoint(xpos, ypos);
		polygon2.addPoint(xpos-w/2, ypos);
		polygon2.addPoint(xpos-w/2, ypos+h);
		polygon2.addPoint(xpos, ypos+h+round);
		polygon2.addPoint(xpos+w/2, ypos+h);
		polygon2.addPoint(xpos+w/2, ypos);
		g2.fillPolygon(polygon2);
		g.setColor(Color.black);
		g.drawString("Type: Boss Shape. (Not to Scale)", 280, 425);
		g.drawString("Appears every 5 levels", 280, 450);
		g.drawString("Rains down missiles (-2 damage):", 280, 475);
		
		g.setColor(Color.white);
		g.fillRect(140, 500, 500, 50);
		g.setColor(Color.black);
		g.drawString("Type: Fast Shape. -10 health if you collide", 200, 520);
		g.drawString("New spawn every 8 levels", 200, 542);
		g.setColor(new Color(255,225,0));
		g.fillRect(159	,515, 16, 16);
		
	
		font = new Font("comic sans ms", 1,29);
		g.setFont(font);
		g.setColor(Color.white);
		g.fillRect(660, 165, 500, 385);
		g.setColor(Color.black);
		g.drawString("STORE ITEMS", 798, 192);
		g.drawLine(790, 200, 1018, 200);
		
		font = new Font("comic sans ms", 1,15);
		g.setFont(font);
		g.drawString("1. Extra Life.",670,230);
		g.drawString("    If your health goes to 0 you can respawn with an extra life.",670,250);
		g.drawString("2. Carnage Chips.",670,280);
		g.drawString("   When carnage mode is activated if you collide with shapes you",670,300);
		g.drawString("   get coins and your health is unafected - Space to activate",673,320);
		g.drawString("3. Player Speed.",670,350);
		g.drawString("   Increases speed. Max Speed: 20",671,370);
		g.drawString("4. Bullet Speed.",670,400);
		g.drawString("   Decreases interval between two bullets. Max Speed: 40",671,420);
		
		g.drawLine(660, 430,1160 , 430);
		font = new Font("comic sans ms", 1,20);
		g.setFont(font);
		g.setColor(new Color(31, 71, 176));
		g.drawString("DEFAULT VALUES", 815, 455);
		font = new Font("comic sans ms", 1,17);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Extra Lives: 0", 700, 490);
		g.drawString("Carnage Chips: 1", 700, 525);
		g.drawString("Player Speed: 5", 950, 490);
		g.drawString("Bullet Speed: 2", 956, 525);
		
		g.setFont(font);
		
		g.setColor(Color.white);
		Polygon polygon1 = new Polygon();
		polygon1.addPoint(Game.WIDTH/2-5, 570); 
		polygon1.addPoint(Game.WIDTH/2+15,570);
		polygon1.addPoint(Game.WIDTH/2+5, 585); 
		g2.fillPolygon(polygon1);
		
		
		Font font2 = new Font("arial", 1, 27);
		g.setFont(font2);
		g.setColor(Color.cyan);
		g.fillRect(Game.WIDTH/2 - 150, 590, 300, 80);
		g.setColor(Color.black);
		g.drawString("BACK", Game.WIDTH/2-40, 640);
	}
	
}


