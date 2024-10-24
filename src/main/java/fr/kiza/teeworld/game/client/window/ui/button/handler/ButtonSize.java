package fr.kiza.teeworld.game.client.window.ui.button.handler;

public enum ButtonSize {
    TINY(50, 25),
    NORMAL(150, 50),
    BIG(250, 150);

    private final int width, height;

    ButtonSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
