//Texture class to give the specific frames of player and enemy entities
//Yes Needed
import java.awt.image.BufferedImage;
import java.util.Random;

public class Texture {
    public BufferedImage[] player = new BufferedImage[3];                       //3 frame so array length is 3
    public BufferedImage[] enemy = new BufferedImage[8];                        //8 frame so array length is 8

    private SpriteSheet spriteSheet, spriteSheetEnemy;
    Random random = new Random();

    public int randomEnemy = random.nextInt(2) + 1;                     //To choose between two different enemy sprites

    //Constructor
    public Texture(GameGui gameGui){
        spriteSheet = new SpriteSheet(gameGui.getSpriteSheet());               //Player sprite
        spriteSheetEnemy = new SpriteSheet(gameGui.getSpritesheetEnemy());     //Enemy sprite

        getTextures();
    }

    private void getTextures(){
        /*
        Array of buffered image being given 3 different frames with specific location of frames
        Player textures
         */
        player[0] = spriteSheet.grabImage(7,533,64,64);
        player[1] = spriteSheet.grabImage(72,533,64,64);
        player[2] = spriteSheet.grabImage(137,533,64,64);

        //Axe
        if (randomEnemy == 1) {
            /*
            Array of buffered image being given 8 different frames with specific location of frames
            Enemy(Axe) textures
             */
            enemy[0] = spriteSheetEnemy.grabImage(4, 1616, 46, 46);
            enemy[1] = spriteSheetEnemy.grabImage(52, 1616, 46, 46);
            enemy[2] = spriteSheetEnemy.grabImage(100, 1616, 46, 46);
            enemy[3] = spriteSheetEnemy.grabImage(148, 1616, 46, 46);
            enemy[4] = spriteSheetEnemy.grabImage(196, 1616, 46, 46);
            enemy[5] = spriteSheetEnemy.grabImage(244, 1616, 46, 46);
            enemy[6] = spriteSheetEnemy.grabImage(292, 1616, 46, 46);
            enemy[7] = spriteSheetEnemy.grabImage(340, 1616, 46, 46);
        }

        //Light stick
        else if (randomEnemy == 2){
            /*
            Array of buffered image being given 8 different frames with specific location of frames
            Enemy(Light stick) textures
             */
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
