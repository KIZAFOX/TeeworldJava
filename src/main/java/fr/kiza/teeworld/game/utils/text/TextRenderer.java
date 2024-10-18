package fr.kiza.teeworld.game.utils.text;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.GamePanel;

import java.awt.*;

public record TextRenderer(Game game) {

    public void renderMidCentered(final Graphics2D graphics, final String message) {
        final Font font = new Font("Arial", Font.BOLD, 24);
        graphics.setFont(font);
        final FontMetrics metrics = graphics.getFontMetrics(font);

        int
                x = (GamePanel.WIDTH - metrics.stringWidth(message)) / 2,
                y = 50;

        graphics.setColor(Color.WHITE);
        graphics.drawString(message, x, y);
    }

    public void renderLeftBottom(final Graphics2D graphics, final String message) {
        final Font font = new Font("Arial", Font.BOLD, 24);
        graphics.setFont(font);
        final FontMetrics metrics = graphics.getFontMetrics(font);

        int
                x = 10,
                y = GamePanel.HEIGHT - metrics.getHeight();

        graphics.setColor(Color.GREEN);
        graphics.drawString(message, x, y);
    }
}
