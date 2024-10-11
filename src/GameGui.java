import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class GameGui extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "StartEnd";

    private int Enemy_Count = 20;

    public static int Health = 1;

    public static final Color FRAME_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage spritesheetEnemy = null;
    private BufferedImage background = null;
    private BufferedImage healthbar = null;

    private Player player;
    private Texture texture;
    private Controller controller;
    private Menu menu;

    public LinkedList<EntityB> eb;

    private void init(){
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage("Sprite/Amelia Watson.png");
            spritesheetEnemy = loader.loadImage("Sprite/Enemies(Weapons).png");
            background = loader.loadImage("Sprite/minimalist.jpg");
            healthbar = loader.loadImage("Sprite/heart.png");

        }catch (IOException e){
            e.printStackTrace();
        }
        addKeyListener(new KeyboardInput(this));
        addMouseListener(new MouseInput());

        texture = new Texture(this);

        controller = new Controller(texture);
        player = new Player(200,200, texture, this, controller);
        menu = new Menu();

        eb = controller.getEntityB();

        controller.createEnemy(Enemy_Count);
    }

    private synchronized void start(){
        if(running){
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

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

    private JLabel title, playButton, quitButton;
    Bgm bgm = new Bgm();

    private static boolean songplayed = false;

    File PlaylistFile1 = new File("src/Assets/BackgroundMusic/GameBgm.txt");
    File PlaylistFile2 = new File("src/Assets/BackgroundMusic/MenuBgm.txt");

    public static enum STATE {
        MENU,
        GAME
    };

    public static STATE state = STATE.MENU;

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
            }
        }
        stop();
    }

    public void tick(){
        if (state == STATE.GAME) {
            player.tick();
            controller.tick();
        }
    }

    public void render(){

        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null){
            createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        graphics.drawImage(background, 0, 0, null);

        if (state == STATE.GAME) {
            player.render(graphics);
            controller.render(graphics);

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
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT){
            player.setVelX(0);
        }else if (key == KeyEvent.VK_LEFT){
            player.setVelX(0);
        }else if (key == KeyEvent.VK_DOWN){
            player.setVelY(0);
        }else if (key == KeyEvent.VK_UP){
            player.setVelY(0);
        }
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public BufferedImage getSpritesheetEnemy() {
        return spritesheetEnemy;
    }

    public int getEnemy_Count(){
        return Enemy_Count;
    }

    public void setEnemy_Count(int enemy_Count){
        this.Enemy_Count = enemy_Count;
    }
}