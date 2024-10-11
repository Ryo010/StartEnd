import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
    GameGui gameGui;

    public KeyboardInput(GameGui gameGui){
        this.gameGui = gameGui;
    }

    public void keyPressed(KeyEvent e){
        gameGui.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        gameGui.keyReleased(e);
    }
}
