package fr.kiza.teeworld.game.scheduler;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.mysql.dao.UserDAO;
import fr.kiza.teeworld.mysql.data.UserSession;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameScheduler {

    protected final Game game;

    public ScheduledExecutorService scheduler;
    public final UserDAO user;

    private static long timer = 0;
    private volatile boolean running = false;

    public GameScheduler(final Game game) {
        this.game = game;

        this.scheduler = Executors.newScheduledThreadPool(1);
        this.user = this.game.getDatabaseManager().getUserDAO();
    }

    public void start() {
        if(!this.running){
            this.running = true;

            if(this.scheduler.isShutdown() || this.scheduler.isTerminated()){
                this.scheduler = Executors.newScheduledThreadPool(1);
            }

            this.scheduler.scheduleAtFixedRate(() -> timer++, 0, 1, TimeUnit.SECONDS);
            this.game.log("[Scheduler] NEW GAME THREAD STARTED!");
        }
    }

    public void stop(final boolean killed) {
        if(this.running){
            this.running = false;
            this.scheduler.shutdown();

            final int id = this.user.getId(UserSession.getInstance().getUsername());

            if(!killed && (this.user.getTimer(id) == 0 || this.user.getTimer(id) > timer)) {
                this.user.setTimer(id, GameScheduler.getTimer());
            }

            timer = 0;

            this.game.log("[Scheduler] GAME THREAD ENDED!");
        }
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    public static long getTimer() {
        return timer;
    }

    public boolean isRunning() {
        return running;
    }
}