package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class ShapeWarsMenuEffect extends GameObject{
	
	private Handler handler;
	private long time = System.currentTimeMillis(); 
	private boolean entranceComplete = false;
	Random r;
	private int w=300;
	private int h=150;
	private int red,green,blue;
	private int i=0;
	Color color;
	private boolean redShouldIncrease = true;
	private boolean greenShouldIncrease = true;
	private boolean blueShouldIncrease = true;
	
	private int redChange =0;
	private int greenChange = 0;
	private int blueChange = 0;
	
	private int timer = 0;
	
	private long colorChangeTime;
	
	private Color[] colors;
	
	public ShapeWarsMenuEffect(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
		r = new Random();
		
		colorChangeTime = System.currentTimeMillis();
		
		red = r.nextInt(154);
		green = r.nextInt(154);
		blue = r.nextInt(154);
		
		
	}
	@Override
	public Ellipse2D bounds() {
		Ellipse2D shape = new Ellipse2D.Float();
	    shape.setFrame(x, y, 320,100);
		return shape;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(0, 40, Game.WIDTH, Game.HEIGHT - 40);
	}

	@Override
	public void tick() {
		x +=velX;
		y +=velY;
		
		setColors();
		
		
		
		if(timer <=110) {
			timer++;
		}
		else if(entranceComplete == false){
			velX =5;
			velY=0;
			entranceComplete = true;
		}
		
		
		velX=Game.velocityClamp((int)x, velX, 0, Game.WIDTH-300);
		
		
		
				
	}
	
	public void setColors() {
		if(redChange>=100) {
			redShouldIncrease = false;
		}
		
		if(redChange<=10) {
			redShouldIncrease = true;
		}
		if(greenChange>=100) {
			greenShouldIncrease = false;
		}
		
		if(greenChange<=10) {
			greenShouldIncrease = true;
		}
		if(blueChange>=100) {
			blueShouldIncrease = false;
		}
		
		if(blueChange<=10) {
			blueShouldIncrease = true;
		}
		
		if(System.currentTimeMillis() - colorChangeTime >=70) {
			if(redShouldIncrease== true) {
				redChange = redChange + r.nextInt(5);
			}
			else {
				redChange = redChange - r.nextInt(5);;
			}
			if(greenShouldIncrease== true) {
				greenChange = greenChange + r.nextInt(5);
			}
			else {
				greenChange = greenChange - r.nextInt(5);;
			}
			if(blueShouldIncrease== true) {
				blueChange = blueChange + r.nextInt(5);
			}
			else {
				blueChange = blueChange - r.nextInt(5);;
			}
			colorChangeTime = System.currentTimeMillis();
		}
		
		color = new Color(red+redChange,green+i,blue+i);
	}

	@Override
	public void render(Graphics g) {
		
		
		Font font = new Font("comic sans ms", 1, 50);
		
		g.setFont(font);
		g.setColor(color);
		g.fillOval((int)x,(int)y, 320, 100);
		
		g.setColor(new Color(2,16,66));
		g.fillRect(1, 150, Game.WIDTH - 14, Game.HEIGHT - 187);
		
		
		
		g.setColor(Color.white);
		g.drawString("Shape Wars", (int)x+12, (int) y+64);
		
		
		
	}
	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}


}
