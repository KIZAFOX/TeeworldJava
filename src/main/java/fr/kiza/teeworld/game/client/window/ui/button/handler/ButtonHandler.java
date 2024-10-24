package fr.kiza.teeworld.game.client.window.ui.button.handler;

import fr.kiza.teeworld.game.client.window.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public abstract class ButtonHandler {

    protected final Game game;

    protected int x, y, width, height;
    protected boolean mousePressed, mouseReleased, mouseHovered;

    public ButtonHandler(final Game game, final int x, final int y) {
        this.game = game;
        this.x = x * (WIDTH / PIXEL);
        this.y = y * (HEIGHT / PIXEL);
    }

    public ButtonHandler(final Game game, final ButtonSize buttonSize, final int x, final int y) {
        this.game = game;
        this.x = x * (WIDTH / PIXEL);
        this.y = y * (HEIGHT / PIXEL);
        this.width = buttonSize.getWidth();
        this.height = buttonSize.getHeight();
    }

    public abstract void render(final Graphics2D graphics);

    public abstract void update();

    public abstract Rectangle getBounds();

    public void renderText(final Graphics2D graphics, final String title){
        int fontSize = Math.min(this.width, this.height) / 2;
        final Font font = graphics.getFont().deriveFont((float) fontSize);

        graphics.setColor(Color.WHITE);
        graphics.setFont(font);

        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int
                titleWidth = fontMetrics.stringWidth(title),
                titleHeight = fontMetrics.getHeight(),

                centeredX = this.x + ((this.width - titleWidth) / 2),
                centeredY = this.y + ((this.height - titleHeight) / 2 + fontMetrics.getAscent());

        graphics.drawString(title, centeredX, centeredY);
    }

    public boolean isIn(final MouseEvent event, final ButtonHandler button){
        return button.getBounds().contains(event.getX(), event.getY());
    }

    public void setLocation(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    public void setSize(final ButtonSize buttonSize){
        this.width = buttonSize.getWidth();
        this.height = buttonSize.getHeight();
    }

    public void setSize(final int width, final int height){
        this.width = width;
        this.height = height;
    }

    public void reset(){
        this.mousePressed = false;
        this.mouseReleased = false;
        this.mouseHovered = false;
    }

    public Game getGame() {
        return game;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseReleased() {
        return mouseReleased;
    }

    public void setMouseReleased(boolean mouseReleased) {
        this.mouseReleased = mouseReleased;
    }

    public boolean isMouseHovered() {
        return mouseHovered;
    }

    public void setMouseHovered(boolean mouseHovered) {
        this.mouseHovered = mouseHovered;
    }
}
