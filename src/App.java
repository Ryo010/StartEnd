//Class which runs it all
//Yes Needed
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGui().setVisible(true);
            }
        });
    }
}