package fr.kiza.teeworld.game.client.window.ui.gui;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.handler.ActionListener;
import fr.kiza.teeworld.game.client.window.ui.handler.GuiHandler;
import fr.kiza.teeworld.game.object.ObjectType;
import fr.kiza.teeworld.game.object.entity.player.Player;
import fr.kiza.teeworld.game.utils.formatter.TimeFixer;
import fr.kiza.teeworld.game.scheduler.GameScheduler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayGui extends GuiHandler implements ActionListener {

    public PlayGui(final Game game) {
        super(game);
    }

    @Override
    public void render(Graphics2D graphics) {
        this.game.getTextRenderer().renderMidCentered(graphics, TimeFixer.formatTime(GameScheduler.getTimer()));

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

    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_UP){
            GameState.setCurrentState(GameState.MENU);
        }

        if(event.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

        this.game.getHandler().objects.stream()
                .filter(gameObject -> gameObject instanceof Player)
                .map(gameObject -> (Player) gameObject)
                .forEach(player -> player.keyPressed(event));
    }

    @Override
    public void keyReleased(KeyEvent event) {
        this.game.getHandler().objects.stream()
                .filter(gameObject -> gameObject instanceof Player)
                .map(gameObject -> (Player) gameObject)
                .forEach(player -> player.keyReleased(event));
    }
}
