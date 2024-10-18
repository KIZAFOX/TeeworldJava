package fr.kiza.teeworld.game.object;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.GamePanel;

import java.awt.*;

public abstract class GameObject {

    protected final Game game;
    protected final ObjectType objectType;

    protected float x, y, velocityX, velocityY;
    protected int width, height;

    protected boolean left = false, right = false, falling = false, jumping = false;

    public GameObject(final Game game, final ObjectType objectType, final float x, final float y) {
        this.game = game;
        this.objectType = objectType;

        this.x = x;
        this.y = y;
        this.width = GamePanel.PIXEL / 2;
        this.height = GamePanel.PIXEL / 2;
    }

    public abstract void render(final Graphics2D graphics);

    public abstract void update();

    public abstract Rectangle getBounds();

    public Game getGame() {
        return game;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
