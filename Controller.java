/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: Controller.java
*/
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	//exit keys that when pressed close the game window
	boolean keyQ;
	boolean keyEsc;
	//editing key and boolean for edit toggle
	//boolean keyEdit;
	//boolean keyPot;
	boolean control = false;
	boolean pot = false;
	//controls link with arrow keys
	boolean keyUpArrow;
	boolean keyDownArrow;
	boolean keyRightArrow;
	boolean keyLeftArrow;
	boolean keyControl;

	View view;
	Model model;

	Controller(Model m)
	{
		model = m;
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	void setView(View v)
	{
		view = v;
	}

	public void mousePressed(MouseEvent e)
	{
		if(control == true && pot == false)
		{
			model.addBrick(e.getX() + View.scroll_x, e.getY() + View.scroll_y);
		}
		if(control == true && pot == true)
		{
			model.addPot(e.getX() + View.scroll_x, e.getY() + View.scroll_y);
		}
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_Q: keyQ = true; break;
			case KeyEvent.VK_ESCAPE: keyEsc = true; break;
			case KeyEvent.VK_UP: keyUpArrow = true; break;
			case KeyEvent.VK_DOWN: keyDownArrow = true; break;
			case KeyEvent.VK_LEFT: keyLeftArrow = true; break;
			case KeyEvent.VK_RIGHT: keyRightArrow = true; break;
			case KeyEvent.VK_CONTROL: keyControl = true; break;
		}
		char c = e.getKeyChar();
		//saves the current map state
		if(c == 's')
		{
			Json saveObject = model.marshal();
			saveObject.save("map.json");
			System.out.println("File is saved");
		}

		//loads file with l is pressed
		if(c == 'l')
		{
			loadFile();
		}

		//toggles edit mode when e is pressed (edit mode is off by default)
		if(c == 'e')
		{
			control = !control;

			if(control == true)
			{
				System.out.println("Editing mode enabled.");
				System.out.println("Click to place bricks.");
			}
			else
				System.out.println("Editing mode disabled.");
			
		}
		if(control == true)
		{
			if(c == 'p')
				{
					pot = !pot;
					if(pot == true)
						System.out.println("click to place pots.");
					else
						System.out.println("Click to place bricks.");
				}
		}

	}

	public void keyReleased(KeyEvent e)
	{
		//when control is pressed calls the throwBoomerang method and creates a new boomerang
		if(keyControl == true)
		{
			model.throwBoomerang();
		}
		
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_Q: keyQ = false; break;
			case KeyEvent.VK_ESCAPE: keyEsc = false; break;
			case KeyEvent.VK_UP: keyUpArrow = false; break;
			case KeyEvent.VK_DOWN: keyDownArrow = false; break;
			case KeyEvent.VK_LEFT: keyLeftArrow = false; break;
			case KeyEvent.VK_RIGHT: keyRightArrow = false; break;
			case KeyEvent.VK_CONTROL: keyControl = false; break;
		}
		
		
	}

	public void keyTyped(KeyEvent e)
	{
	}

	//determines which direction link is facing based upon an integer value
	void linkDirection()
	{
		model.link.setAnimate(true);
		if(keyDownArrow)
		{
			//model.link.direction = 1;
			model.link.setDirection(1);
		}
		else if(keyLeftArrow)
		{
			model.link.setDirection(2);
		}
		else if(keyRightArrow)
		{
			model.link.setDirection(3);
		}
		else if(keyUpArrow)
		{
			model.link.setDirection(4);
		}
		else
			model.link.setAnimate(false);
	}

	void update()
	{
		model.link.savePrevLocation();
		if(keyRightArrow) model.link.x+=model.link.speed;
		if(keyLeftArrow) model.link.x-=model.link.speed;
		if(keyDownArrow) model.link.y+=model.link.speed;
		if(keyUpArrow) model.link.y-=model.link.speed;
		view.moveScreen();
		linkDirection();

		//if q or esc are pressed then game window is closed
		if(keyQ || keyEsc)
		{
			System.exit(0);
		}
		
	}

	public void loadFile()
	{
		Json j = Json.load("map.json");
		model.unmarshal(j);
		System.out.println("File loaded");
	}
}
