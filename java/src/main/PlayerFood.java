package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class PlayerFood extends GameObject {
	
	public static float speed; 
	
	private Handler handler;

	public PlayerFood(int x, int y, ID id, float speed, Handler handler) {
		super(x, y, id);
		
		this.speed = speed;
		this.handler = handler;


		velX = speed;
		velY = speed;
		
	}

	@Override
	public void tick() {
		x +=velX;
		y +=velY;
		
		velX=Game.velocityClamp(x, velX, 0, Game.WIDTH-20);
		velY=Game.velocityClamp(y, velY, 0, Game.HEIGHT-48);
		
		collision();
		
	}
	
	private void collision() {
		for(int i=0; i<handler.object.size();i++) {
			 GameObject tempObject = handler.object.get(i);
			  if(tempObject.getId() == ID.ToughEnemy) {
				 if(bounds().intersects(tempObject.getBounds())) {
					velX *= -1;
				 }
				 
			 }
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		
		g.setColor(Color.green);
		g.fillOval((int)x, (int)y, 20, 20);
		
		
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,20,20);
	}

	@Override
	public Ellipse2D bounds() {
		Ellipse2D shape = new Ellipse2D.Float();
	    shape.setFrame((int)x, (int)y, 20,20);
		return shape;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}



}
