/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: Sprite.java
*/
import java.awt.Graphics;
abstract class Sprite 
{
    int x, y, w, h;
  
    public Sprite()
    {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }

    abstract public void drawYourself(Graphics g);
    abstract public boolean update();
    abstract Json marshal();
    
    public boolean isLink()
    {
        return false;
    }
    public boolean isBrick()
    {
        return false;
    }
    public boolean isPot()
    {
        return false;
    }
    public boolean isBoomerang()
    {
        return false;
    }
}
