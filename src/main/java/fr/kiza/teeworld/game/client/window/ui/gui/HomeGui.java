package fr.kiza.teeworld.game.client.window.ui.gui;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.button.handler.ButtonAction;
import fr.kiza.teeworld.game.client.window.ui.button.handler.ButtonHandler;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.ActionListener;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.GuiHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.util.function.BiConsumer;

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

        this.renderButtons(graphics);
    }

    @Override
    public void update() {
        this.updateButtons();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.handleButtonEvent(event, ButtonAction::mousePressed);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        this.handleButtonEvent(event, ButtonAction::mouseReleased);
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
        this.handleButtonEvent(event, ButtonAction::mouseMoved);
    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    private void renderButtons(final Graphics2D graphics) {
        this.game.getButtonBuilder().getButton().forEach(buttons -> buttons.render(graphics));
    }

    private void updateButtons(){
        this.game.getButtonBuilder().getButton().forEach(ButtonHandler::update);
    }

    private void handleButtonEvent(final MouseEvent event, final BiConsumer<ButtonAction, MouseEvent> action){
        this.game.getButtonBuilder().getButton().forEach(buttons -> {
            if(buttons.getBounds().contains(event.getPoint())){
                action.accept((ButtonAction) buttons, event);
            }
        });
    }
}
