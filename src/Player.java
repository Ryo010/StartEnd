import java.awt.*;

public class Player extends GameObject implements EntityA{

    private double velX = 0;
    private double velY = 0;

    private Texture texture;
    GameGui gameGui;
    Controller controller;

    Animation animation;

    public Player(double x, double y, Texture texture, GameGui gameGui, Controller controller){
        super(x, y);
        this.texture = texture;
        this.gameGui = gameGui;
        this.controller = controller;

        animation = new Animation(8, texture.player[0], texture.player[1], texture.player[2]);
    }

    public void tick(){
        x+=velX;
        y+=velY;

        if (x <= 0) {
            x = 0;
        }else if (x >= 640 - 18){
            x = 640 - 18;
        }else if (y <= 0){
            y = 0;
        }else if (y >= 480 - 32){
            y = 480 - 32;
        }

        for (int i = 0; i<gameGui.eb.size(); i++){
            EntityB entityB = gameGui.eb.get(i);

            if (Physics.Collision(this, entityB)){
                controller.removeEntity(entityB);
                GameGui.Health -= 1;
                GameGui.state = GameGui.STATE.MENU;
            }
        }

        animation.runAnimation();
    }

    public void render(Graphics graphics){
        animation.drawAnimation(graphics, x, y, 0);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 24, 24);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
        this.x = x;
    }
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
