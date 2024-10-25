package fr.kiza.teeworld.game.object.block.handler;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.object.GameObject;
import fr.kiza.teeworld.game.object.ObjectType;

import java.awt.image.BufferedImage;

public abstract class Block extends GameObject {

    protected BufferedImage image;

    public Block(Game game, ObjectType objectType, float x, float y) {
        super(game, objectType, x, y);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
