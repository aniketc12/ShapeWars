package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.main.Game.STATE;

public class pauseMenu extends MouseAdapter{
	
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
	
	private boolean renderLifeError = false;
	private boolean renderCarnageError = false;
	private boolean renderPlayerSpeedError = false;
	private boolean renderBulletSpeedError = false;
	private boolean render5000Life = false;
	private boolean render5000Carnage = false;
	private boolean render2000 = false;
	private boolean render1500 = false;
	private boolean renderMaxBulletSpeedMessage = false;
	private boolean renderMaxPlayerSpeedMessage = false;
	
	private int messageTimer = 0;
	
	
	public pauseMenu (Game game, Handler handler) {
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
		if(game.gameState == Game.STATE.Paused) {
			
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 180, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				Game.paused = false;
			}
		
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 300, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				Game.gameState = Game.STATE.PausedStore;
			}
			
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 420, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				Game.gameState = Game.STATE.PausedHelp;
			}
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 540, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				System.exit(1);
			}
		}
		
		else if(game.gameState == Game.STATE.PausedHelp){
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 590, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				where=1;
				game.gameState = Game.STATE.Paused;
				
			}
		}
		
		else if(game.gameState == Game.STATE.PausedStore){
			
			if(mouseOver(mx, my, Game.WIDTH/2 - 500, 300, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				if(Control.money>=5000) {
					Control.money-=5000;
					render5000Life = true;
					Control.numberOfLives++;
				}
				else {
					renderLifeError = true;
				}
			}
		
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 300, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				if(Control.money>=5000) {
					Control.money-=5000;
					render5000Carnage = true;
					Control.numberOfCarnageModeChips++;
				}
				else {
					renderCarnageError = true;
				}
			}
			
			if(mouseOver(mx, my, Game.WIDTH/2 +200, 300, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				if(Control.playerSpeed<20) {
					if(Control.money>=2000) {
						Control.money-=2000;
						render2000 = true;
						Control.playerSpeed++;
					}
					else {
						renderPlayerSpeedError = true;
					}
				}
				else {
					renderMaxPlayerSpeedMessage = true;
				}
			}
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 420, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				if(Control.bulletSpeed<40) {
					if(Control.money>=1500) {
						Control.money-=1500;
						render1500 = true;
						Control.bulletSpeed++;
					}
					else {
						renderBulletSpeedError = true;
					}
				}
				else {
					renderMaxBulletSpeedMessage = true;
				}
			}
			
			if(mouseOver(mx, my, Game.WIDTH/2 - 150, 590, 300, 80)) {
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				Game.gameState = Game.STATE.Paused;
				where=1;
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
        
        
        
        
        if(game.gameState == Game.STATE.Paused) {
				
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
					Game.paused =false;
				}
				
				if(where == 2) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					Game.gameState = Game.STATE.PausedStore;
				}
				if(where == 3) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					Game.gameState = Game.STATE.PausedHelp;
				}
				
				if(where == 4) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					System.exit(1);
				}
			}
			
        }
        
        else if(game.gameState == Game.STATE.PausedHelp) {
        	
			if(enterPressed == true) {
				enterPressed = false;
				String filepath="res/click.wav";
				Audio audio = new Audio();
				audio.playMusic(filepath);
				where=1;
				game.gameState = Game.STATE.Paused;
			}
			
        }
        
        else if(game.gameState == Game.STATE.PausedStore) {
        	if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 500, 300, 300, 80) ) {
				where = 1;
			}
			if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 300, 300, 80) ) {
				where = 2;
			}
			if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 +200, 300, 300, 80) ) {
				where = 3;
			}
			if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 420, 300, 80) ) {
				where = 4;
			}
			if(mouseOver(relativeMouseX, relativeMouseY, Game.WIDTH/2 - 150, 590, 300, 80) ) {
				where = 5;
			}
			
			if(enterPressed == true) {
				enterPressed = false;
				if(where == 1) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					if(Control.money>=5000) {
						
						Control.money-=5000;
						render5000Life = true;
						Control.numberOfLives++;
					}
					else {
						renderLifeError = true;
					}
				}
				
				if(where == 2) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					if(Control.money>=5000) {
						
						Control.money-=5000;
						render5000Carnage = true;
						Control.numberOfCarnageModeChips++;
					}
					else {
						renderCarnageError = true;
					}
				}
				
				if(where == 3) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					if(Control.playerSpeed<20) {
						if(Control.money>=2000) {
							Control.money-=2000;
							render2000 = true;
							Control.playerSpeed++;
						}
						else {
							renderPlayerSpeedError = true;
						}
					}
					else {
						renderMaxPlayerSpeedMessage = true;
					}
					
				}
				
				if(where == 4) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					if(Control.bulletSpeed<40) {
						if(Control.money>=1500) {
							Control.money-=1500;
							render1500 = true;
							Control.bulletSpeed++;
						}
						else {
							renderBulletSpeedError = true;
						}
					}
					else {
						renderMaxBulletSpeedMessage = true;
					}
					
				}
				
				if(where == 5) {
					String filepath="res/click.wav";
					Audio audio = new Audio();
					audio.playMusic(filepath);
					Game.gameState = Game.STATE.Paused;
					where=1;
				}
			}
			
			
        }
		
	}
	
	public void render(Graphics g) {
		
		if(game.gameState == Game.STATE.Paused || game.gameState == Game.STATE.Game ) {
			
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
		}
		
		else if(game.gameState == Game.STATE.PausedStore) {
			if(setWhereStore==false) {
				where = 1;
				setWhereStore=true;
			}
			
			renderStore(g, where);
			renderMessage(g);
		}
		
		else if(Game.gameState == Game.STATE.PausedHelp) {
			
			renderHelp(g);
		}
		
		
		
	}
	
	public void renderTriangle(Graphics g, int where) {
		
		
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
	
	private void renderStore(Graphics g, int where) {
		//add speed add carnage chips add number of lives add bullet speed
		
		Graphics2D g2 = (Graphics2D) g;
		
		Font font2 = new Font("comic sans ms",1,20);
		g.setFont(font2);
		g.setColor(new Color(2,16,66));
		g.fillRect(80, 180, 118, 22);
		g.setColor(Color.white);
		g.drawString("Coins: "+Control.money, 80, 200);
		
		font2 = new Font("comic sans ms", 1, 26);
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
		g.drawString("PLAYER SPEED: "+Control.playerSpeed+"/20", Game.WIDTH/2+207, 330);
		g.drawString("2000 COINS", Game.WIDTH/2+275, 365);
		
		g.setColor(Color.WHITE);
		g.fillRect(Game.WIDTH/2 - 150, 420, 300, 80);
		g.setColor(Color.black);
		g.drawString("BULLET SPEED: "+Control.bulletSpeed+"/40", Game.WIDTH/2-145, 451);
		g.drawString("1500 COINS", Game.WIDTH/2-77, 485);
		
		g.setColor(Color.WHITE);
		g.fillRect(Game.WIDTH/2 - 150, 590, 300, 80);
		g.setColor(Color.black);
		g.drawString("BACK", Game.WIDTH/2-40, 640);
		
		
		if(where == 1) {
			
			g.setColor(Color.white); Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-365, 280); 
			polygon.addPoint(Game.WIDTH/2-345,280);
			polygon.addPoint(Game.WIDTH/2-355, 295); 
			g2.fillPolygon(polygon);
			 
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 500, 300, 300, 80);
			g.setColor(Color.black);
			g.drawString("EXTRA LIVES: "+Control.numberOfLives, Game.WIDTH/2-454, 330); 
			g.drawString("5000 COINS", Game.WIDTH/2-428, 365);
		}
		if(where == 2) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-15, 280); 
			polygon.addPoint(Game.WIDTH/2+5,280);
			polygon.addPoint(Game.WIDTH/2-5, 295); 
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 - 150, 300, 300, 80);
			g.setColor(Color.black);
			g.drawString("CARNAGE CHIPS: "+Control.numberOfCarnageModeChips, Game.WIDTH/2-130, 330);
			g.drawString("5000 COINS", Game.WIDTH/2-77, 365);
		}
		if(where == 3) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2+335, 280); 
			polygon.addPoint(Game.WIDTH/2+355,280);
			polygon.addPoint(Game.WIDTH/2+345, 295); 
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);
			g.fillRect(Game.WIDTH/2 +200, 300, 300, 80);
			g.setColor(Color.black);
			g.drawString("PLAYER SPEED: "+Control.playerSpeed+"/20", Game.WIDTH/2+207, 330);
			g.drawString("2000 COINS", Game.WIDTH/2+275, 365);
		}
		if(where == 4) {
			g.setColor(Color.white);
			Polygon polygon = new Polygon();
			polygon.addPoint(Game.WIDTH/2-15, 400); 
			polygon.addPoint(Game.WIDTH/2+5,400);
			polygon.addPoint(Game.WIDTH/2-5, 415); 
			g2.fillPolygon(polygon);
			
			g.setColor(Color.cyan);			
			g.fillRect(Game.WIDTH/2 - 150, 420, 300, 80);
			g.setColor(Color.black);
			g.drawString("BULLET SPEED: "+Control.bulletSpeed+"/40", Game.WIDTH/2-145, 451);
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
	
	private void renderMessage(Graphics g) {
		g.setColor(Color.green);
		Font font = new Font("comic sans ms", 1, 24);
		g.setFont(font);
		
		if(render5000Life) {
			
			if(messageTimer <= 5) {
				messageTimer++;
				g.drawString("- 5000", 250, 290);
			}
			else if(messageTimer <= 10) {
				messageTimer++;
				g.drawString("- 5000", 250, 270);
			}
			else if(messageTimer <= 15) {
				messageTimer++;
				g.drawString("- 5000", 250, 250);
			}
			else if(messageTimer <= 40) {
				messageTimer++;
				g.drawString("- 5000", 250, 230);
			}
			else {
				messageTimer = 0;
				render5000Life = false;
			}
		}
		if(render5000Carnage) {
			
			if(messageTimer <= 5) {
				messageTimer++;
				g.drawString("- 5000", 600, 290);
			}
			else if(messageTimer <= 10) {
				messageTimer++;
				g.drawString("- 5000", 600, 270);
			}
			else if(messageTimer <= 15) {
				messageTimer++;
				g.drawString("- 5000", 600, 250);
			}
			else if(messageTimer <= 40) {
				messageTimer++;
				g.drawString("- 5000", 600, 230);
			}
			else {
				messageTimer = 0;
				render5000Carnage = false;
			}
		}
		if(render2000) {
			
			if(messageTimer <= 5) {
				messageTimer++;
				g.drawString("- 2000", 950, 290);
			}
			else if(messageTimer <= 10) {
				messageTimer++;
				g.drawString("- 2000", 950, 270);
			}
			else if(messageTimer <= 15) {
				messageTimer++;
				g.drawString("- 2000", 950, 250);
			}
			else if(messageTimer <= 40) {
				messageTimer++;
				g.drawString("- 2000", 950, 230);
			}
			else {
				messageTimer = 0;
				render2000 = false;
			}
			
		}
		if(render1500) {
			
			if(messageTimer <= 5) {
				messageTimer++;
				g.drawString("- 1500", 600, 290);
			}
			else if(messageTimer <= 10) {
				messageTimer++;
				g.drawString("- 1500", 600, 270);
			}
			else if(messageTimer <= 15) {
				messageTimer++;
				g.drawString("- 1500", 600, 250);
			}
			else if(messageTimer <= 40) {
				messageTimer++;
				g.drawString("- 1500", 600, 230);
			}
			else {
				messageTimer = 0;
				render1500 = false;
			}
			
		}
		g.setColor(Color.white);
		if (renderLifeError) {
			
			if(messageTimer <= 45) {
				messageTimer++;
				g.drawString("YOU NEED "+(5000-Control.money)+" COINS MORE", 460, 230);
			}
			else {
				messageTimer = 0;
				renderLifeError = false;
			}
		}
		if(renderCarnageError) {
			
			if(messageTimer <= 45) {
				messageTimer++;
				g.drawString("YOU NEED "+(5000-Control.money)+" COINS MORE", 460, 230);
			}
			else {
				messageTimer = 0;
				renderCarnageError = false;
			}
		}
		if(renderPlayerSpeedError) {
			
			if(messageTimer <= 45) {
				messageTimer++;
				g.drawString("YOU NEED "+(2000-Control.money)+" COINS MORE", 460, 230);
			}
			else {
				messageTimer = 0;
				renderPlayerSpeedError = false;
			}
		}
		if(renderBulletSpeedError) {
			
			if(messageTimer <= 45) {
				messageTimer++;
				g.drawString("YOU NEED "+(1500-Control.money)+" COINS MORE", 460, 230);
			}
			else {
				messageTimer = 0;
				renderBulletSpeedError = false;
			}
		}
		if(renderMaxPlayerSpeedMessage) {
			
			if(messageTimer <= 45) {
				messageTimer++;
				g.drawString("MAX PLAYER SPEED REACHED", 465, 230);
			}
			else {
				messageTimer = 0;
				renderMaxPlayerSpeedMessage = false;
			}
		}
		if(renderMaxBulletSpeedMessage) {
			
			if(messageTimer <= 45) {
				messageTimer++;
				g.drawString("MAX BULLET SPEED REACHED", 465, 230);
			}
			else {
				messageTimer = 0;
				renderMaxBulletSpeedMessage = false;
			}
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


