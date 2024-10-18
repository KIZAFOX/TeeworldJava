package fr.kiza.teeworld.game.utils;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.Game;

public class FPS implements Runnable {

    protected final Game game;

    private final int MAX_FPS = 144, UPS_SET = 200;

    private int frames = 0, updates = 0;

    public FPS(final Game game) {
        this.game = game;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        double
                timePerFrame = 1000000000.0 / MAX_FPS,
                timePerUpdate = 1000000000.0 / UPS_SET,
                deltaU = 0,
                deltaF = 0;

        long previousTime = System.nanoTime(), lastCheck = System.currentTimeMillis();

        int frames = 0, updates = 0;

        try {
            while (!this.game.getThread().isInterrupted()){
                long currenTime = System.nanoTime();

                deltaU += (currenTime - previousTime) / timePerUpdate;
                deltaF += (currenTime - previousTime) / timePerFrame;
                previousTime = currenTime;

                if(deltaU >= 1){
                    this.game.update();
                    updates++;
                    deltaU--;
                }

                if(deltaF >= 1){
                    this.game.getGamePanel().repaint();
                    frames++;
                    deltaF--;
                }

                if(System.currentTimeMillis() - lastCheck >= 1000){
                    lastCheck = System.currentTimeMillis();
                    this.frames = frames;
                    this.updates = updates;
                    this.game.log("FPS: " + frames + " | UPS: " + updates);
                    this.game.log(GameState.getCurrentState().toString());
                    frames = 0;
                    updates = 0;
                }
            }
        }catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Game getGame() {
        return game;
    }

    public int getMaxFPS() {
        return MAX_FPS;
    }

    public int getUPSSet() {
        return UPS_SET;
    }

    public int getFrames() {
        return frames;
    }

    public int getUpdates() {
        return updates;
    }
}
