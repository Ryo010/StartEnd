import java.awt.image.BufferedImage;
import java.util.Random;

public class Texture {
    public BufferedImage[] player = new BufferedImage[3];
    public BufferedImage[] enemy = new BufferedImage[8];

    private SpriteSheet spriteSheet, spriteSheetEnemy;
    Random random = new Random();

    public int randomEnemy = random.nextInt(2) + 1;

    public Texture(GameGui gameGui){
        spriteSheet = new SpriteSheet(gameGui.getSpriteSheet());
        spriteSheetEnemy = new SpriteSheet(gameGui.getSpritesheetEnemy());

        getTextures();
    }

    private void getTextures(){
        player[0] = spriteSheet.grabImage(7,533,64,64);
        player[1] = spriteSheet.grabImage(72,533,64,64);
        player[2] = spriteSheet.grabImage(137,533,64,64);

        if (randomEnemy == 1) {
            enemy[0] = spriteSheetEnemy.grabImage(4, 1616, 46, 46);
            enemy[1] = spriteSheetEnemy.grabImage(52, 1616, 46, 46);
            enemy[2] = spriteSheetEnemy.grabImage(100, 1616, 46, 46);
            enemy[3] = spriteSheetEnemy.grabImage(148, 1616, 46, 46);
            enemy[4] = spriteSheetEnemy.grabImage(196, 1616, 46, 46);
            enemy[5] = spriteSheetEnemy.grabImage(244, 1616, 46, 46);
            enemy[6] = spriteSheetEnemy.grabImage(292, 1616, 46, 46);
            enemy[7] = spriteSheetEnemy.grabImage(340, 1616, 46, 46);
        }
        else if (randomEnemy == 2){
            enemy[0] = spriteSheetEnemy.grabImage(4, 2494, 64, 64);
            enemy[1] = spriteSheetEnemy.grabImage(70, 2494, 64, 64);
            enemy[2] = spriteSheetEnemy.grabImage(136, 2494, 64, 64);
            enemy[3] = spriteSheetEnemy.grabImage(202, 2494, 64, 64);
            enemy[4] = spriteSheetEnemy.grabImage(268, 2494, 64, 64);
            enemy[5] = spriteSheetEnemy.grabImage(334, 2494, 64, 64);
            enemy[6] = spriteSheetEnemy.grabImage(400, 2494, 64, 64);
            enemy[7] = spriteSheetEnemy.grabImage(466, 2494, 64, 64);
        }
    }
}
