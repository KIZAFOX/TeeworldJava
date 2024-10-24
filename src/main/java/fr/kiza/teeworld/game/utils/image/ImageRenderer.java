package fr.kiza.teeworld.game.utils.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class ImageRenderer {

    public static final String
            MAP_1 = "map.png",

            TILE_SET = "assets/tileset.png";

    public static BufferedImage load(final String fileName){
        final InputStream inputStream = ImageRenderer.class.getResourceAsStream("/" + fileName);
        final BufferedImage image;

        if(inputStream == null){
            throw new IllegalStateException("File " + fileName + " not found or doesn't exist in 'resources' folder!");
        }

        try {
            image = ImageIO.read(inputStream);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }

    public static BufferedImage cropImage(final String fileName, final int x, final int y){
        return load(fileName).getSubimage(x * (PIXEL * 2), y * (PIXEL * 2), PIXEL * 2, PIXEL * 2);
    }
}
