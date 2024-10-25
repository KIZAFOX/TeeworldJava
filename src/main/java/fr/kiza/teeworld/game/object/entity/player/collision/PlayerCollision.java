package fr.kiza.teeworld.game.object.entity.player.collision;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.event.CollisionEvent;
import fr.kiza.teeworld.game.event.CollisionListener;
import fr.kiza.teeworld.game.object.GameObject;
import fr.kiza.teeworld.game.object.ObjectType;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class PlayerCollision {

    private static final int BOUND_MARGIN = 4;
    private static final int BOUND_VERTICAL_MARGIN = 8;

    protected final Game game;
    protected final GameObject entity;

    protected float x, y;
    protected int width, height;

    private final List<CollisionListener> LISTENERS = new ArrayList<>();
    private final Map<ObjectType, Consumer<GameObject>> HANDLERS = new EnumMap<>(ObjectType.class);

    public PlayerCollision(final Game game, final GameObject entity) {
        this.game = game;
        this.entity = entity;

        this.initializeCollisionHandlers();
    }

    private void initializeCollisionHandlers() {
        this.HANDLERS.put(ObjectType.BLOCK, this::handleDefault);
        this.HANDLERS.put(ObjectType.BLOCK_START, this::handleIntersection);
        this.HANDLERS.put(ObjectType.BLOCK_FINISH, this::handleIntersection);
        this.HANDLERS.put(ObjectType.BLOCK_KILL, this::handleIntersection);
    }

    public void updatePosition(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getBoundTop() {
        return new Rectangle((int) this.x + this.width / BOUND_MARGIN, (int) this.y, this.width / 2, this.height / 2);
    }

    public Rectangle getBoundBottom() {
        return new Rectangle((int) this.x + this.width / BOUND_MARGIN, (int) this.y + this.height / 2, this.width / 2, this.height / 2);
    }

    public Rectangle getBoundLeft() {
        return new Rectangle((int) this.x, (int) this.y + this.height / BOUND_VERTICAL_MARGIN, this.width / BOUND_MARGIN, this.height - this.height / BOUND_MARGIN);
    }

    public Rectangle getBoundRight() {
        return new Rectangle((int) this.x + this.width / 2 + this.width / BOUND_MARGIN, (int) this.y + this.height / BOUND_VERTICAL_MARGIN, this.width / BOUND_MARGIN, this.height - this.height / BOUND_MARGIN);
    }

    public void render(final Graphics2D graphics) {
        graphics.setColor(Color.RED);
        graphics.draw(this.getBoundTop());
        graphics.draw(this.getBoundBottom());
        graphics.draw(this.getBoundLeft());
        graphics.draw(this.getBoundRight());
    }

    public void checkCollisions() {
        game.getHandler().objects.stream()
                .filter(object -> this.HANDLERS.containsKey(object.getObjectType()))
                .forEach(object -> this.HANDLERS.get(object.getObjectType()).accept(object));
    }

    private void handleDefault(final GameObject block) {
        if (this.getBoundTop().intersects(block.getBounds())) {
            this.entity.setY(block.getY() + block.getHeight());
        }

        if (this.getBoundBottom().intersects(block.getBounds())) {
            this.entity.setY(block.getY() - this.entity.getHeight());
            this.entity.setVelocityY(0);
            this.entity.setFalling(false);
            this.entity.setJumping(false);
        } else {
            this.entity.setFalling(true);
        }

        if (this.getBoundLeft().intersects(block.getBounds())) {
            this.entity.setX(block.getX() + block.getWidth());
        } else if (getBoundRight().intersects(block.getBounds())) {
            this.entity.setX(block.getX() - this.entity.getWidth());
        }

        this.notifyCollisionListeners(block);
    }

    private void handleIntersection(final GameObject block) {
        if (this.getBoundTop().intersects(block.getBounds()) ||
                this.getBoundBottom().intersects(block.getBounds()) ||
                this.getBoundLeft().intersects(block.getBounds()) ||
                this.getBoundRight().intersects(block.getBounds())) {
            this.notifyCollisionListeners(block);
        }
    }

    private void notifyCollisionListeners(GameObject block) {
        final CollisionEvent event = new CollisionEvent(entity, block);
        this.LISTENERS.forEach(listener -> listener.onCollision(event));
    }

    public void addListener(final CollisionListener listener) {
        this.LISTENERS.add(listener);
    }
}