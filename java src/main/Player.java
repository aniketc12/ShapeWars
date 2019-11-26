package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	Control control;
	private int toughEnemyMissileHitTimer;
	private int enemyBossMissileHitTimer;
	private int basicEnemyHitTimer;
	private int bulletTimer = 0;
	public static int carnageModeTimer = 0;
	
	public static boolean carnageModeActive = false;
	
	private boolean createFollowEnemy = false;

	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		control = new Control(handler);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,Game.playerDiameter,Game.playerDiameter);
	}
	
	public Ellipse2D bounds() {
		Ellipse2D shape = new Ellipse2D.Float();
	    shape.setFrame(x, y, Game.playerDiameter,Game.playerDiameter);
		return shape;
	}
	



	@Override
	public void tick() {
		x +=velX;
		y +=velY;
		
		x = Game.wrapAround(x, -32,Game.WIDTH-32 );		
		y = Game.wrapAround(y, 0, Game.HEIGHT-40);	
		
		if(carnageModeActive == true) {
			if(carnageModeTimer<=1500) {
				carnageModeTimer++;
			}
			else {
				carnageModeActive = false;
				carnageModeTimer = 0;
			}
		}
		
		
		
		collision();
	}
	
	private void collision() {
		for(int i=0; i<handler.object.size();i++) {
			 GameObject tempObject = handler.object.get(i);
			 
			 //shoot bullets
			 if(tempObject.getId() == ID.Player) {
				 if(bulletTimer<=40 - Control.bulletSpeed) {
					 bulletTimer++;
				 }
				 else {
					 if(Control.enemyBossActive == false) {
						 handler.addObject(new Bullets(tempObject.getX()+Game.playerDiameter, tempObject.getY()+Game.playerDiameter/2, ID.Bullets, handler));
					 }
					 bulletTimer = 0;
				 }
			 }
			 //if eat player food
			 else if(tempObject.getId() == ID.PlayerFood) {
				 if(bounds().intersects(tempObject.getBounds())) {
					 String filepath="res/pop.wav";
						Audio audio = new Audio();
						audio.playMusic(filepath);
					 Game.playerDiameter +=2;
					 Control.moreScore();
					 Control.money +=200;
					 handler.removeObject(handler.object.get(i));
					 handler.addObject(new PlayerFood(r.nextInt(Game.WIDTH-80)+30,r.nextInt(Game.HEIGHT-80)+30, ID.PlayerFood, PlayerFood.speed, handler));					 
				 }
				 else if(tempObject.getX()<0 && tempObject.getX()> Game.WIDTH + 50) {
					 handler.removeObject(tempObject);
					 handler.addObject(new PlayerFood(r.nextInt(Game.WIDTH-80)+30,r.nextInt(Game.HEIGHT-80)+30, ID.PlayerFood, PlayerFood.speed, handler));					 
				}
				 else if(tempObject.getY()<0&&tempObject.getY()>Game.HEIGHT+50) {
					 handler.removeObject(tempObject);
					 handler.addObject(new PlayerFood(r.nextInt(Game.WIDTH-80)+30,r.nextInt(Game.HEIGHT-80)+30, ID.PlayerFood, PlayerFood.speed, handler));					 
				 }
			 }
			 //if bullets go out of frame remove them
			 else if(tempObject.getId() == ID.Bullets) {
				 if(tempObject.getX() > Game.WIDTH + 20) {
					 handler.removeObject(handler.object.get(i));
				 }				 
			 }
			 
			 if(carnageModeActive == false) {
				 
				//if collision with basic enemy
				 if(tempObject.getId() == ID.BasicEnemy) {
					 if(bounds().intersects(tempObject.getBounds())) {
						 Control.HEALTH -=10;
						 String filepath="res/hit.wav";
							Audio audio = new Audio();
							audio.playMusic(filepath);
						 handler.removeObject(handler.object.get(i));
						 basicEnemyHitTimer = 1;
						 if(Control.enemyBossActive == false) {
							 handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100)+30,r.nextInt(Game.HEIGHT-100)+30, ID.BasicEnemy, handler));
						 }
					 }
					 
				 }
				 
				 //if collision with toughEnemy missiles
				 else if(tempObject.getId() == ID.ToughEnemyMissiles) {
					 if(bounds().intersects(tempObject.getBounds())) {
						 String filepath="res/missile.wav";
							Audio audio = new Audio();
							audio.playMusic(filepath);
						 Control.HEALTH -=5;
						 handler.removeObject(handler.object.get(i));
						 toughEnemyMissileHitTimer = 1;
						 }				 
				 }
				 
				 
				 //if collision with enemyboss missiles
				 else if(tempObject.getId() == ID.EnemyBossMissiles) {
					 if(bounds().intersects(tempObject.getBounds())) {
						 String filepath="res/missile.wav";
							Audio audio = new Audio();
							audio.playMusic(filepath);
						 Control.HEALTH -=2;
						 handler.removeObject(handler.object.get(i));
						 enemyBossMissileHitTimer = 1;
						 }	
					 else if(tempObject.getY()>Game.HEIGHT) {
						 handler.removeObject(tempObject);
					 }
				 }
				 
				 //if collision with fast enemy
				 else if(tempObject.getId() == ID.FastEnemy) {
					 if(bounds().intersects(tempObject.getBounds())) {
						 String filepath="res/hit.wav";
							Audio audio = new Audio();
							audio.playMusic(filepath);
						 Control.HEALTH -=10;
						 handler.removeObject(handler.object.get(i));
						 handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-60)+25,r.nextInt(Game.HEIGHT-60)+25, ID.FastEnemy, handler));
					 }
					 
				 }
				 
				 
				 
			 }
			 else if(carnageModeActive == true) {
				 
					//if collision with basic enemy
					 if(tempObject.getId() == ID.BasicEnemy) {
						 if(bounds().intersects(tempObject.getBounds())) {
							 String filepath="res/blop.wav";
								Audio audio = new Audio();
								audio.playMusic(filepath);
							 handler.removeObject(handler.object.get(i));
							 Control.money +=200;
							 basicEnemyHitTimer = 1;
							 if(Control.enemyBossActive == false) {
								 handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100)+30,r.nextInt(Game.HEIGHT-100)+30, ID.BasicEnemy, handler));
							 }
						 }
						 
					 }
					 
					 //if collision with toughEnemy missiles
					 else if(tempObject.getId() == ID.ToughEnemyMissiles) {
						 if(bounds().intersects(tempObject.getBounds())) {
							 String filepath="res/blop.wav";
								Audio audio = new Audio();
								audio.playMusic(filepath);
							 handler.removeObject(handler.object.get(i));
							 toughEnemyMissileHitTimer = 1;
							 Control.money +=15;
							 }				 
					 }
					 
					 
					 //if collision with enemyboss missiles
					 else if(tempObject.getId() == ID.EnemyBossMissiles) {
						 if(bounds().intersects(tempObject.getBounds())) {
							 String filepath="res/blop.wav";
								Audio audio = new Audio();
								audio.playMusic(filepath);
							 handler.removeObject(handler.object.get(i));
							 enemyBossMissileHitTimer = 1;
							 Control.money +=15;
							 }	
						 else if(tempObject.getY()>Game.HEIGHT) {
							 handler.removeObject(tempObject);
						 }
					 }
					 
					 //if collision with fast enemy
					 else if(tempObject.getId() == ID.FastEnemy) {
						 if(bounds().intersects(tempObject.getBounds())) {
							 String filepath="res/blop.wav";
								Audio audio = new Audio();
								audio.playMusic(filepath);
							 handler.removeObject(handler.object.get(i));
							 handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-60)+25,r.nextInt(Game.HEIGHT-60)+25, ID.FastEnemy, handler));
							 Control.money +=200;
						 }
						 
					 }
					 
					 
				 }
			 
			 	 
			 
		}
		
		if(Control.enemyBossActive == true) {
			if(x<=5) {
				x=5;
			}
			if(x>=Game.WIDTH-18-Game.playerDiameter) {
				x=Game.WIDTH-18-Game.playerDiameter;
			}
			if(y>=Game.HEIGHT-40-Game.playerDiameter) {
				y=Game.HEIGHT-40-Game.playerDiameter;
				
			}
			if (y<=225) {
				y=225;
			}
			
		}
		
		
		if(Game.toughEnemyExists == false && Control.enemyBossActive == false) {
			handler.addObject(new ToughEnemy(Game.WIDTH+100,r.nextInt(Game.HEIGHT-200)+10, ID.ToughEnemy, handler, Game.toughEnemyHealth));
			Game.toughEnemyExists = true;
		}
	}

	@Override
	public void render(Graphics g) {
			
		g.setColor(Color.white);
		g.fillOval((int)x, (int)y, Game.playerDiameter, Game.playerDiameter);
		
		//If tough enemy missile hits
		if(toughEnemyMissileHitTimer> 0 && toughEnemyMissileHitTimer <= 6) {	
			toughEnemyMissileHitTimer++;
			g.setColor(Color.red);
			if(carnageModeActive == true) {
				g.setColor(new Color(0,255,0));				
			}
			g.fillOval((int)x, (int)y, Game.playerDiameter, Game.playerDiameter);				
		}
		
		//If basic enemy hits
		if(basicEnemyHitTimer > 0 && basicEnemyHitTimer <= 6) {	
			basicEnemyHitTimer++;
			g.setColor(Color.red);
			if(carnageModeActive == true) {
				g.setColor(new Color(0,255,0));				
			}
			g.fillOval((int)x, (int)y, Game.playerDiameter, Game.playerDiameter);				
		}
		
		
		
		//If enemy boss missile hits
		if(enemyBossMissileHitTimer > 0 && enemyBossMissileHitTimer <= 6) {	
			enemyBossMissileHitTimer++;
			g.setColor(new Color(31, 71, 176));
			if(carnageModeActive == true) {
				g.setColor(new Color(0,255,0));				
			}
			g.fillOval((int)x, (int)y, Game.playerDiameter, Game.playerDiameter);				
		}
		
		if(carnageModeActive == true) {
			g.setColor(new Color(0,255,0));
			g.drawOval((int)x, (int)y, Game.playerDiameter, Game.playerDiameter);
			if(carnageModeTimer>=1300) {
				if(carnageModeTimer%30>5) {
					g.fillOval((int)x, (int)y, Game.playerDiameter, Game.playerDiameter);
				}
				else{
					g.setColor(Color.white);
					g.fillOval((int)x, (int)y, Game.playerDiameter, Game.playerDiameter);
				}
			}
		}
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	
}
