package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class EnemyBossMissile extends GameObject {
	
	Random r;
	
	public EnemyBossMissile(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		r=new Random();
		velX = r.nextInt(5 - -5)+ -5;
	}

	@Override
	public void tick() {
		
		x -= velX;
		y += 6;
		
	}@Override
	public Ellipse2D bounds() {
		return null;
	}

	@Override
	public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
		
		g.setColor(new Color(31, 71, 176));
		
		int w=10;
		int h=30;
		int round=3;

		Polygon polygon = new Polygon();
		polygon.addPoint((int)x, (int)y);
		polygon.addPoint((int)x-w/2, (int)y);
		polygon.addPoint((int)x-w/2, (int)y+h);
		polygon.addPoint((int)x, (int)y+h+round);
		polygon.addPoint((int)x+w/2, (int)y+h);

		polygon.addPoint((int)x+w/2, (int)y);
		g2.fillPolygon(polygon);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,10,30);
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
