import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class LivesList
{
    private List<Lives> lives;

    public LivesList(int num)
    {
        lives = new ArrayList<Lives>();
        for(int i = 0, xposition = 0; i < num; i++, xposition += 50)
            lives.add(new Lives(xposition, 0));
        /*
        System.out.println(aliens.size());
        for(int i = 0; i < size; i++)
            System.out.println(aliens.get(i).getX() + " " + aliens.get(i).getY()); */
        //initalize ArrayList
        //and fill with size amount of aliens (75 pixels apart)
        //if your row is full (out of bounds of screen)
        //move down a row (75 pixels)
        //starting point is 25, 50
        //first add aliens with speed of 0 to make sure you spacing is good

    }

    public void add(Lives al)
    {
        lives.add(al);
        //add an al to the list
    }

    public void drawEmAll( Graphics window )
    {
        for(Lives l:lives) {
            l.draw(window);
        }
        //make sure you draw all aliens in the list
    }

    public void removeLife(List<Alien> aliens, int xpos, int ypos, int w, int h) {
        for(int a = aliens.size()-1; a > -1; a--) {
            Alien al = aliens.get(a);
            if ((xpos >= al.getX() && xpos <= al.getX()+al.getWidth()) ||

                    (xpos+w >= al.getX() && xpos+w <= al.getX()+al.getWidth())) {

                if ((ypos >= al.getY() && ypos <= al.getY() + al.getHeight()) ||

                        (ypos + h >= al.getY() && ypos + h <= al.getY() + al.getHeight())) {

                    lives.remove(lives.size()-1);

                    break;


                }
            }
        }
    }

    public int getLives(){
        return lives.size();
    }

    public String toString()
    {
        return "" + lives;
    }
}