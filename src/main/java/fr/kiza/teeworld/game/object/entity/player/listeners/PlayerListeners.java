package fr.kiza.teeworld.game.object.entity.player.listeners;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.event.CollisionEvent;
import fr.kiza.teeworld.game.event.CollisionListener;
import fr.kiza.teeworld.game.map.MapUtil;
import fr.kiza.teeworld.game.object.GameObject;
import fr.kiza.teeworld.game.object.block.types.BlockFinish;
import fr.kiza.teeworld.game.object.block.types.BlockKill;
import fr.kiza.teeworld.game.object.block.types.BlockStart;
import fr.kiza.teeworld.game.object.entity.Entity;
import fr.kiza.teeworld.game.object.entity.player.Player;
import fr.kiza.teeworld.game.scheduler.GameScheduler;

public class PlayerListeners implements CollisionListener {

    protected final Game game;

    private final GameScheduler gameScheduler;

    public PlayerListeners(final Game game) {
        this.game = game;
        this.gameScheduler = new GameScheduler(this.game);
    }

    @Override
    public void onCollision(CollisionEvent event) {
        final Entity entity = (Entity) event.entity();
        final GameObject block = event.block();

        if(entity instanceof final Player player){
            if(block instanceof BlockStart){
                this.gameScheduler.start();
            }else if(block instanceof BlockFinish){
                this.gameScheduler.stop(false);
            }else if(block instanceof BlockKill){
                player.setLocation(MapUtil.SPAWN_POINT.x(), MapUtil.SPAWN_POINT.y());
                this.gameScheduler.stop(true);
            }
        }
    }

    public Game getGame() {
        return game;
    }
}
