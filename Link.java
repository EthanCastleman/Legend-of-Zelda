/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: Link.java
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Link extends Sprite
{
    //links x and y coordinates

    private BufferedImage[] link_images;

    //variables used to keep track of which images need to be used
    private final int maxImages = 10;
    private int imageNum = 1;
    private int bucketNum;
    private int direction = 0;
    private boolean animate = false;
   
    //links previous x and y coordinates
    int px;
    int py;

    int speed;
    private BufferedImage image;

    Link(int x, int y) 
    {
        this.x = x;
        this.y = y;
        w = 50;
        h = 70;
        speed = 10;

        //default link image
        if(image == null)
        {
            image = View.loadImage("link_starting1.png");
        }

        link_images = new BufferedImage[42];
        //since link_images[0] is not used I initialize it to make sure that
        //if it is somehow accessed it doesn't throw a null pointer
        //this image is a random image that is not being used in the animation
		link_images[0] = View.loadImage("link_starting3.png");

        //stores images into array
        for(int i = 1; i < link_images.length; i++)
        {
            link_images[i] = View.loadImage("link" + i + ".png");
        }
    }
    

    public boolean update()
    {
        return true;
    }

    @Override
    public boolean isLink()
    {
        return true;
    }

    public boolean getAnimate()
    {
        return animate;
    }

    public void setAnimate(boolean animate)
    {
        this.animate = animate;
    }

    //tracks the current image number
    public void imageNumTracker()
    {
        if(animate == true)
        {
            if(imageNum >= maxImages)
            {
                imageNum = 1;
            }
            imageNum++;
        }
    }

    public void drawYourself(Graphics g)
    {
        
        if(direction == 1)
        {
            bucketNum = 0;
            image = link_images[imageNum + bucketNum*maxImages];
            //boomerangLinkDirection = 1;
           
        }
        
       
        if(direction == 2)
        {
            bucketNum = 1;
            image = link_images[imageNum + bucketNum*maxImages];
            //boomerangLinkDirection = 2;
            
        }
        
        if(direction == 3)
        {
            bucketNum = 2;
            image = link_images[imageNum + bucketNum*maxImages];
            //boomerangLinkDirection = 3;
            
        }
      
        if(direction == 4)
        {
            bucketNum = 3;
            image = link_images[imageNum + bucketNum*maxImages];
            //boomerangLinkDirection = 4;
           
        }
        imageNumTracker();
        
        g.drawImage(image, x - View.scroll_x, y - View.scroll_y, null);
    }

    void getOutOfBrick(Sprite b)
    {
        if(x + w >= b.x && px + w <= b.x)
        {
            x = px;
        }
        if(x <= b.x + b.w && px >= b.x + b.w)
        {
            x = px;
        }
        if(y <= b.y + b.h && py >= b.y + b.h)
        {
            y = py;
        }
        if(y + h >= b.y && py + h <= b.y)
        {
            y = py;
        }
    }

    public void savePrevLocation()
    {
        px = x;
        py = y;
    }

    public Link(Json ob)
    {
        x = (int)ob.getLong("linkx");
        y = (int)ob.getLong("linky");
        w = 50;
        h = 70;
        if(image == null)
        {
           image = View.loadImage("sprites-link-forward-resting_01.png");
        }

        link_images = new BufferedImage[42];
        //since link_images[0] is not used I initialize it to make sure that
        //if it is somehow accessed it doesn't throw a null pointer
		link_images[0] = View.loadImage("link_starting1.png");

        //stores images into array
        for(int i = 1; i < link_images.length; i++)
        {
            link_images[i] = View.loadImage("link" + i + ".png");
        }
    }

    int getDirection()
    {
        return direction;
    }

    void setDirection(int x)
    {
        direction = x;
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("linkx", x);
        ob.add("linky", y);
        return ob;
    }

    @Override
    public String toString()
    {
        return "Link (x,y) =  (" + x + ", " + y + ")" + " w = " + w + " h = " + h;
    }
    
}
