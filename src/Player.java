//Player main class
//Yes Needed
import java.awt.*;

public class Player extends GameObject implements EntityA{

    //Player coordinates
    private double velX = 0;
    private double velY = 0;

    //All Different class objects
    private Texture texture;
    GameGui gameGui;
    Controller controller;
    Animation animation;
    Animation deathAnimation;

    public Player(double x, double y, Texture texture, GameGui gameGui, Controller controller){
        super(x, y);
        this.texture = texture;
        this.gameGui = gameGui;
        this.controller = controller;

        //3 frame animation with animation speed 8
        animation = new Animation(8, texture.player[0], texture.player[1], texture.player[2]);
        deathAnimation = new  Animation(2, texture.death[0], texture.death[1], texture.death[2], texture.death[3], texture.death[4]);
    }

    //Player main tick for how much and how far the movement goes
    public void tick(){
        x+=velX;
        y+=velY;

        if (x <= 0) {
            x = 0;
        }else if (x >= 640 - 80){
            x = 640 - 80;
        }else if (y <= 0){
            y = 0;
        }else if (y >= 520 - 100){
            y = 480 - 100;
        }

        //Keeps check on each enemy that is in game
        for (int i = 0; i<gameGui.eb.size(); i++){
            EntityB entityB = gameGui.eb.get(i);

            //Checks for collision between player and enemy to end game
            if (Physics.Collision(this, entityB)){
//                for (int j = 0; j<gameGui.eb.size(); j++) {
//                    EntityB entityE = gameGui.eb.get(j);
//                    controller.removeEntity(entityE);
//                }
                controller.removeEntity(entityB);
                deathAnimation.runAnimation();
                GameGui.Health -= 1;
                GameGui.state = GameGui.STATE.MENU;
            }
        }

        //Animation per tick
        animation.runAnimation();
    }

    //Rendering animation graphics for player
    public void render(Graphics graphics){
        animation.drawAnimation(graphics, x, y, 0);
        deathAnimation.drawAnimation(graphics, x - 95, y - 80, 0);
    }

    //To get player hit box
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 17, 17);
    }

    //To get player position on x-axis
    public double getX(){
        return x;
    }

    //To get player position on y-axis
    public double getY(){
        return y;
    }

    //Setting player x-axis
    public void setX(double x){
        this.x = x;
    }

    //Setting player y-axis
    public void setY(double y){
        this.y = y;
    }

    public void setVelX(double velX){
        this.velX = velX;
    }

    public void setVelY(double velY){
        this.velY = velY;
    }
}
