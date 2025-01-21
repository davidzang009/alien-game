import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Bullets
{
    private List<Ammo> ammo;

    public Bullets()
    {
        ammo = new ArrayList<Ammo>();

        //initalize ammo
    }

    public void add(Ammo al)
    {
        ammo.add(al);
        //add al to list
    }

    public void drawEmAll( Graphics window )
    {
        for(Ammo a:ammo)
            a.draw(window);
        //draw each ammo in the list
    }

    public void moveEmAll()
    {
        for(Ammo a:ammo)
            a.move("UP");
        //move each ammon in the list
    }

    public void cleanEmUp()
    {
        for(int i = ammo.size()-1; i > -1; i--)
            if(ammo.get(i).getY() < 0-ammo.get(i).getHeight())
                ammo.remove(i);
        //for every ammo in the list
        //if the ammo is out of bounds
        //remove it
    }

    public List<Ammo> getList()
    {
        return ammo;
        //add code
    }

    public String toString()
    {
        return "" + ammo;
    }
}