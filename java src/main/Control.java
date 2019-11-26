package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;




public class Control {
	
	
	private static int BasicEnemy = 0;
	private static int FastEnemy = 0;
	
	private static boolean PlayerFoodRemoved = false;
	private static boolean BasicEnemyRemoved = false;
	private static boolean FastEnemyRemoved = false;
	private static boolean FollowEnemyRemoved = false;
	private static boolean ToughEnemyRemoved = false;
	
	private static int defaultNumberOfLives = 0;
	
	public static int numberOfCarnageModeChips = 1;
	public static boolean showCarnageModeErrorMessage = false;
	private static int display = 0;
	private static int errorMessageTimer = 0;
	public static int money = 0;
	
	public static int bulletSpeed = 2;
	public static int playerSpeed = 5;
		
	public static int HEALTH = 100;
	private static int greenValue = 255;
	public static int numberOfLives = defaultNumberOfLives;
	
	private int toughEnemyHealth;
	
	public static int score = 0;
	private static int level = 1;
	private static int tempScore = 0;
	Handler handler;
	Random r ;
	public static boolean enemyBossActive = false;
	public static boolean shouldEnemyBossBeActived = false;
	private static boolean shouldSpawnBack = false;
	
	private long enemyBossActivateTime;
	
	public Control(Handler handler) {
		this.handler = handler;
		r = new Random();
		
	}
	
	public void tick() {
		HEALTH = (int)Game.clamp(HEALTH, 0, 100);
		greenValue =(int) Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH*2;
		updateLevel();
		
		if(HEALTH <=0) {
			if(numberOfLives >0 ) {
				HEALTH = 100;
				numberOfLives--;
			}
		}
		
		
	}
	
	public static void reset() {
		BasicEnemy = 0;
		FastEnemy = 0;
		
		PlayerFoodRemoved = false;
		BasicEnemyRemoved = false;
		FastEnemyRemoved = false;
		FollowEnemyRemoved = false;
		ToughEnemyRemoved = false;
		
		defaultNumberOfLives = 0;
		
		numberOfCarnageModeChips = 1;
		showCarnageModeErrorMessage = false;
		display = 0;
		errorMessageTimer = 0;
		money = 0;
		
		bulletSpeed = 2;
		playerSpeed = 5;
		
		Game.playerDiameter = 80;
			
		HEALTH = 100;
		greenValue = 255;
		numberOfLives = defaultNumberOfLives;
		score = 0;
		level = 1;
		tempScore = 0;
		Handler handler;
		Random r ;
		enemyBossActive = false;
		shouldEnemyBossBeActived = false;
		shouldSpawnBack = false;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 20);
		g.setColor(new Color(150, greenValue, 0));
		g.fillRect(15, 15, HEALTH*2, 20);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, HEALTH*2, 20);
		
		g.drawString("Score: "+score, Game.WIDTH-125, 20);
		g.drawString("Level: "+level, Game.WIDTH-125, 40);
		g.drawString("High Score: "+Game.highScore, Game.WIDTH-125, 60);
		
		Font font1 = new Font("comic sans ms", 1, 17);
		g.setFont(font1);
		
		g.drawString("Extra Lives: "+numberOfLives, 20, 60);
		g.drawString("Coins: "+money, 20, 80);
		g.drawString("Carnage Chips Remaining: "+numberOfCarnageModeChips, 20, 100);
		if(Player.carnageModeActive == true) {	
			Font font = new Font("arial", 1, 20);
			g.setFont(font);
			int time = 1500 - Player.carnageModeTimer;
			if(time%10==0) {
				display = time;
			}

			g.drawString("Carnage Mode Time Counter: "+display, 500, 80);
		}
		
		if(showCarnageModeErrorMessage == true) {
			if(errorMessageTimer<=35) {
				errorMessageTimer++;
				Font font = new Font("arial", 1, 30);
				g.setFont(font);
				g.drawString("No Carnage Chips Remaining", 450, 100);
			}
			else {
				errorMessageTimer = 0;
				showCarnageModeErrorMessage = false;
			}
			
		}
	}
	
	public static void moreScore() {
		score += 25;
		tempScore += 25;
		
	}
	
	private void updateLevel() {
		if(tempScore>=100) {
			
			tempScore = 0;
			level++;
			
			if(level%5==0) {
				shouldEnemyBossBeActived = true;
			}			
			if(level%3==0) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100)+30,r.nextInt(Game.HEIGHT-100)+30, ID.BasicEnemy, handler));
			}
			if(level%8==0) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-110)+40,r.nextInt(Game.HEIGHT-110)+40, ID.FastEnemy, handler));
			}
			
		}
		
		
		
		if(shouldEnemyBossBeActived == true) {
			addEnemyBoss();
		}
		if(enemyBossActive == true) {
			removeEnemyBoss();
		}
	
	}
	
	private void addEnemyBoss() {
		if(PlayerFoodRemoved == false || ToughEnemyRemoved == false || BasicEnemy < level/3+1 || FastEnemy < level/8){
			
			
				for(int i=0; i<handler.object.size();i++) {
					 GameObject tempObject = handler.object.get(i);
					 if(tempObject.getId() == ID.PlayerFood) {
						 handler.removeObject(tempObject);
						 PlayerFoodRemoved = true;
						
					 }
					 
					 else if(tempObject.getId() == ID.ToughEnemy) {
						 toughEnemyHealth = tempObject.getHealth();
						 handler.removeObject(tempObject);
						 ToughEnemyRemoved = true;
						 
					 }
					 
					 else if(tempObject.getId() == ID.BasicEnemy) {
						 handler.removeObject(tempObject);
						 BasicEnemy++;
					 }
					 else if(tempObject.getId() == ID.FastEnemy) {
						 handler.removeObject(tempObject);
						 FastEnemy++;
					 }
				}
			
		}
		else {
			
			handler.addObject(new EnemyBoss(Game.WIDTH/2,-200, ID.EnemyBoss, handler));
			shouldEnemyBossBeActived = false;
			enemyBossActive = true;
			enemyBossActivateTime = System.currentTimeMillis();
		}
	}
	
	private void removeEnemyBoss() {
		if(EnemyBoss.timer<=3150) {
			
			EnemyBoss.timer++;
		}
		else {
			
			for(int i=0; i<handler.object.size();i++) {
				 GameObject tempObject = handler.object.get(i);
				 if(tempObject.getId() == ID.EnemyBoss) {
					 handler.removeObject(tempObject);
					 enemyBossActive = false;
				 }
			}
			
			handler.addObject(new PlayerFood(r.nextInt(Game.WIDTH-80)+35,r.nextInt(Game.HEIGHT-80)+35, ID.PlayerFood, 5, handler));
			PlayerFoodRemoved = false;
			handler.addObject(new ToughEnemy(Game.WIDTH+100,r.nextInt(Game.HEIGHT-200)+10, ID.ToughEnemy, handler,toughEnemyHealth));
			ToughEnemyRemoved = false;
			
			for(int i = 0; i<=level/3; i++) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100)+30,r.nextInt(Game.HEIGHT-100)+30, ID.BasicEnemy, handler));
			}
			for(int i = 1; i<=level/8; i++) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-110)+40,r.nextInt(Game.HEIGHT-110)+40, ID.FastEnemy, handler));
			}
			BasicEnemy = 0;
			FastEnemy = 0;
			
			
		}
	}
	
	public static void checkHighScore() {
		if(score>Integer.parseInt(Game.highScore)) {
			Game.setNewHighScore(""+score);
			Game.setHighScore();
		}
	}

}
