import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing
{
    private int speed;

    public Ammo()
    {
        this(10,10,10,10,0);
    }

    public Ammo(int x, int y)
    {
        //add code
        this(x, y, 10, 10, 0);
    }

    public Ammo(int x, int y, int s)
    {
        //add code
        this(x, y, 10, 10, s);
    }

    public Ammo(int x, int y, int w, int h, int s)
    {
        //add code here
        super(x, y, w, h);
        speed = s;
    }

    public void setSpeed(int s)
    {
        //add code
        speed = s;
    }

    public int getSpeed()
    {
        //add gode
        return speed;
    }

    public void draw( Graphics window )
    {
        window.setColor(Color.yellow);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
        //add code to draw the ammo
        //ammo will be a yellow square
        //use window.setColor(COLOR) to set the color
        //if you don't set a color, your ammo will be black and you will not see it
        //use window.fillRect(x,y,w,h); to make a rectangle/square
    }


    public void move( String direction )
    {
        setY(getY() - speed);
        //add code to move the ammo
        //ship ammo should only move up
    }

    public String toString()
    {
        return super.toString() + getSpeed();
    }
}