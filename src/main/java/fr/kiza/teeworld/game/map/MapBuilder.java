package fr.kiza.teeworld.game.map;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.map.point.PointData;
import fr.kiza.teeworld.game.object.GameObject;
import fr.kiza.teeworld.game.object.block.handler.Block;
import fr.kiza.teeworld.game.object.block.types.BlockBrick;
import fr.kiza.teeworld.game.object.block.types.BlockFinish;
import fr.kiza.teeworld.game.object.block.types.BlockKill;
import fr.kiza.teeworld.game.object.block.types.BlockStart;
import fr.kiza.teeworld.game.object.entity.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public record MapBuilder(Game game) {

    public void create(final BufferedImage image) {
        final int
                width = image.getWidth(),
                height = image.getHeight();

        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                final int pixel = image.getRGB(xx, yy),
                        red = (pixel >> 16) & 0xff,
                        green = (pixel >> 8) & 0xff,
                        blue = (pixel) & 0xff;

                if (this.check(new Color(255, 255, 255), green, green, blue)) {
                    this.game.getHandler().add(new BlockBrick(
                            this.game,
                            xx * PIXEL,
                            yy * PIXEL
                    ));
                }

                if (this.check(new Color(0, 55, 255), red, green, blue)) {
                    this.game.getHandler().add(new Player(this.game, xx * PIXEL, yy * PIXEL));
                    MapUtil.SPAWN_POINT = new PointData(xx * PIXEL, yy * PIXEL);
                }

                if (this.check(new Color(0, 255, 63), red, green, blue)) {
                    this.game.getHandler().add(new BlockStart(
                            this.game,
                            xx * PIXEL,
                            yy * PIXEL
                    ));
                }

                if (this.check(new Color(255, 0, 255), red, green, blue)) {
                    this.game.getHandler().add(new BlockFinish(
                            this.game,
                            xx * PIXEL,
                            yy * PIXEL
                    ));
                }

                if (this.check(new Color(255, 0, 12), red, green, blue)) {
                    this.game.getHandler().add(new BlockKill(
                            this.game,
                            xx * PIXEL,
                            yy * PIXEL
                    ));
                }
            }
        }
    }

    public void updateMapStyle(){
        this.game.getHandler().objects.stream()
                .filter(gameObject -> gameObject instanceof Block)
                .map(gameObject -> (Block) gameObject)
                .forEach(GameObject::update);
    }

    private boolean check(final Color targetColor, final int red, final int green, final int blue) {
        return targetColor.getRed() == red && targetColor.getGreen() == green && targetColor.getBlue() == blue;
    }
}
