package fr.kiza.teeworld.game.object.entity.player;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.ActionListener;
import fr.kiza.teeworld.game.object.entity.line.Line;
import fr.kiza.teeworld.game.object.entity.player.listeners.PlayerListeners;
import fr.kiza.teeworld.game.object.ObjectType;
import fr.kiza.teeworld.game.object.entity.Entity;
import fr.kiza.teeworld.mysql.dao.UserDAO;
import fr.kiza.teeworld.mysql.data.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class Player extends Entity implements ActionListener {

    protected final PlayerCollision playerCollision;
    protected final PlayerListeners playerListeners;

    private final UserDAO user;

    private Line currentLine;
    private boolean drawingLine = false;

    public Player(final Game game, final float x, final float y) {
        super(game, ObjectType.PLAYER, x, y);
        this.height = PIXEL;
        this.falling = true;

        this.playerCollision = new PlayerCollision(game, this);
        this.playerCollision.addListener(playerListeners = new PlayerListeners(game));

        this.user = game.getDatabaseManager().getUserDAO();
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLUE);
        graphics.fillRect((int) x, (int) y, width, height);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.PLAIN, 12));
        graphics.drawString(String.valueOf(this.user.getUser(this.user.getId(UserSession.getInstance().getUsername())).getName()), x - 5, y - 10);

        if(this.currentLine != null){
            this.currentLine.render(graphics);
        }

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

        //System.out.println(this.laser.toString());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON2) {
            Point playerCenter = this.getPlayerCenter();

            this.currentLine = new Line(this.game, playerCenter, 100);
            this.drawingLine = true;

            Point mousePosition = SwingUtilities.convertPoint(event.getComponent(), event.getPoint(), event.getComponent().getParent());

            this.currentLine.updateEnd(mousePosition, playerCenter);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON2){
            this.drawingLine = false;
            this.currentLine = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if(this.drawingLine && this.currentLine != null){
            this.currentLine.updateEnd(event.getPoint(), this.getPlayerCenter());
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
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

    @Override
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

    private Point getPlayerCenter() {
        return new Point((int) (this.x + (float) this.width / 2), (int) (this.y + (float) this.height / 2));
    }

    public PlayerCollision getPlayerCollision() {
        return playerCollision;
    }

    public PlayerListeners getPlayerListeners() {
        return playerListeners;
    }
}