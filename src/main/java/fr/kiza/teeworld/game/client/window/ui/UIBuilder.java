package fr.kiza.teeworld.game.client.window.ui;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.gui.HomeGui;
import fr.kiza.teeworld.game.client.window.ui.gui.LoginGui;
import fr.kiza.teeworld.game.client.window.ui.gui.PlayGui;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.GuiHandler;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class UIBuilder {

    protected final Game game;

    private final EnumMap<GameState, Supplier<? extends GuiHandler>> UI;
    private final Map<GameState, GuiHandler> CACHED_UI;

    public UIBuilder(final Game game){
        this.game = game;

        this.UI = new EnumMap<>(GameState.class);
        this.CACHED_UI = new HashMap<>();

        this.UI.put(GameState.MENU, () -> new HomeGui(this.game));
        this.UI.put(GameState.PLAY, () -> new PlayGui(this.game));
        this.UI.put(GameState.LOGIN, () -> new LoginGui(this.game));
    }

    public Optional<GuiHandler> getGui(final GameState state){
        if(!CACHED_UI.containsKey(state)){
            Optional.ofNullable(this.UI.get(state))
                    .ifPresent(supplier -> CACHED_UI.put(state, supplier.get()));
        }

        return Optional.ofNullable(CACHED_UI.get(state));
    }
}
