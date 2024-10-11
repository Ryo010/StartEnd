import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mx >= GameGui.WIDTH / 2 + 120 && mx <= GameGui.WIDTH / 2 + 220){
            if (my >= 150 && my <= 200){
                GameGui.state = GameGui.STATE.GAME;
            }
        }
        if (mx >= GameGui.WIDTH / 2 + 120 && mx <= GameGui.WIDTH / 2 + 220){
            if (my >= 350 && my <= 400){
                System.exit(1);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
