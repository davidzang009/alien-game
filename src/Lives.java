import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Lives extends MovingThing
{
    private int speed;
    private Image image;

    public Lives()
    {
        this(0,0,50,50,0);
    }

    public Lives(int x, int y)
    {
        //add code here
        this(x, y, 50, 50, 0);
    }

    public Lives(int x, int y, int s)
    {
        this(x, y, 50, 50, s);
    }

    public Lives(int x, int y, int w, int h, int s)
    {
        super(x, y, w, h);
        speed = s;
        try
        {
            //this sets ship.jpg as the image for your ship
            //for this to work ship.jpg needs to be in the same folder as this .java file
            URL url = getClass().getResource("hearts.png");
            image = ImageIO.read(url);
        }
        catch(Exception e)
        {
            //feel free to do something here or not
            System.out.println("something is wrong");
        }
    }


    public void setSpeed(int s)
    {
        //add more code
        speed = s;
    }

    public int getSpeed()
    {
        //continue coding
        return speed;
    }

    public void move(String direction)
    {
        //add code here
        //think about ALL your global variables and how you can use them to "move"
        //keep your parameter in mind as well
    }

    public void draw( Graphics window )
    {
        window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }

    public String toString()
    {
        return super.toString() + " " + getSpeed();
    }
}