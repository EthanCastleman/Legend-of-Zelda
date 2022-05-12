/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: Model.java
*/
import java.util.ArrayList;

class Model
{
	//instance variables
	//Brick brick;
	int brick_x;
	int brick_y;
	int pot_x;
	int pot_y;
	//ArrayList<Brick> bricks;
	ArrayList<Sprite> sprites;
	//stores the x and y coordinates of the mouse click
	int click_x;
	int click_y;
	//instance of link class
	Link link;

	Model()
	{
		link = new Link(50, 60);
		sprites = new ArrayList<Sprite>();
		sprites.add(link);
	}

	public void update()
	{
		for(int i = 0; i < sprites.size(); i++)
		{
			if(!sprites.get(i).update())
			{
				sprites.remove(i);
				break;
			}
			
			if(!sprites.get(i).isLink())
			{
				if(checkCollision(link, sprites.get(i)))
				{
					link.getOutOfBrick(sprites.get(i));
					if(sprites.get(i).isPot())
					{
						((Pot)sprites.get(i)).linkCollision = true;
						if(link.x < ((Pot)sprites.get(i)).x + ((Pot)sprites.get(i)).w)
						{
							((Pot)sprites.get(i)).collisionRight = true;
						}
						if(link.x + link.w > ((Pot)sprites.get(i)).x)
						{
							((Pot)sprites.get(i)).collisionLeft = true;
						}
						if(link.y > ((Pot)sprites.get(i)).y + ((Pot)sprites.get(i)).h)
						{
							((Pot)sprites.get(i)).collisionDown = true;
						}
						if(link.y + link.h < ((Pot)sprites.get(i)).y)
						{
							((Pot)sprites.get(i)).collisionUp = true;
						}

					}
				}

				//used to test if a pot collides with another object
				for(int j = 1; j < sprites.size(); j++)
				{
					if(sprites.get(j).isPot() && !sprites.get(i).isPot())
					{
						if(checkCollision(sprites.get(j), sprites.get(i)))
						{
							((Pot)sprites.get(j)).potBroken = true;
						}
					}
				}

				//used to test if a boomerang collides with another object
				for(int j = 1; j < sprites.size(); j++)
				{
					if(sprites.get(j).isBoomerang() && !sprites.get(i).isBoomerang())
					{
						if(checkCollision(sprites.get(j), sprites.get(i)))
						{
							sprites.remove(j);
							break;
						}
					}
				}
			}
		}
	}

	//creates a new boomerang
	public void throwBoomerang()
	{
		sprites.add(new Boomerang(link.x + link.w / 2, link.y + link.h / 2, link.getDirection()));
	}

	//adds a brick wherever the user clicks
	//also removes a brick if the user clicks on a brick
	public void addBrick(int x, int y)
	{	
		click_x = x;
		click_y = y;
		brick_x = click_x - click_x % 50;
		brick_y = click_y - click_y % 50;
		pot_x = click_x - click_x % 50;
		pot_y = click_y - click_y % 50;
		
		boolean found = false;
		
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isBrick())
			{
				if(((Brick)sprites.get(i)).amIClickingOnYou(brick_x, brick_y))
				{
					//remove brick
					found = true;
					if(found == true)
					{
						sprites.remove(i);
					}
					break;
				}
			}
			
		}
		if(!found)
		{
			sprites.add(new Brick(brick_x, brick_y));
		}
	}

	//adds a pot wherever a user clicks
	//also removes a pot if the user clicks on a pot
	public void addPot(int x, int y)
	{	
		click_x = x;
		click_y = y;
		pot_x = click_x - click_x % 50;
		pot_y = click_y - click_y % 50;
		
		boolean found = false;
		
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isPot())
			{
				if(((Pot)sprites.get(i)).amIClickingOnYou(pot_x, pot_y))
				{
				
					//remove pot
					found = true;
					if(found == true)
					{
						sprites.remove(i);
					}
					break;
				}
			}
		}

		if(!found)
		{
			sprites.add(new Pot(pot_x, pot_y));
		}
	}

	boolean checkCollision(Sprite link, Sprite brick)
	{
		if(link.y + link.h < brick.y)
		{
			return false;
		}

		if(link.y > brick.y + brick.h)
		{
			return false;
		}

		if(link.x + link.w < brick.x)
		{
			return false;
		}

		if(link.x > brick.x + brick.w)
		{
			return false;
		}

		return true;
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		Json tmpListBrick = Json.newList();
		ob.add("bricks", tmpListBrick);
		
		Json tmpListPot = Json.newList();
		ob.add("pot", tmpListPot);
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isBrick())
			{
				tmpListBrick.add(sprites.get(i).marshal());
			}
		
			if(sprites.get(i).isPot())
			{
				tmpListPot.add(sprites.get(i).marshal());
			}
		}


		return ob;
	}

	void unmarshal(Json ob)
	{
		sprites = new ArrayList<Sprite>();
		sprites.add(link);
		Json tmpListBrick = ob.get("bricks");
		Json tmpListPot = ob.get("pot");
		
		for(int i = 0; i < tmpListBrick.size(); i++)
		{
			sprites.add(new Brick(tmpListBrick.get(i)));
		}
		for(int i = 0; i < tmpListPot.size(); i++)
		{
			sprites.add(new Pot(tmpListPot.get(i)));
		}
	}
}