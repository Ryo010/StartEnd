//Main Game class that runs mostly everything
//Yes Needed
import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class GameGui extends Canvas implements Runnable{

    //Game Window Specifications
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "StartEnd";
    public static final Color FRAME_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    public int second = 0;
    public boolean Enemy_spwaned = false;

    //Enemy Counter
    private int Enemy_Count = 20;

    //Player Health
    public static int Health = 1;

    //Game loop specifics
    private boolean running = false;
    private Thread thread;

    //All Spritesheets
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage spritesheetEnemy = null;
    private BufferedImage background = null;
    private BufferedImage healthbar = null;

    //Other classes
    private Player player;
    private Texture texture;
    private Controller controller;
    private Menu menu;
    private Timestamp timestamp;
    private HighScore highScore;

    //Enemy Entity
    public LinkedList<EntityB> eb;


    //All classes objects and SpriteSheets
    private void init(){
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage("Sprite/Amelia Watson.png");         //Player SpriteSheet
            spritesheetEnemy = loader.loadImage("Sprite/Enemies.png");          //Enemy SpriteSheet
            background = loader.loadImage("Sprite/Background.png");             //Background
            healthbar = loader.loadImage("Sprite/heart.png");                   //Player health

        }catch (IOException e){
            e.printStackTrace();
        }
        addKeyListener(new KeyboardInput(this));                             //Keyboard Inputs
        addMouseListener(new MouseInput());                                           //Mouse Inputs

        texture = new Texture(this);                                         //All spritesheets and images

        controller = new Controller(texture);                                         //Controls the player
        player = new Player(200,200, texture, this, controller);       //Player Spawn point and object
        menu = new Menu();                                                            //Main Menu
        timestamp = new Timestamp();

        eb = controller.getEntityB();                                                 //Enemy list
        highScore = new HighScore();
    }

    //Thread start for game loop
    private synchronized void start(){
        if(running){
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    //Stopping all threads and the entire game
    private synchronized void stop(){
        if (!running){
            return;
        }
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(1);
    }

    //Background music specifics
    private JLabel title, playButton, quitButton;
    Bgm bgm = new Bgm();

    //Flag for when to stop Menu bgm and switch to in game bgm and vica-versa
    private static boolean songplayed = false;

    //Menu and in game bgm playlists
    File PlaylistFile1 = new File("src/Assets/BackgroundMusic/GameBgm.txt");
    File PlaylistFile2 = new File("src/Assets/BackgroundMusic/MenuBgm.txt");

    //States to change from menu -> game and game -> menu
    public static enum STATE {
        MENU,
        GAME,
        OTHER
    };

    //Default state is MENU
    public static STATE state = STATE.MENU;

    //Constructor specifies the Gui features and starts the threads and game loop
    public GameGui() {

        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(TITLE);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        start();
    }

    //Game loop (Don't mind the math)
    public void run(){
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
                if (state == STATE.MENU){
                    if (Enemy_spwaned == true){
                        Enemy_spwaned = false;
                        controller.destroyEnemy();
                        //highScore.changeHighScore(second);
                    }
                    second = 0;
                } else {
                    second++;
                    if (Enemy_spwaned == false){
                        Enemy_spwaned = true;
                        controller.createEnemy(Enemy_Count);                                          //Enemy Spawns
                    }
                }
            }
        }
        stop();
    }

    //Ticks for all entities in game
    public void tick(){
        if (state == STATE.GAME) {
            player.tick();
            controller.tick();
        }
    }

    //Renders all the spritesheets and main menu
    public void render(){

        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null){
            createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        if (state == STATE.GAME) {
            graphics.drawImage(background, -10, -950,null);
            player.render(graphics);
            controller.render(graphics);
            timestamp.render(graphics, second);

            graphics.drawImage(healthbar, 5, 5, null);
            if (songplayed == true){
                bgm.stopSong();
                bgm.loadPlaylist(PlaylistFile1);
                songplayed = false;
            }
        } else if (state == STATE.MENU){
            menu.render(graphics);

            if (songplayed == false){
                bgm.stopSong();
                bgm.loadPlaylist(PlaylistFile2);
                songplayed = true;

            }
        }
        graphics.dispose();
        bufferStrategy.show();
    }

    //Makes sure to listen to keyboard inputs to move player
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (state == STATE.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                player.setVelX(5);
            } else if (key == KeyEvent.VK_LEFT) {
                player.setVelX(-5);
            } else if (key == KeyEvent.VK_DOWN) {
                player.setVelY(5);
            } else if (key == KeyEvent.VK_UP) {
                player.setVelY(-5);
            }
        } /*else if (state == STATE.OTHER) {
            if (key == KeyEvent.VK_RIGHT) {
                player.setVelX(5);
            } else if (key == KeyEvent.VK_D) {

            } else if (key == KeyEvent.VK_LEFT) {
                player.setVelX(-5);
            } else if (key == KeyEvent.VK_A) {

            } else if (key == KeyEvent.VK_DOWN) {
                player.setVelY(5);
            } else if (key == KeyEvent.VK_S) {

            }  else if (key == KeyEvent.VK_UP) {
                player.setVelY(-5);
            } else if (key == KeyEvent.VK_W) {

            }
        }*/
    }

    //Listens to when the keyboard keys are released
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (state == STATE.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                player.setVelX(0);
            } else if (key == KeyEvent.VK_LEFT) {
                player.setVelX(0);
            } else if (key == KeyEvent.VK_DOWN) {
                player.setVelY(0);
            } else if (key == KeyEvent.VK_UP) {
                player.setVelY(0);
            }
        }/*else if (state == STATE.OTHER) {
            if (key == KeyEvent.VK_RIGHT) {
                player.setVelX(0);
            } else if (key == KeyEvent.VK_D) {

            } else if (key == KeyEvent.VK_LEFT) {
                player.setVelX(0);
            } else if (key == KeyEvent.VK_A) {

            } else if (key == KeyEvent.VK_DOWN) {
                player.setVelY(0);
            } else if (key == KeyEvent.VK_S) {

            }  else if (key == KeyEvent.VK_UP) {
                player.setVelY(0);
            } else if (key == KeyEvent.VK_W) {

            }
        }*/
    }

    //To get spritesheet of player
    public BufferedImage getSpriteSheet() { return spriteSheet; }

    //To get spritesheet of enemy
    public BufferedImage getSpritesheetEnemy() { return spritesheetEnemy; }

    //Total enemy count
    public int getEnemy_Count(){ return Enemy_Count; }

    //To change enemies in game
    public void setEnemy_Count(int enemy_Count){ this.Enemy_Count = enemy_Count; }
}