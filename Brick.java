/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: Brick.java
*/
import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Brick extends Sprite
{
    static BufferedImage image;

    Brick(int x, int y)
    {
       this.x = x;
       this.y = y;
       w = 40;
       h = 40;
       if(image == null)
        {
            image = View.loadImage("brick.jpg");
        }
    }

    @Override
    public boolean isBrick()
    {
        return true;
    }

    public boolean update()
    {
        return true;
    }

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
        g.drawImage(image, x - View.scroll_x, y - View.scroll_y, null);
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("brickx", x);
        ob.add("bricky", y);
        
        return ob;
    }

    Brick(Json ob)
    {
        x = (int) ob.getLong("brickx");
        y = (int) ob.getLong("bricky");
        w = 40;
        h = 40;

        if(image == null)
        {
            image = View.loadImage("brick.jpg");
        }   
    }

    @Override
    public String toString()
    {
        return "Brick (x,y) =  (" + x + ", " + y + ")" + " w = " + w + " h = " + h;
    }
}
