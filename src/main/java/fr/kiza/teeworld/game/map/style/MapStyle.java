package fr.kiza.teeworld.game.map.style;

import fr.kiza.teeworld.game.utils.image.ImageRenderer;

import java.awt.image.BufferedImage;

public class MapStyle {

    public static boolean style = false;

    /**
     * Style: OFF
     *
     */
    private static final BufferedImage
            NO_STYLE_BRICK = ImageRenderer.cropImage(ImageRenderer.TILE_SET, 1, 0),
            NO_STYLE_START = ImageRenderer.cropImage(ImageRenderer.TILE_SET, 1, 2),
            NO_STYLE_FINISH = ImageRenderer.cropImage(ImageRenderer.TILE_SET, 2, 2),
            NO_STYLE_KILL = ImageRenderer.cropImage(ImageRenderer.TILE_SET, 2, 0);

    /**
     * Style: ON
     *
     */
    private static final BufferedImage STYLED_BRICK = ImageRenderer.cropImage(ImageRenderer.BRICKS_TILE_SET, 1, 0);

    public static BufferedImage getBrickImage(){
        return style ? STYLED_BRICK : NO_STYLE_BRICK;
    }
}
