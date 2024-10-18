package fr.kiza.teeworld.game.utils.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageRenderer {

    public static final String
            MAP_1 = "map.png",

            BRICK = "assets/brick.png",

            START = "assets/start.png",
            FINISH = "assets/finish.png",
            KILL = "assets/kill.png";

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
}
