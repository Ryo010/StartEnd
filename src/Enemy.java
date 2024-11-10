//Main class for enemies specifics
//Not really
import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {

    Random r = new Random();

    private Texture texture;

    private int speed = r.nextInt(3) + 1;                           //Randomising speed of each enemy so that there is no specific pattern of attack

    Animation animation;

    public Enemy(double x, double y, Texture texture){
        super(x, y);
        this.texture = texture;

        //8 frame animations of enemies with animation speed 2
        animation = new Animation(2, texture.enemy[0], texture.enemy[1], texture.enemy[2],texture.enemy[3], texture.enemy[4], texture.enemy[5],texture.enemy[6], texture.enemy[7]);

    }

    //Main enemy tick which controls movement and spawn
    public void tick(){
        y += speed;

        if (y > (GameGui.HEIGHT * GameGui.SCALE)){
            speed = r.nextInt(3) + 1;
            y = -10;
            x = r.nextInt(GameGui.WIDTH * GameGui.SCALE);
        }

        animation.runAnimation();           //Runs the animation per tick

    }

    //Rendering the enemy animations
    public void render(Graphics graphics){
        animation.drawAnimation(graphics, x, y, 0);
    }

    //Enemy hit box
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 24, 24);
    }

    //Current position of enemy on x-axis
    public double GetX() {
        return x;
    }

    //Current position of enemy on y-axis
    public double GetY() {
        return y;
    }
}
