//Keyboard input class
//Maybe if you wanna use the keyboard that is
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
    GameGui gameGui;

    public KeyboardInput(GameGui gameGui){
        this.gameGui = gameGui;
    }

    public void keyPressed(KeyEvent e){                     //Keys pressed method
        gameGui.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){                    //Keys released method
        gameGui.keyReleased(e);
    }
}
