//Enemy entity interface
//One is enough so not needed
import java.awt.*;

public interface EntityB {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double GetX();
    public double GetY();
}
