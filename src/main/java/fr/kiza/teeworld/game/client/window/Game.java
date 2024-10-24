package fr.kiza.teeworld.game.client.window;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.ui.ButtonBuilder;
import fr.kiza.teeworld.game.client.window.ui.UIBuilder;
import fr.kiza.teeworld.game.map.MapBuilder;
import fr.kiza.teeworld.game.map.MapUtil;
import fr.kiza.teeworld.game.object.Handler;
import fr.kiza.teeworld.game.object.camera.Camera;
import fr.kiza.teeworld.game.utils.FPS;
import fr.kiza.teeworld.game.utils.text.TextRenderer;
import fr.kiza.teeworld.mysql.database.DatabaseManager;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class Game {

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;

    private final TextRenderer textRenderer;
    private final UIBuilder uiBuilder;
    private final ButtonBuilder buttonBuilder;

    private final Handler handler;
    private final Camera camera;

    private final MapBuilder mapBuilder;

    private final FPS fps;

    private final Thread thread;

    private final DatabaseManager databaseManager;

    public Game() {
        final Instant start = Instant.ofEpochMilli(Instant.now().toEpochMilli());

        GameState.setCurrentState(GameState.MENU);

        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(this);

        this.textRenderer = new TextRenderer(this);
        this.uiBuilder = new UIBuilder(this);
        this.buttonBuilder = new ButtonBuilder(this);

        this.handler = new Handler(this);
        this.camera = new Camera(0, 0);

        this.mapBuilder = new MapBuilder(this);
        this.mapBuilder.create(MapUtil.MAP_1);

        this.fps = new FPS(this);

        this.thread = new Thread(this.fps);
        this.thread.start();

        this.databaseManager = new DatabaseManager();
        this.databaseManager.connect();

        this.log("Game resolution: " +WIDTH + "x" + HEIGHT);
        this.log("Tiles size: " + WIDTH / PIXEL + "x" + HEIGHT / PIXEL);
        this.log("Done in: " + Duration.between(start, Instant.ofEpochMilli(Instant.now().toEpochMilli())) + "ms");
    }

    public void render(final Graphics2D graphics) {
        this.uiBuilder.getGui(GameState.getCurrentState())
                .orElseThrow(() -> new IllegalStateException("No GUI for current state!"))
                .render(graphics);
    }

    public void update() {
        this.uiBuilder.getGui(GameState.getCurrentState())
                .orElseThrow(() -> new IllegalStateException("No GUI for current state!"))
                .update();
    }

    public void log(final String message) {
        System.out.println("[" + this.thread.getName() + ":" + this.thread.getState() + "] " + message);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public TextRenderer getTextRenderer() {
        return textRenderer;
    }

    public UIBuilder getUiBuilder() {
        return uiBuilder;
    }

    public ButtonBuilder getButtonBuilder() {
        return buttonBuilder;
    }

    public Handler getHandler() {
        return handler;
    }

    public Camera getCamera() {
        return camera;
    }

    public MapBuilder getMapBuilder() {
        return mapBuilder;
    }

    public FPS getFPS() {
        return fps;
    }

    public Thread getThread() {
        return thread;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
