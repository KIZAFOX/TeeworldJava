package fr.kiza.teeworld.game.client.window.ui.gui;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.handler.ActionListener;
import fr.kiza.teeworld.game.client.window.ui.handler.GuiHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class HomeGui extends GuiHandler implements ActionListener {
    public HomeGui(final Game game) {
        super(game);
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.BLACK);

        this.renderGrill(graphics);
    }

    @Override
    public void update() {

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
            GameState.setCurrentState(GameState.PLAY);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
