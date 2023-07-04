package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Bullets extends GameObject {
	
	private Handler handler;
	Random r;
	
	public Bullets(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		r = new Random();
	}

	@Override
	public void tick() {
		x +=25;
		
		
	}@Override
	public Ellipse2D bounds() {
		Ellipse2D shape = new Ellipse2D.Float();
	    shape.setFrame((int)x, (int)y, 14,5);
		return shape;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x, (int)y, 14, 5);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,14,5);
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
