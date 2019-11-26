package com.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class TrailOval extends GameObject {
	
	private float alpha = 1;
	Handler handler;
	private Color color;
	private float diameter;
	public float life;
	public static boolean display = true;

	public TrailOval(float x, float y, ID id, Color color, float diameter, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.diameter = diameter;
		this.life = life;
	}

	@Override
	public void tick() {
		if(alpha>life && display == true) {
			alpha -= life - 0.001f;
			
		}
		else {
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
		
		g2.setComposite(makeTransparent(1));
		
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ellipse2D bounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
