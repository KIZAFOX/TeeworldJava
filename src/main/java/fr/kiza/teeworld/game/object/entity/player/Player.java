package fr.kiza.teeworld.game.object.entity.player;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.ActionListener;
import fr.kiza.teeworld.game.object.entity.line.Line;
import fr.kiza.teeworld.game.object.entity.player.collision.PlayerCollision;
import fr.kiza.teeworld.game.object.entity.player.listener.PlayerListeners;
import fr.kiza.teeworld.game.object.ObjectType;
import fr.kiza.teeworld.game.object.entity.Entity;
import fr.kiza.teeworld.mysql.dao.UserDAO;
import fr.kiza.teeworld.mysql.data.UserSession;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class Player extends Entity implements ActionListener {

    private static final Color PLAYER_COLOR = Color.BLUE;
    private static final Color NAME_COLOR = Color.WHITE;
    private static final Font NAME_FONT = new Font("Arial", Font.PLAIN, 12);

    private final PlayerCollision playerCollision;
    private final PlayerListeners playerListeners;
    private final UserDAO userDAO;

    private Line currentLine;
    private boolean isDrawingLine;

    public Player(final Game game, final float x, final float y) {
        super(game, ObjectType.PLAYER, x, y);
        this.height = PIXEL;
        this.falling = true;

        this.playerCollision = new PlayerCollision(game, this);
        this.playerListeners = new PlayerListeners(game);
        this.playerCollision.addListener(playerListeners);

        this.userDAO = game.getDatabaseManager().getUserDAO();
    }

    @Override
    public void render(Graphics2D graphics) {
        this.configureGraphics(graphics);
        this.drawPlayer(graphics);
        this.drawPlayerName(graphics);
        this.drawCurrentLine(graphics);
    }

    @Override
    public void update() {
        this.updatePosition();
        this.updateVelocity();
        this.handleMovement();
        this.handleCollision();
        this.updateCurrentLine();
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
            startDrawingLine(event);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON2) {
            stopDrawingLine();
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
        updateLinePosition(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        updateLinePosition(event);
    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(final KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> setLeft(true);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> setRight(true);
            case KeyEvent.VK_Z, KeyEvent.VK_UP -> handleJump();
        }
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> setLeft(false);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> setRight(false);
        }
    }

    /**
     * Render
     *
     */

    private void configureGraphics(Graphics2D graphics) {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void drawPlayer(Graphics2D graphics) {
        graphics.setColor(PLAYER_COLOR);
        graphics.fillRect((int) x, (int) y, width, height);
    }

    private void drawPlayerName(Graphics2D graphics) {
        graphics.setColor(NAME_COLOR);
        graphics.setFont(NAME_FONT);
        String playerName = userDAO.getUser(userDAO.getId(UserSession.getInstance().getUsername())).getName();
        graphics.drawString(playerName, x - 5, y - 10);
    }

    private void drawCurrentLine(Graphics2D graphics) {
        if (currentLine != null) {
            currentLine.render(graphics);
        }
    }

    /**
     * Update
     *
     */

    private void updatePosition() {
        x += velocityX;
        y += velocityY;
        playerCollision.updatePosition(x, y, width, height);
    }

    private void updateVelocity() {
        if (falling || jumping) {
            velocityY = Math.min(velocityY + GRAVITY, MAX_SPEED);
        } else {
            velocityY = 0;
        }
    }

    private void handleMovement() {
        if (isLeft() && !isRight()) {
            velocityX = -MOVE_SPEED;
        } else if (isRight() && !isLeft()) {
            velocityX = MOVE_SPEED;
        } else {
            velocityX = 0;
        }
    }

    private void handleCollision() {
        this.playerCollision.checkCollisions();
    }

    private void updateCurrentLine() {
        if (currentLine != null) {
            currentLine.setStart(getPlayerCenter());
        }
    }

    private void handleJump() {
        if (!isJumping()) {
            setJumping(true);
            setVelocityY(JUMP_SPEED);
        }
    }

    private void startDrawingLine(MouseEvent event) {
        currentLine = new Line(game, getPlayerCenter(), 100);
        isDrawingLine = true;
        updateLinePosition(event);
    }

    private void stopDrawingLine() {
        isDrawingLine = false;
        currentLine = null;
    }

    private void updateLinePosition(MouseEvent event) {
        if (isDrawingLine && currentLine != null) {
            Point mousePosition = event.getPoint();
            Point playerCenter = getPlayerCenter();

            currentLine.setStart(playerCenter);
            currentLine.updateDirection(mousePosition);
        }
    }

    private Point getPlayerCenter() {
        return new Point((int) (x + (float) width / 2), (int) (y + (float) height / 2));
    }

    public PlayerCollision getPlayerCollision() {
        return playerCollision;
    }

    public PlayerListeners getPlayerListeners() {
        return playerListeners;
    }
}