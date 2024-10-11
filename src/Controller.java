import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
    private LinkedList<EntityB> e = new LinkedList<EntityB>();

    Random r = new Random();

    EntityB ent;
    Texture texture;

    public Controller (Texture texture){
        this.texture = texture;
    }

    public void createEnemy(int enemy_Count){
        for (int i = 0; i < enemy_Count; i++){
            addEntity(new Enemy((GameGui.WIDTH * GameGui.SCALE), 0, texture));
        }
    }

    public void tick(){
        for (int i = 0; i < e.size(); i++ ){
            ent = e.get(i);

            ent.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < e.size(); i++ ){
            ent = e.get(i);

            ent.render(g);
        }
    }

    public void addEntity(EntityB block){
        e.add(block);
    }

    public void removeEntity(EntityB block){
        e.remove(block);
    }

    public LinkedList<EntityB> getEntityB(){
        return e;
    }
}
