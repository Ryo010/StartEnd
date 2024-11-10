//Physics class for the collision check between player and enemy entities
//Noah
import java.util.LinkedList;

public class Physics {

    //Checks for collision between player and enemy only
    public static boolean Collision(EntityA enta, EntityB entb){
        if(enta.getBounds().intersects(entb.getBounds())){
            return true;
        }
        return false;
    }

}
