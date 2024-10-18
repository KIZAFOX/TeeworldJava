package fr.kiza.teeworld.game.object.entity;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.object.*;

public abstract class Entity extends GameObject {

    protected final float
            GRAVITY = 0.1F,
            MAX_SPEED = 10F,
            MOVE_SPEED = 2.0f,
            JUMP_SPEED = -4.0f;

    public Entity(Game game, ObjectType objectType, float x, float y) {
        super(game, objectType, x, y);
    }

    public void setLocation(final int x, final int y){
        this.setX(x);
        this.setY(y);
    }

    public float getGravity() {
        return GRAVITY;
    }

    public float getMaxSpeed() {
        return MAX_SPEED;
    }
}
