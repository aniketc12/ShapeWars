package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class ToughEnemy extends GameObject{
	
	private Handler handler;
	public static int enemyGreenValue = 200;
	public static int enemyHealth;
	private long time = System.currentTimeMillis(); 
	private boolean entranceComplete = false;
	Random r;
	public int timer = 0;
	public int timer2 = 0;
	
	public ToughEnemy(int x, int y, ID id, Handler handler, int enemyHealth) {
		super(x, y, id);
		
		this.handler = handler;
		this.enemyHealth = enemyHealth;
		
		velX = -2;
		velY = 0;
		r = new Random();
		
		
	}
	
	public int getHealth() {
		return enemyHealth;
	}
	
	@Override
	public Ellipse2D bounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,40,100);
	}

	@Override
	public void tick() {
		x +=velX;
		y +=velY;
		
		if(timer<=100) {
			timer++;
		}
		else if(entranceComplete == false){
			velX =0;
			velY=r.nextInt(1)+1;
			entranceComplete = true;
		}
		
		velY=Game.velocityClamp((int)y, velY, 0, Game.HEIGHT-130);
		
		for(int i=0; i<handler.object.size();i++) {
			 GameObject tempObject = handler.object.get(i);
			 
			 if(tempObject.getId() == ID.ToughEnemy) {
				 if(timer2<=80) {
					 timer2++;
					 
				 }else {
					 timer2=0;
					 handler.addObject(new ToughEnemyMissile(tempObject.getX(), tempObject.getY()+50, ID.ToughEnemyMissiles, handler));
					
				 }
			 }
			 
			 
			 
			 else if(tempObject.getId() == ID.Bullets) {
				 if(getBounds().intersects(tempObject.getBounds())) {
					 enemyHealth -= Game.bulletDamage;
					 handler.removeObject(tempObject);
				 }
			 }
		}	
		
		if(enemyHealth <= 0) {
			
			Game.toughEnemyExists = false;
			Control.money +=150;
			handler.removeObject(this);
			
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRoundRect((int)x, (int)y, 40, 100, 10, 10);
		
		
		g.setColor(Color.GRAY);
		g.fillRect((int)x+7, (int)y-30, 30, 8);
		g.setColor(new Color(150, enemyGreenValue, 0));
		g.fillRect((int)x+7, (int)y-30, enemyHealth*2, 8);
		g.setColor(Color.WHITE);
		g.drawRect((int)x+7, (int)y-30, enemyHealth*2, 8);
		
	}


}
