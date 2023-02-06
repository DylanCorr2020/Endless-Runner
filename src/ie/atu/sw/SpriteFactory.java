package ie.atu.sw;

import java.awt.image.BufferedImage;
/**
 * This class creates different Sprite objects 
 * @author dylancorrGMIT
 *
 */
public class SpriteFactory {
	/**
	 * This method returns a sprite object depending on the sprite name passed
	 * @param name - The name of the sprite
	 * @param position - The position of the sprite
	 * @return Sprite
	 * @throws Exception
	 */
    public static Sprite createSprite(String name, Point position) throws Exception {
        switch (name) {
            case "Player":
                return new Sprite(name, position, Assets.loadImages("./resources/images/sprites/default", null));
            case "Spider":
                BufferedImage[] spiderImages = Assets.loadImages("./resources/images/sprites/spiders", null);
                spiderImages = SpriteHelper.changeColour(spiderImages);
                return new Sprite(name, position, spiderImages);
            default:
                throw new IllegalArgumentException("Invalid sprite name: " + name);
        }
    }
}
