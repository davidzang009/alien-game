import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
    private List<Alien> aliens;

    public AlienHorde(int size, int speed)
    {
        aliens = new ArrayList<Alien>();
        int x = 25;
        int y = 50;
        for(int i = 0; i < size; i++) {
            aliens.add(new Alien(x, y, speed));
            if(x < 800 - aliens.get(i).getWidth()) {
                x += 75;
            }
            else {
                x = 25;
                y += 75;
                aliens.set(i, new Alien(x, y, speed));
                x += 75;
            }
        }
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

    public void add(Alien al)
    {
        aliens.add(al);
        //add an al to the list
    }

    public void drawEmAll( Graphics window )
    {
        for(Alien a:aliens) {
            a.draw(window);
        }
        //make sure you draw all aliens in the list
    }

    public void moveEmAll()
    {
        for(Alien a:aliens) {
            a.move("");
        }
        //make sure you move all aliens in the list
    }

    public void removeDeadOnes(List<Ammo> shots) {
        for (int s = shots.size() - 1; s > -1; s--) {
            for (int a = aliens.size() - 1; a > -1; a--) {
                Ammo am = shots.get(s);
                Alien al = aliens.get(a);
                if ((am.getX() >= al.getX() && am.getX() <= al.getX() + al.getWidth()) ||

                        (am.getX() + am.getWidth() >= al.getX() && am.getX() + am.getWidth() <= al.getX() + al.getWidth())) {

                    if ((am.getY() >= al.getY() && am.getY() <= al.getY() + al.getHeight()) ||

                            (am.getY() + am.getHeight() >= al.getY() && am.getY() + am.getHeight() <= al.getY() + al.getHeight())) {

                        aliens.remove(a);

                        shots.remove(s);

                        break;


                    }
                }
            }
        }

    }
    public List<Alien> getList()
    {
        return aliens;
        //add code
    }

    public boolean areAliensDead() {
        if(aliens.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkForBottom() {
        for(Alien a:aliens) {
            if(a.getY() > 600) {
                return true;
            }
        }
        return false;
    }

        //Part 3
        //for every shot in the list
        //check if you've hit any alien in the list
        //(do the coordinates of the shot fall between the boundarises of the alien)
        //if they do then remove the alien and the shot
        //make sure you break out of the loop if this happens


    public String toString()
    {
        return "" + aliens;
    }
}