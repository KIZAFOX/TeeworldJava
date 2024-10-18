package fr.kiza.teeworld.game.object.block.types;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.map.MapUtil;
import fr.kiza.teeworld.game.object.GameObject;
import fr.kiza.teeworld.game.object.ObjectType;

import java.awt.*;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class BlockStart extends GameObject {
    public BlockStart(Game game, float x, float y) {
        super(game, ObjectType.BLOCK_START, x, y);
        this.width = PIXEL;
        this.height = PIXEL;
    }

    @Override
    public void render(Graphics2D graphics) {
        MapUtil.render(graphics, MapUtil.START, (int) x, (int) y);
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
