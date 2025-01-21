import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class PowerupOne extends MovingThing
{
    private int speed;
    private Image image;

    public PowerupOne()
    {
        this(0,0,20,20,0);
    }

    public PowerupOne(int x, int y)
    {
        //add code here
        this(x, y, 20, 20, 0);
    }

    public PowerupOne(int x, int y, int s)
    {
        //add code here
        this(x, y, 20, 20, s);
    }

    public PowerupOne(int x, int y, int w, int h, int s)
    {
        //add code here
        super(x, y, w, h);
        speed = s;
        try
        {

            URL url = getClass().getResource("powerup1.png");
            image = ImageIO.read(url);
        }
        catch(Exception e)
        {
            //feel free to do something here
            System.out.println("oops something went wrong");
        }
    }

    public void setSpeed(int s)
    {
        //add code
        speed = s;
    }

    public int getSpeed()
    {
        //add code
        return speed;
    }

    public void move(String direction)
    {
        setY(getY()+speed);
    }

    public boolean checkCollisionWithShip(int x, int y, int w, int h) {
        if ((x >= getX() && x <= getX()+getWidth()) ||

                (x+w >= getX() && x+w <= getX()+getWidth())) {

            if ((y >= getY() && y <= getY() + getHeight()) ||

                    (y + h >= getY() && y + h <= getY() + getHeight())) {

                return true;
            }
        }
        return false;
    }

    public boolean checkForOffScreen() {
        if(getY() > 600) {
            return true;
        }
        return false;
    }

    /* The draw method is done for  you.
    This method will move the alien and update it's location on screen by
    constantly redrawing it.
    */
    public void draw( Graphics window )
    {
        //move("DOWN");
        window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }

    public String toString()
    {
        return super.toString() + getSpeed();
    }
}