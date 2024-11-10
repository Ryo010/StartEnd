//Main Menu class
//Yes Needed
import java.awt.*;

public class Menu {

    public void render(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        //Graphics for Game Title
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        graphics.setFont(fnt0);
        graphics.setColor(Color.WHITE);
        graphics.drawString("StartEnd", GameGui.WIDTH / 2 + 50, 125);

        //Graphics for Menu options
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        graphics.setFont(fnt1);
        graphics.drawString("Play", (GameGui.WIDTH / 2 + 120) + 19, 150 + 35);              //play button positioning
        graphics.drawString("Other", (GameGui.WIDTH / 2 + 120) + 12, 250 + 35);             //other button positioning
        graphics.drawString("Quit", (GameGui.WIDTH / 2 + 120) + 19, 350 + 35);              //quit button positioning
    }
}