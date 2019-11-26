package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class FastEnemy extends GameObject{
	
	private Handler handler;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 10;
		velY = 10;
		
		
	}
	@Override
	public Ellipse2D bounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}

	@Override
	public void tick() {
		x +=velX;
		y +=velY;
		
		velX=Game.velocityClamp(x, velX, 0, Game.WIDTH-16);
		velY=Game.velocityClamp(y, velY, 0, Game.HEIGHT-32);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 20, 20, 0.05f, handler));
		}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 20, 20);
		
	}
	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}


}
