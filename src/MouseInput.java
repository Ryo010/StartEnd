//Mouse Input class
//Yes Needed
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        //No action when mouse clicked
    }

    //Mouse pressed action for main menu operations
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //Basically the position of play button and when clicked changes to GAME state to start game
        if (mx >= GameGui.WIDTH / 2 + 120 && mx <= GameGui.WIDTH / 2 + 220){
            if (my >= 150 && my <= 200){
                GameGui.state = GameGui.STATE.GAME;
            }
        }
        //Basically the position of other button and when clicked changes to Other state to start other game
        if (mx >= GameGui.WIDTH / 2 + 120 && mx <= GameGui.WIDTH / 2 + 220){
            if (my >= 250 && my <= 300){
                GameGui.state = GameGui.STATE.OTHER;
            }
        }
        //Basically the position of quit button and when clicked exits the program with code 1
        if (mx >= GameGui.WIDTH / 2 + 120 && mx <= GameGui.WIDTH / 2 + 220){
            if (my >= 350 && my <= 400){
                System.exit(1);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //No action when mouse released
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //No action when mouse entered
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //No action when mouse exited
    }
}
