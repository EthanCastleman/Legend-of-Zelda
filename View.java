/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: View.java
*/
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
//import java.util.Iterator;

class View extends JPanel
{
	
	Model model;
	static int scroll_x;
	static int scroll_y;

	//room positions 
	int leftRoom = 0;
	int rightRoom = 800;
	int topRoom = 0;
	int bottomRoom = 800;

	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
	}

	public void moveScreen()
	{
		//if links x position is greater than or equal to the rightrooms position
		//then move the view to the right
		if(model.link.x >= rightRoom)
		{
			scroll_x = rightRoom;
		}
		//if links x position is less than or equal to the rightrooms position
		//then move the view to the left
		if(model.link.x <= rightRoom)
		{
			 scroll_x = leftRoom;
		}
		//if links y position is greater than or equal to the bottomrooms position
		//then move the view down
		if(model.link.y >= bottomRoom)
		{
			scroll_y = bottomRoom;
		}
		//if links y position is less than or equal to the bottomrooms position
		//then move the view up
		if(model.link.y <= bottomRoom)
		{
			scroll_y = topRoom;
		}
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Iterator worked in last assignment. However, it throws a modification exeption now and I haven't been able to figure out why
		// for(Iterator<Sprite> bIterator = model.sprites.iterator(); bIterator.hasNext();)
		// {
		// 	bIterator.next().drawYourself(g);
		// }

		for(int i = 0; i < model.sprites.size(); i++)
		{
			model.sprites.get(i).drawYourself(g);
		}
		
	}

	static BufferedImage loadImage(String name)
	{
		BufferedImage image = null;
		if(image == null)
		{
			try {
				image = ImageIO.read(new File(name));
				System.out.println(name + " was loaded.");
			} catch (Exception e) {
				System.out.println("Error loading image!");
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
		return image;
	}
}
