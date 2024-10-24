package fr.kiza.teeworld.game.client.window.ui.button;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.button.handler.ButtonAction;
import fr.kiza.teeworld.game.client.window.ui.button.handler.ButtonHandler;
import fr.kiza.teeworld.game.client.window.ui.button.handler.ButtonSize;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PlayButton extends ButtonHandler implements ButtonAction {
    public PlayButton(final Game game, final int x, final int y) {
        super(game, ButtonSize.NORMAL, x, y);
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor((this.isMouseHovered() ? Color.LIGHT_GRAY : Color.GRAY));
        graphics.fillRect(this.x, this.y, this.width, this.height);

        this.renderText(graphics, "PLAY");
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if(this.isIn(event, this)){
            this.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if(this.isIn(event, this) && this.isMousePressed()){
            GameState.setCurrentState(GameState.PLAY);
        }
        this.game.getButtonBuilder().getButton().forEach(ButtonHandler::reset);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        this.game.getButtonBuilder().getButton().forEach(buttons -> buttons.setMouseHovered(false));
        this.game.getButtonBuilder().getButton().forEach(buttons -> {
            if(this.isIn(event, buttons)) buttons.setMouseHovered(true);
        });
    }
}
