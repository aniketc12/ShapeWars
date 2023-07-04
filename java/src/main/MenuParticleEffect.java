package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class MenuParticleEffect extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	 
	
	private Color color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	private Color color2 = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	private Color color3 = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	
	public MenuParticleEffect(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = r.nextInt(7 - -7)+-7;
		velY = r.nextInt(7 - -7)+-7;
		if(velX == 0) {
			velX = 5;
		}
	}
	
	@Override
	public Ellipse2D bounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,20,20);
	}

	@Override
	public void tick() {
		x +=velX;
		y +=velY;
		
		velX=Game.velocityClamp(x, velX, 1, Game.WIDTH-30);
		velY=Game.velocityClamp(y, velY, 150, Game.HEIGHT-45);
		
		
		
		handler.addObject(new Trail(x, y, ID.Trail, color, 20, 20, 0.05f, handler));
		handler.addObject(new Trail(x+1, y+1, ID.Trail, color2, 20, 20, 0.05f, handler));
		handler.addObject(new Trail(x+1, y+1, ID.Trail, color3, 20, 20, 0.05f, handler));
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, 20, 20);
		
		
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}


}
