package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class ToughEnemyMissile extends GameObject {
	
	public ToughEnemyMissile(float x, float y, ID id, Handler handler) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		x -=10;
		
	}@Override
	public Ellipse2D bounds() {
		return null;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int)x, (int)y, 30, 10);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,30,10);
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
