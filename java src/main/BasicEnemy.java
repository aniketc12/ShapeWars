package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class BasicEnemy extends GameObject{
	
	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
		
		
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
		
		velX=Game.velocityClamp((int)x, velX, 0, Game.WIDTH-16);
		velY=Game.velocityClamp((int)y, velY, 0, Game.HEIGHT-32);
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
		}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}


}
