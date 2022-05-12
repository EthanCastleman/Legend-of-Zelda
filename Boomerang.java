/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: Boomerang.java
*/
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Boomerang extends Sprite 
{
    static BufferedImage image;
    private int direction = 1;
    private int speed = 15;

    Boomerang(int x, int y, int direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
        if(this.direction == 0)
        {
            this.direction = 1;
        }
        w = 20;
        h = 24;

        if(image == null)
        {
            image = View.loadImage("boomerang1.png");
        }
    }

    public boolean isBoomerang()
    {
        return true;
    }

    @Override
    public void drawYourself(Graphics g) 
    {
        g.drawImage(image, x - View.scroll_x, y - View.scroll_y, w, h, null);
    }

    @Override
    public boolean update() 
    {
        if(direction == 1)
        {
            y += speed;
        }
        if(direction == 2)
        {
            x -= speed;
        }
        if(direction == 3)
        {
            x += speed;
        }
        if(direction == 4)
        {
            y -= speed;
        }
        return true;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    @Override
    Json marshal() {
        Json ob = Json.newObject();
        ob.add("boomerangx", x);
        ob.add("boomerangy", y);
        return ob;
    }

    @Override
    public String toString()
    {
        return "Boomerang (x,y) =  (" + x + ", " + y + ")" + " w = " + w + " h = " + h;
    }
}
