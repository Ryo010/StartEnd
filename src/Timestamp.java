import java.awt.*;

public class Timestamp {

    public void render(Graphics graphics, int seconds){
        Graphics2D graphics2D = (Graphics2D) graphics;

        //Graphics for Game Timer
        Font fnt0 = new Font("arial", Font.BOLD, 16);
        graphics.setFont(fnt0);
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.valueOf(seconds), GameGui.WIDTH / 2 + 400, 40);
    }
}
