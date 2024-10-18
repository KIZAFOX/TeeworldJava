package fr.kiza.teeworld.game.map;

import fr.kiza.teeworld.game.map.point.PointData;
import fr.kiza.teeworld.game.utils.image.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapUtil {
    public static PointData SPAWN_POINT;

    public static final BufferedImage
            MAP_1 = ImageRenderer.load(ImageRenderer.MAP_1),

            BRICK = ImageRenderer.load(ImageRenderer.BRICK),

            START = ImageRenderer.load(ImageRenderer.START),
            FINISH = ImageRenderer.load(ImageRenderer.FINISH),
            KILL = ImageRenderer.load(ImageRenderer.KILL);

    public static void render(final Graphics2D graphics, final BufferedImage image, final int x, final int y){
        final int
                scaledWidth = (int) (image.getWidth() * .5F),
                scaledHeight = (int) (image.getWidth() * .5F);

        graphics.drawImage(image, x, y, scaledWidth, scaledHeight, null);
    }
}
