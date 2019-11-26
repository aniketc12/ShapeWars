package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class EnemyBoss extends GameObject{
	
	private Handler handler;
	//private long time = System.currentTimeMillis();
	public static int timer = 0;
	private boolean entranceComplete = false;
	Random r;
	private int w=300;
	private int h=150;
	
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
		r = new Random();
		
	}
	@Override
	public Ellipse2D bounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(5, h+75, Game.WIDTH-20, Game.HEIGHT-265);
	}

	@Override
	public void tick() {
		x +=velX;
		y +=velY;
		
		if(timer<=200) {
			timer++;
		}
		else if(entranceComplete == false){
			velX =5;
			velY=0;
			entranceComplete = true;
		}
		else if(timer>205) {
			timer++;
			
			if(timer<2900) {
				if(r.nextInt(11)==0) {
					handler.addObject(new EnemyBossMissile((int)x, (int)y+75, ID.EnemyBossMissiles, handler));
				}
				if(x==200) {
					velX *=1.3;
				}
			}
			
			else if(timer>=2900) {
				velX = 0;
			}
			if(timer>=2920) {
				velY = -2;
			}			
		}
		
		else {
			
		}
		
		velX=Game.velocityClamp((int)x, velX, 150, Game.WIDTH-150);
		
		
		
				
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		
		g.setColor(new Color(31, 71, 176));
		
		

		Polygon polygon = new Polygon();
		polygon.addPoint((int)x, (int)y);
		polygon.addPoint((int)x-w/2, (int)y+h/2);
		polygon.addPoint((int)x, (int)y+h);
		polygon.addPoint((int)x+w/2, (int)y+h/2);
		g2.fillPolygon(polygon);
		
		g.setColor(Color.DARK_GRAY);
		g.drawRect(5, h+75, Game.WIDTH-20, Game.HEIGHT-265);
		
		
		
	}
	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}


}
