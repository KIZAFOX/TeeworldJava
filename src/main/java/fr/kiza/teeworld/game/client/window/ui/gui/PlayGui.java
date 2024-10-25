package fr.kiza.teeworld.game.client.window.ui.gui;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.ActionListener;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.GuiHandler;
import fr.kiza.teeworld.game.object.ObjectType;
import fr.kiza.teeworld.game.object.entity.player.Player;
import fr.kiza.teeworld.game.utils.formatter.TimeFixer;
import fr.kiza.teeworld.game.scheduler.GameScheduler;
import fr.kiza.teeworld.mysql.dao.UserDAO;
import fr.kiza.teeworld.mysql.data.UserSession;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static fr.kiza.teeworld.game.map.style.MapStyle.*;

public class PlayGui extends GuiHandler implements ActionListener {

    private final UserDAO userDAO;

    public PlayGui(final Game game) {
        super(game);
        this.userDAO = game.getDatabaseManager().getUserDAO();
    }

    @Override
    public void render(Graphics2D graphics) {
        this.game.getTextRenderer().renderMidCentered(graphics, TimeFixer.formatTime(GameScheduler.getTimer()));
        this.game.getTextRenderer().renderLeftBottom(graphics, UserSession.getInstance().getUsername() + " ended the map in " + TimeFixer.formatTime(this.userDAO.getTimer(this.userDAO.getId(UserSession.getInstance().getUsername()))));

        graphics.translate(this.game.getCamera().getX(), this.game.getCamera().getY());
        this.game.getHandler().render(graphics);
        graphics.translate(-this.game.getCamera().getX(), -this.game.getCamera().getY());
    }

    @Override
    public void update() {
        this.game.getHandler().update();

        this.game.getHandler().objects.stream()
                .filter(tempObject -> tempObject.getObjectType() == ObjectType.PLAYER)
                .forEach(object -> this.game.getCamera().update(object));
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        this.handleMouseEvent(event);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.handleMouseEvent(event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        this.handleMouseEvent(event);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        this.handleMouseEvent(event);
    }

    @Override
    public void mouseExited(MouseEvent event) {
        this.handleMouseEvent(event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        this.handleMouseEvent(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        this.handleMouseEvent(event);
    }

    @Override
    public void keyTyped(KeyEvent event) {
        this.handleKeyboardEvent(event);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()){
            case KeyEvent.VK_ESCAPE -> GameState.setCurrentState(GameState.MENU);
            case KeyEvent.VK_P -> {
                style = !style;
                this.game.getMapBuilder().updateMapStyle();
            }
        }

        this.handleKeyboardEvent(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        this.handleKeyboardEvent(event);
    }

    private void handleMouseEvent(final MouseEvent event){
        this.game.getHandler().objects.stream()
                .filter(gameObject -> gameObject instanceof Player)
                .map(gameObject -> (Player) gameObject)
                .forEach(player -> {
                    switch (event.getID()){
                        case MouseEvent.MOUSE_CLICKED -> player.mouseClicked(event);
                        case MouseEvent.MOUSE_PRESSED -> player.mousePressed(event);
                        case MouseEvent.MOUSE_RELEASED -> player.mouseReleased(event);
                        case MouseEvent.MOUSE_ENTERED -> player.mouseEntered(event);
                        case MouseEvent.MOUSE_EXITED -> player.mouseExited(event);
                        case MouseEvent.MOUSE_DRAGGED -> player.mouseDragged(event);
                        case MouseEvent.MOUSE_MOVED -> player.mouseMoved(event);
                    }
                });
    }

    private void handleKeyboardEvent(final KeyEvent event){
        this.game.getHandler().objects.stream()
                .filter(gameObject -> gameObject instanceof Player)
                .map(gameObject -> (Player) gameObject)
                .forEach(player -> {
                    switch (event.getID()){
                        case KeyEvent.KEY_TYPED -> player.keyTyped(event);
                        case KeyEvent.KEY_PRESSED -> player.keyPressed(event);
                        case KeyEvent.KEY_RELEASED -> player.keyReleased(event);
                    }
                });
    }
}
