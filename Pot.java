/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: Pot.java
*/
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Pot extends Sprite
{
    //two buffered images used. Image stores the unbroken pot image. While imageBrokenPot stores the broken pot image
    static BufferedImage image, imageBrokenPot;
    
    //used to determin if pot is broken or not
    //set to true upon collision
    boolean potBroken = false;

    //if collision with link occurs then collision right, left, up or down is used to check how link collided with the pot
    boolean linkCollision = false;
    boolean collisionRight = false;
    boolean collisionLeft = false;
    boolean collisionUp = false;
    boolean collisionDown = false;

    int speed = 15;

    //used to determine how long the broken pot should stay on screen
    int countDown = 15;
    
    Pot(int x, int y)
    {
        this.x = x;
        this.y = y;
        w = 48;
        h = 48;
        if(image == null)
        {
            image = View.loadImage("pot.png");
        }
        if(imageBrokenPot == null)
            imageBrokenPot = View.loadImage("pot_broken.png");
    }

    @Override
    public boolean isPot()
    {
        return true;
    }

    //works the same as bricks amIClickingOnYou method
    public boolean amIClickingOnYou(int user_x, int user_y)
    {
        if(user_x >= x && user_x < x + w && user_y >= y && user_y < y + h)
        {
            return true;
        }
        
        return false;
    }
    
    public void drawYourself(Graphics g) 
    {
        if(potBroken == false)
            g.drawImage(image, x - View.scroll_x, y - View.scroll_y, null);
        else
            g.drawImage(imageBrokenPot, x - View.scroll_x, y - View.scroll_y, null);
    }

    
    public boolean update() 
    {
        if(linkCollision == true)
        {
            if(collisionRight)
            {
                x += speed;
            }
            if(collisionLeft)
            {
                x -= speed;
            }
            if(collisionUp)
            {
                y += speed;
            }
            if(collisionDown)
            {
                y -= speed;
            }
        }
        if(potBroken == true)
        {
            speed = 0;
        }

        if(potBroken == true && countDown > 0)
        {
            countDown--;
            return true;
        }

        if(potBroken == true && countDown <= 0)
        {
            return false;
        }

        return true;
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("potx", x);
        ob.add("poty", y);
        
        return ob;
    }

    Pot(Json ob)
    {
        x = (int) ob.getLong("potx");
        y = (int) ob.getLong("poty");
        w = 48;
        h = 48;

        if(image == null)
        {
            image = View.loadImage("pot.png");
        }  
        if(imageBrokenPot == null)
            imageBrokenPot = View.loadImage("pot_broken.png");
        
    }

    @Override
    public String toString()
    {
        return "Pot (x,y) =  (" + x + ", " + y + ")" + " w = " + w + " h = " + h;
    }
}
