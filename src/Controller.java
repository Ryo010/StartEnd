//Class used to control entities
//Maybe
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
    private LinkedList<EntityB> e = new LinkedList<EntityB>();                                  //Enemy entity

    Random r = new Random();

    EntityB ent;                                                //Enemy Entity
    Texture texture;

    public Controller (Texture texture){
        this.texture = texture;
    }

    //Adds enemy entity to the game
    public void createEnemy(int enemy_Count){
        for (int i = 0; i < enemy_Count; i++){
            addEntity(new Enemy((GameGui.WIDTH * GameGui.SCALE), 0, texture));
        }
    }

    //Enemy ticks
    public void tick(){
        for (int i = 0; i < e.size(); i++ ){
            ent = e.get(i);

            ent.tick();
        }
    }

    //Rendering enemies
    public void render(Graphics g){
        for (int i = 0; i < e.size(); i++ ){
            ent = e.get(i);

            ent.render(g);
        }
    }

    //Adding a new enemy entity
    public void addEntity(EntityB block){
        e.add(block);
    }

    //Removing an enemy entity
    public void removeEntity(EntityB block){
        e.remove(block);
    }

    public LinkedList<EntityB> getEntityB(){
        return e;
    }
}
