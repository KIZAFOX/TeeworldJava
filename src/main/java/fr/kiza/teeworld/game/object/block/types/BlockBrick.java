package fr.kiza.teeworld.game.object.block.types;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.map.MapUtil;
import fr.kiza.teeworld.game.map.style.MapStyle;
import fr.kiza.teeworld.game.object.*;
import fr.kiza.teeworld.game.object.block.handler.Block;

import java.awt.*;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class BlockBrick extends Block {
    public BlockBrick(Game game, float x, float y) {
        super(game, ObjectType.BLOCK, x, y);
        this.width = PIXEL;
        this.height = PIXEL;

        this.setImage(MapUtil.BRICK);
    }

    @Override
    public void render(Graphics2D graphics) {
        MapUtil.render(graphics, this.getImage(), (int) x, (int) y);
    }

    @Override
    public void update() {
        this.setImage(MapStyle.getBrickImage());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
