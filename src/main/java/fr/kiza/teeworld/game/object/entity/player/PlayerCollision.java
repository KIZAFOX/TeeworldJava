package fr.kiza.teeworld.game.object.entity.player;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.event.CollisionEvent;
import fr.kiza.teeworld.game.event.CollisionListener;
import fr.kiza.teeworld.game.object.GameObject;
import fr.kiza.teeworld.game.object.ObjectType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerCollision {

    protected final Game game;
    protected final GameObject entity;

    protected float x, y;
    protected int width, height;

    public final List<CollisionListener> COLLISION = new ArrayList<>();

    public PlayerCollision(Game game, GameObject entity) {
        this.game = game;
        this.entity = entity;
    }

    public void updatePosition(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getBoundTop() {
        return new Rectangle((int) x + width / 4, (int) y, width / 2, height / 2);
    }

    public Rectangle getBoundBottom() {
        return new Rectangle((int) x + width / 4, (int) y + height / 2, width / 2, height / 2);
    }

    public Rectangle getBoundLeft() {
        return new Rectangle((int) x, (int) y + height / 8, width / 4, height - height / 4);
    }

    public Rectangle getBoundRight() {
        return new Rectangle((int) x + width / 2 + width / 4, (int) y + height / 8, width / 4, height - height / 4);
    }

    public void render(final Graphics2D graphics){
        graphics.setColor(Color.RED);
        graphics.draw(this.getBoundTop());
        graphics.draw(this.getBoundBottom());
        graphics.draw(this.getBoundLeft());
        graphics.draw(this.getBoundRight());
    }

    public void checkCollisions(){
        this.game.getHandler().objects.stream()
                .filter(tempObject -> tempObject.getObjectType() == ObjectType.BLOCK)
                .forEach(this::handleDefault);

        this.game.getHandler().objects.stream()
                .filter(tempObject -> tempObject.getObjectType() == ObjectType.BLOCK_START)
                .forEach(this::handleStart);

        this.game.getHandler().objects.stream()
                .filter(tempObject -> tempObject.getObjectType() == ObjectType.BLOCK_FINISH)
                .forEach(this::handleFinish);

        this.game.getHandler().objects.stream()
                .filter(tempObject -> tempObject.getObjectType() == ObjectType.BLOCK_KILL)
                .forEach(this::handleKill);
    }

    private void handleDefault(final GameObject block) {
        if(this.getBoundTop().intersects(block.getBounds())){
            this.entity.setY(block.getY() + block.getHeight());
        }

        if(this.getBoundBottom().intersects(block.getBounds())){
            this.entity.setY(block.getY() - this.entity.getHeight());
            this.entity.setVelocityY(0);
            this.entity.setFalling(false);
            this.entity.setJumping(false);
        }else{
            this.entity.setFalling(true);
        }

        if(this.getBoundLeft().intersects(block.getBounds())){
            this.entity.setX(block.getX() + block.getWidth());
        }else if(this.getBoundRight().intersects(block.getBounds())){
            this.entity.setX(block.getX() - this.entity.getWidth());
        }

        this.COLLISION.forEach(listeners -> listeners.onCollision(new CollisionEvent(this.entity, block)));
    }

    private void handleStart(final GameObject block){
        if(this.getBoundTop().intersects(block.getBounds()) ||
                this.getBoundBottom().intersects(block.getBounds()) ||
                this.getBoundLeft().intersects(block.getBounds()) ||
                this.getBoundRight().intersects(block.getBounds())){
            this.COLLISION.forEach(listeners -> listeners.onCollision(new CollisionEvent(this.entity, block)));
        }
    }

    private void handleFinish(final GameObject block){
        if(this.getBoundTop().intersects(block.getBounds()) ||
                this.getBoundBottom().intersects(block.getBounds()) ||
                this.getBoundLeft().intersects(block.getBounds()) ||
                this.getBoundRight().intersects(block.getBounds())){
            this.COLLISION.forEach(listeners -> listeners.onCollision(new CollisionEvent(this.entity, block)));
        }
    }

    private void handleKill(final GameObject block){
        if(this.getBoundTop().intersects(block.getBounds()) ||
                this.getBoundBottom().intersects(block.getBounds()) ||
                this.getBoundLeft().intersects(block.getBounds()) ||
                this.getBoundRight().intersects(block.getBounds())){
            this.COLLISION.forEach(listeners -> listeners.onCollision(new CollisionEvent(this.entity, block)));
        }
    }

    public void addListener(final CollisionListener listener){
        this.COLLISION.add(listener);
    }
}