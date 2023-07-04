package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean [] keyDown = new boolean[5];
	private boolean pressed = false;
	private long whenPaused;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		keyDown[4] = false;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(Game.gameState == Game.STATE.Game) {
		
			for(int i=0;i<handler.object.size();i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					
					if(key == KeyEvent.VK_UP) {
						tempObject.setVelY(-Control.playerSpeed);
						keyDown[0] = true;
					}
					if(key == KeyEvent.VK_DOWN) {
						tempObject.setVelY(Control.playerSpeed);
						keyDown[1] = true;
					}
					if(key == KeyEvent.VK_LEFT) {
						tempObject.setVelX(-Control.playerSpeed);
						keyDown[2] = true;
					}
					if(key == KeyEvent.VK_RIGHT) {
						tempObject.setVelX(Control.playerSpeed);
						keyDown[3] = true;
					}
					
					
				}			
			}
			
			if(key == KeyEvent.VK_ESCAPE) {
				if(Game.gameState == Game.STATE.Game) {
					if(Game.paused) {
						Game.paused = false;

					}
					else {
						Game.paused = true;

						whenPaused = System.currentTimeMillis();
					}
				}
			}
			
			if(key == KeyEvent.VK_SPACE) {
				if(Control.numberOfCarnageModeChips > 0) {
					Player.carnageModeActive = true;
					Control.numberOfCarnageModeChips--;
				}
				else {
					Control.showCarnageModeErrorMessage = true;
				}
			}
			
			
			
		}
		else if(Game.gameState == Game.STATE.Menu){
			if(key == KeyEvent.VK_DOWN) {
				Menu.where++;
				if(Menu.where >4) {
					Menu.where = 1;
				}
			}
			if(key == KeyEvent.VK_UP) {
				Menu.where--;
				if(Menu.where < 1) {
					Menu.where = 4;
				}
			}
			if(key == KeyEvent.VK_ENTER) {
				Menu.enterPressed = true;
			}
			if(key == KeyEvent.VK_ESCAPE) {
				Menu.escPressed = true;
			}
		}
		else if(Game.gameState == Game.STATE.Paused){
			
			if(key == KeyEvent.VK_DOWN) {
				pauseMenu.where++;
				if(pauseMenu.where >4) {
					pauseMenu.where = 1;
				}
			}
			if(key == KeyEvent.VK_UP) {
				pauseMenu.where--;
				if(pauseMenu.where < 1) {
					pauseMenu.where = 4;
				}
			}
			if(key == KeyEvent.VK_ENTER) {
				pauseMenu.enterPressed = true;
			}
			
			if(key == KeyEvent.VK_ESCAPE) {
				Game.paused = false;
			}
		}
		else if(Game.gameState == Game.STATE.Store){
			
			if(key == KeyEvent.VK_RIGHT) {
				Menu.where++;
				if(Menu.where >4) {
					Menu.where = 1;
				}
			}
			if(key == KeyEvent.VK_LEFT) {
				Menu.where--;
				if(Menu.where < 1) {
					Menu.where = 4;
				}
			}
			if(key == KeyEvent.VK_UP) {
				
				if(Menu.where ==2) {
					Menu.where = 5;
				}
				else if(Menu.where ==4) {
					Menu.where = 2;
				}
				else if(Menu.where ==5) {
					Menu.where = 4;
				}
			}
			if(key == KeyEvent.VK_DOWN) {
				
				if(Menu.where ==2) {
					Menu.where = 4;
				}
				else if(Menu.where ==4) {
					Menu.where = 5;
				}
				else if(Menu.where ==5) {
					Menu.where = 2;
				}
			}
			
			if(key == KeyEvent.VK_ENTER) {
				Menu.enterPressed = true;
			}
			
			
		}
		
		else if(Game.gameState == Game.STATE.PausedStore){
			
			if(key == KeyEvent.VK_RIGHT) {
				pauseMenu.where++;
				if(pauseMenu.where >4) {
					pauseMenu.where = 1;
				}
			}
			if(key == KeyEvent.VK_LEFT) {
				pauseMenu.where--;
				if(pauseMenu.where < 1) {
					pauseMenu.where = 4;
				}
			}
			if(key == KeyEvent.VK_UP) {
				
				if(pauseMenu.where ==2) {
					pauseMenu.where = 5;
				}
				else if(pauseMenu.where ==4) {
					pauseMenu.where = 2;
				}
				else if(pauseMenu.where ==5) {
					pauseMenu.where = 4;
				}
			}
			if(key == KeyEvent.VK_DOWN) {
				
				if(pauseMenu.where ==2) {
					pauseMenu.where = 4;
				}
				else if(pauseMenu.where ==4) {
					pauseMenu.where = 5;
				}
				else if(pauseMenu.where ==5) {
					pauseMenu.where = 2;
				}
			}
			
			if(key == KeyEvent.VK_ENTER) {
				pauseMenu.enterPressed = true;
			}
			
			
		}
		
		else if(Game.gameState == Game.STATE.GameOver){
			if(key == KeyEvent.VK_DOWN) {
				Menu.where++;
				if(Menu.where >4) {
					Menu.where = 3;
				}
			}
			if(key == KeyEvent.VK_UP) {
				Menu.where--;
				if(Menu.where < 3) {
					Menu.where = 4;
				}
			}
			if(key == KeyEvent.VK_ENTER) {
				Menu.enterPressed = true;
			}
			if(key == KeyEvent.VK_ESCAPE) {
				Menu.escPressed = true;
			}
		}
		
		else if(Game.gameState == Game.STATE.Help){
			
			if(key == KeyEvent.VK_ENTER) {
				Menu.enterPressed = true;
			}
		}
		
		else if(Game.gameState == Game.STATE.PausedHelp){
			
			if(key == KeyEvent.VK_ENTER) {
				pauseMenu.enterPressed = true;
			}
		}
		
		
	}
	
	
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(Game.gameState == Game.STATE.Game) {
		
			for(int i=0;i<handler.object.size();i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					
					if(key == KeyEvent.VK_UP) {
						//tempObject.setVelY(0);
						keyDown[0] = false;
					}
					if(key == KeyEvent.VK_DOWN) {
						//tempObject.setVelY(0);
						keyDown[1] = false;
					}
					if(key == KeyEvent.VK_LEFT) {
						//tempObject.setVelX(0);
						keyDown[2] = false;
					}
					if(key == KeyEvent.VK_RIGHT) {
						//tempObject.setVelX(0);
						keyDown[3] = false;
					}
					
					/*if(key == KeyEvent.VK_SPACE) {
						keyDown[4] = false;
					}*/
					
					
					if(!keyDown[0]&&!keyDown[1]) {
						tempObject.setVelY(0);
					}
					if(!keyDown[2]&&!keyDown[3]) {
						tempObject.setVelX(0);
					}
					/*if(!keyDown[4]) {
						
						tempObject.setBulletFired(false);
					}*/
					
					
				}
				
			}	
		}
		
		
	}
	
}
