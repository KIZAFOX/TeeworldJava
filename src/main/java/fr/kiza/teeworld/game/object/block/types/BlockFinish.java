package fr.kiza.teeworld.game.object.block.types;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.map.MapUtil;
import fr.kiza.teeworld.game.object.ObjectType;
import fr.kiza.teeworld.game.object.block.handler.Block;

import java.awt.*;

import static fr.kiza.teeworld.game.client.window.GamePanel.PIXEL;

public class BlockFinish extends Block {
    public BlockFinish(Game game, float x, float y) {
        super(game, ObjectType.BLOCK_FINISH, x, y);
        this.width = PIXEL;
        this.height = PIXEL;
    }

    @Override
    public void render(Graphics2D graphics) {
        MapUtil.render(graphics, MapUtil.FINISH, (int) x, (int) y);
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
