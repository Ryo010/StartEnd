import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(GameGui.WIDTH / 2 + 120, 150, 100, 50);
    public Rectangle OtherButton = new Rectangle(GameGui.WIDTH / 2 + 120, 250, 100, 50);
    public Rectangle QuitButton = new Rectangle(GameGui.WIDTH / 2 + 120, 350, 100, 50);

    public void render(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        graphics.setFont(fnt0);
        graphics.setColor(Color.WHITE);
        graphics.drawString("StartEnd", GameGui.WIDTH / 2 + 50, 125);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        graphics.setFont(fnt1);
        graphics.drawString("Play", playButton.x + 19, playButton.y + 35);
        graphics2D.draw(playButton);
        graphics.drawString("Other", OtherButton.x + 12, OtherButton.y + 35);
        graphics2D.draw(OtherButton);
        graphics.drawString("Quit", QuitButton.x + 19, QuitButton.y + 35);
        graphics2D.draw(QuitButton);
    }
}