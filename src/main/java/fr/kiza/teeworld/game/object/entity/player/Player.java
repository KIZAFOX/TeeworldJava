package fr.kiza.teeworld.game.object.entity.player;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.object.entity.player.listeners.PlayerListeners;
import fr.kiza.teeworld.game.object.ObjectType;
import fr.kiza.teeworld.game.object.entity.Entity;

import java.awt.*;
import java.awt.event.KeyEvent;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class Player extends Entity {

    protected final PlayerCollision playerCollision;
    protected final PlayerListeners playerListeners;

    public Player(final Game game, final float x, final float y) {
        super(game, ObjectType.PLAYER, x, y);
        this.height = PIXEL;
        this.falling = true;

        this.playerCollision = new PlayerCollision(game, this);
        this.playerCollision.addListener(playerListeners = new PlayerListeners(game));
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLUE);
        graphics.fillRect((int) x, (int) y, width, height);

        //this.playerCollision.render(graphics);
    }

    @Override
    public void update() {
        this.x += velocityX;
        this.y += velocityY;

        this.playerCollision.updatePosition(this.x, this.y, this.width, this.height);

        if(this.falling || this.jumping){
            this.velocityY = Math.min(this.velocityY + GRAVITY, MAX_SPEED);
        }else{
            this.velocityY = 0;
        }

        this.handleMovement();
        this.playerCollision.checkCollisions();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public void keyPressed(final KeyEvent event) {
        switch (event.getKeyCode()){
            case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> this.setLeft(true);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> this.setRight(true);
            case KeyEvent.VK_Z, KeyEvent.VK_UP -> {
                if(this.isJumping()) return;
                this.setJumping(true);
                this.setVelocityY(JUMP_SPEED);
            }
            default -> { }
        }
    }

    public void keyReleased(final KeyEvent event) {
        switch (event.getKeyCode()){
            case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> this.setLeft(false);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> this.setRight(false);
            default -> { }
        }
    }

    private void handleMovement(){
        if(this.isLeft() && !this.isRight()){
            this.velocityX = -MOVE_SPEED;
        }else if(this.isRight() && !this.isLeft()){
            this.velocityX = MOVE_SPEED;
        }else{
            this.velocityX = 0;
        }
    }

    public PlayerCollision getPlayerCollision() {
        return playerCollision;
    }

    public PlayerListeners getPlayerListeners() {
        return playerListeners;
    }
}