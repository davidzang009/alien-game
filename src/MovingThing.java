import java.awt.Color;
import java.awt.Graphics;

public abstract class MovingThing implements Moveable
{
    //add instance variables (look at your constructors)
    private int xpos;
    private int ypos;
    private int width;
    private int height;

    public MovingThing()
    {
        this(10,10,10,10);
    }

    public MovingThing(int x, int y)
    {
        //add code here
        this(x, y, 10, 10);
    }

    public MovingThing(int x, int y, int w, int h)
    {
        //add code here
        xpos = x;
        ypos = y;
        width = w;
        height = h;
    }

    //add necessary implementations (look at interface)
    public void setPos( int x, int y) {
        xpos = x;
        ypos = y;
    }
    public void setX( int x ) {
        xpos = x;
    }
    public void setY( int y ) {
        ypos = y;
    }

    public int getX() {
        return xpos;
    }
    public int getY() {
        return ypos;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void setWidth( int w ) {
        width = w;
    }
    public void setHeight( int h ) {
        height = h;
    }


    //do not change code below this line
    public abstract void move(String direction);
    public abstract void draw(Graphics window);

    public String toString()
    {
        return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
    }
}