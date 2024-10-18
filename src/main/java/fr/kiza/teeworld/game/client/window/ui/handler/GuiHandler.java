package fr.kiza.teeworld.game.client.window.ui.handler;

import fr.kiza.teeworld.game.client.window.Game;

import java.awt.*;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;
import static fr.kiza.teeworld.game.client.window.GamePanel.WIDTH;

public abstract class GuiHandler {

    protected final Game game;

    public GuiHandler(Game game) {
        this.game = game;
    }

    public abstract void render(final Graphics2D graphics);

    public abstract void update();

    public void renderGrill(final Graphics2D graphics){
        graphics.setColor(Color.WHITE);
        for(int x = 0; x < WIDTH; x += PIXEL) {
            graphics.drawLine(x, 0, x, HEIGHT);
        }

        for(int y = 0; y < HEIGHT; y += PIXEL) {
            graphics.drawLine(0, y, WIDTH, y);
        }
    }

    public Game getGame() {
        return game;
    }
}
