import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{


    // uncomment and comment as necessary as you add functionality to your project
    private AlienHorde horde;
    private Bullets shots;
    private Ship ship;
    //private Alien alienOne;
    //private Alien alienTwo;
    private LivesList healthLeft;
    private GameOverScreen endScreen;

    private PowerupOne powerup1;

    private boolean flickering;
    private boolean appears;
    private long flickeringStart;
    private long lastFlickerTime;

    private int currentHordeSize;
    private int currentHordeSpeed;
    private int speedCounter;


    private boolean[] keys;
    private BufferedImage back;


    private boolean gameOver;

    public OuterSpace()
    {
        ship = new Ship(310, 450, 5);
        //alienOne = new Alien(100, 50, 1);
        //alienTwo = new Alien(150, 50, 1);
        horde = new AlienHorde(20, 1);
        shots = new Bullets();
        healthLeft = new LivesList(3);
        endScreen = new GameOverScreen(200, 150);

        gameOver = false;
        flickering = false;
        appears = true;
        flickeringStart = 0;
        lastFlickerTime = 0;
        speedCounter = 0;

        currentHordeSize = 20;
        currentHordeSpeed = 1;

        setBackground(Color.black);

        keys = new boolean[5];

        //instantiate what you need as you need it (from global objects above)

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window)
    {
        paint(window);
    }

    //the top part of the paint method is done for you
    public void paint( Graphics window )
    {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D)window;

        //take a snap shot of the current screen and save it as an image
        //that is the exact same width and height as the current screen
        if(back==null)
            back = (BufferedImage)(createImage(getWidth(),getHeight()));

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();
        //this sets the background for the graphics window
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0,0,getWidth(),getHeight());


        //add code to move Ship, Alien, etc.-- Part 1
        if(gameOver == false) {
            if (keys[0] == true) {
                ship.move("LEFT");
            }
            if (keys[1] == true) {
                ship.move("RIGHT");
            }
            if (keys[2] == true) {
                ship.move("UP");
            }
            if (keys[3] == true) {
                ship.move("DOWN");
            }


            //add code to fire a bullet - Part 3
            if (keys[4] == true) {
                Ammo a = new Ammo(ship.getX() + ship.getWidth() / 2 - 5, ship.getY(), 5);
                shots.add(a);
                keys[4] = false;
            }

            //powerups
            int randNum = (int)(Math.random()*3000)+1;
            if((randNum == 11 || randNum == 22) && powerup1 == null) {
                powerup1 = new PowerupOne((int)(Math.random()*800)+1, 0, 30, 30, 1);

            }
            if(powerup1 != null) {
                powerup1.draw(graphToBack);
                powerup1.move("");
                if(powerup1.checkForOffScreen()) {
                    powerup1 = null;
                }
                else if(powerup1.checkCollisionWithShip(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight())) {
                    int prevLives = healthLeft.getLives();
                    healthLeft = new LivesList(prevLives+1);
                    powerup1 = null;
                }
            }


            //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship -- Part 3
            shots.cleanEmUp();
            horde.removeDeadOnes(shots.getList());

            if (flickering == false) {
                int prevLives = healthLeft.getLives();
                healthLeft.removeLife(horde.getList(), ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
                if(healthLeft.getLives() < prevLives) {
                    flickering = true;
                    flickeringStart = System.currentTimeMillis();
                    lastFlickerTime = System.currentTimeMillis();
                    appears = true;
                }
            }
            else {
                if(System.currentTimeMillis() - flickeringStart > 2000) {
                    flickering = false;
                }
                else {
                    if (System.currentTimeMillis() - lastFlickerTime > 200) {
                        appears = !appears;
                        lastFlickerTime = System.currentTimeMillis();
                    }
                }
            }



            //make sure you've drawn all your stuff
            if(flickering == false || appears == true)
                ship.draw(graphToBack);
            //alienOne.draw(graphToBack);
            //alienTwo.draw(graphToBack);
            horde.moveEmAll();
            horde.drawEmAll(graphToBack);
            shots.drawEmAll(graphToBack);

            shots.moveEmAll();
            shots.drawEmAll(graphToBack);
            healthLeft.drawEmAll(graphToBack);
            if(horde.areAliensDead()) {
                if(speedCounter % 5 == 0 && speedCounter != 0) {
                    currentHordeSpeed++;
                }
                horde = new AlienHorde(currentHordeSize + 2, currentHordeSpeed);
                currentHordeSize+=1;
                speedCounter++;

            }


            if (healthLeft.getLives() == 0 || horde.checkForBottom()) {
                gameOver = true;
            }
        }
        else {
            endScreen.draw(graphToBack);
            ship.draw(graphToBack);
            horde.drawEmAll(graphToBack);
            shots.drawEmAll(graphToBack);
        }

        twoDGraph.drawImage(back, null, 0, 0);
        back = null;
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            keys[4] = false;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e)
    {
        //no code needed here
        //method needs to be implemented
        //because class implements KeyListner
    }

    public void run()
    {
        try
        {
            while(true)
            {
                Thread.currentThread().sleep(5);
                repaint();
            }
        }catch(Exception e)
        {
            //feel free to add something here, or not
        }
    }
}

