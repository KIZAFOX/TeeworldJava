package fr.kiza.teeworld.game.object.block;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.map.MapUtil;
import fr.kiza.teeworld.game.object.*;

import java.awt.*;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class Block extends GameObject {
    public Block(Game game, float x, float y) {
        super(game, ObjectType.BLOCK, x, y);
        this.width = PIXEL;
        this.height = PIXEL;
    }

    @Override
    public void render(Graphics2D graphics) {
        MapUtil.render(graphics, MapUtil.BRICK, (int) x, (int) y);
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
