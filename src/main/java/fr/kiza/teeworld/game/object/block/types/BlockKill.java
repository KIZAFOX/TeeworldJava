package fr.kiza.teeworld.game.object.block.types;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.map.MapUtil;
import fr.kiza.teeworld.game.object.GameObject;
import fr.kiza.teeworld.game.object.ObjectType;

import java.awt.*;

import static fr.kiza.teeworld.game.client.window.GamePanel.PIXEL;

public class BlockKill extends GameObject {
    public BlockKill(Game game, float x, float y) {
        super(game, ObjectType.BLOCK_KILL, x, y);
        this.width = PIXEL;
        this.height = PIXEL;
    }

    @Override
    public void render(Graphics2D graphics) {
        MapUtil.render(graphics, MapUtil.KILL, (int) x, (int) y);
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
