package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 4088146271165387233L;
	
	public static final int WIDTH = 1280, HEIGHT = WIDTH/16*9;
	public static int playerDiameter = 80;
	
	public static int bulletDamage = 5;
	
	public static int toughEnemyHealth = 15;
	public static boolean toughEnemyExists = true;
	
	public static String highScore = "";
	
	
	public static boolean paused = false;
	public boolean setPauseState = false;
	
	
	
	public int frameX = 0;
	public int frameY = 0;
	
	
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private Control control;
	private Menu menu;
	private Store store;
	
	private Handler pauseHandler;
	private pauseMenu pauseMenu;
	
	public enum STATE{
		Menu,
		Game,
		GameOver,
		Store,
		Paused,
		PausedStore,
		Help,
		PausedHelp
	};
	
	public static STATE gameState = STATE.Menu;
	Window window;
	public String filepath;
	
	public Game() {
		handler = new Handler();
		pauseHandler = new Handler();
		
		
		pauseMenu = new pauseMenu(this, pauseHandler);
		
		window = new Window(WIDTH, HEIGHT, "SHAPE WARS", this);
		
		this.addKeyListener(new KeyInput(handler));
		
		control = new Control(handler);
		
		r=new Random();
		
		//setNewHighScore("0");
		
		setHighScore();
		
		if(gameState == STATE.Game) {

		}
		else {
			
			
			
			
			menu = new Menu(this, handler);
			
			

			this.addMouseListener(menu);
			this.addMouseListener(pauseMenu);
		}
		
		
	
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000/amountOfTicks;
		double delta =0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>=1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;	
			
			if(System.currentTimeMillis() - timer>1000) {
				timer +=1000;
				//System.out.println("FPS: "+frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick() {
		
		frameX = window.getFrameX();
		frameY = window.getFrameY();
		
		
		
		if(!paused) {
			setPauseState = false;
			if(gameState == STATE.Menu || gameState == STATE.GameOver || gameState == Game.STATE.Store || gameState == Game.STATE.PausedStore|| gameState == Game.STATE.Help|| gameState == Game.STATE.PausedHelp) {
				
			}else{
				gameState = STATE.Game;
			}
			handler.tick();
			
			if(gameState == STATE.Game) {
				control.tick(); 
				if(control.HEALTH <= 0) {
					handler.object.clear();
					gameState = STATE.GameOver;
				}
			}
			else if(gameState == STATE.Menu || gameState == STATE.GameOver || gameState == Game.STATE.Store|| gameState == Game.STATE.Help) {
				
				menu.tick();
				
			}
			
		}
		else {
			
			if(setPauseState == false) {
				gameState = STATE.Paused;
				setPauseState = true;
			}
			pauseHandler.tick();
			pauseMenu.tick();
		}
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g =bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		
		if(paused) {
			pauseHandler.render(g);
			pauseMenu.render(g);
		}
		
		else {
			handler.render(g);
			if(gameState == STATE.Game) {
				control.render(g); 
			}
			else if(gameState == STATE.Menu || gameState == STATE.GameOver|| gameState == Game.STATE.Store|| gameState == Game.STATE.Help) {
				
				menu.render(g);
			}
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float wrapAround(float var, float min, float max) {
		if(var>max) {
			return min;
		}
		else if(var<min) {
			return max;
		}
		else {
			return var;
		}
	}
	
	public static float velocityClamp(float pos, float vel, float min, float max) {
		if(pos>=max||pos<=min) {
			return vel = vel*-1;
		}
		else {
			return vel;
		}
		
	}
	
	public static float clamp(float var, float min, float max) {
		if(var>=max) {
			return max;
		}
		else if(var<=min) {
			return min;
		}
		else {
			return var;
		}
	}
	
	public static void setHighScore() {
		
	    BufferedWriter writer;
	   
	    String st = "";
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("res/desk.dat"));
			for(int i=0;i<23;i++) {
				if((st = br.readLine()) ==null) {
					remakeDesk();
				}
				else if(i==5) {
					highScore = decode(st);
				}
			}
			 br.close();
		     
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setNewHighScore(String high) {
		String st;
		try {
			BufferedReader br = new BufferedReader(new FileReader("res/desk.dat"));
			
			
			
			int i=0;
			String all="";
			while((st = br.readLine())!=null) {
				if(i!=5) {
					all = all+st+"\n";
				}
				else {
					all = all+encode(high)+"\n";
				}
				i++;
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter("res/desk.dat"));
			writer.write(all);
			writer.close();
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String encode(String code) {
		String encoded = "";
		char temp;
		String retu = "";
		for(int i =0; i<code.length();i++) {
			temp = (char) (code.charAt(i) + i + 12);
			encoded = encoded+temp;
		}
		
		code="a+-1vg*^"+encoded+"!)Polsnhjd^^77";
		return code;
	}
	
	public static String decode(String code) {
		String toDecode = "";
		char temp;
		String decoded="";
		if(code.length()>=15) {
			toDecode = code.substring(8, code.length()-14);
			for(int i=0;i<toDecode.length();i++) {
				temp = (char) (toDecode.charAt(i) - i - 12);
				decoded = decoded+temp;
			}
		}
		return decoded;
	}
	
	public static void remakeDesk() {
		BufferedWriter writer;
		try {		
			writer = new BufferedWriter(new FileWriter("res/desk.dat"));
			for(int i=0;i<23;i++) {
				if(i==5) {
					
					writer.write(encode("0"));
					writer.write("\n");
				}
				else if(i%7==0) {
					writer.write(encode(""+i)+"bjck))+_@^VGDJUI@*()(!");
					writer.write("\n");
				}
				else if(i%5==0) {
					writer.write(encode(""+i));
					writer.write("\n");
				}
				else if(i%3==0) {
					writer.write(":}{D#bjck"+encode(""+i)+"ioc jk()!*@W");
					writer.write("\n");
				}
				else {
					writer.write("nicqe!()@ncjklxq__");
					writer.write("\n");
				}
			}

			writer.close();
		     
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		highScore = "0";
	}
	


}
