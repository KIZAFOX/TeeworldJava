package fr.kiza.teeworld.game.client.window.ui;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.button.PlayButton;
import fr.kiza.teeworld.game.client.window.ui.button.QuitButton;
import fr.kiza.teeworld.game.client.window.ui.button.handler.ButtonHandler;
import fr.kiza.teeworld.game.client.window.ui.button.handler.ButtonType;

import java.util.*;
import java.util.function.Supplier;

public class ButtonBuilder {

    protected final Game game;

    private final EnumMap<ButtonType, Supplier<? extends ButtonHandler>> BUTTON;
    private final Map<ButtonType, ButtonHandler> CACHED_BUTTON;

    public ButtonBuilder(final Game game){
        this.game = game;

        this.BUTTON = new EnumMap<>(ButtonType.class);
        this.CACHED_BUTTON= new HashMap<>();

        this.BUTTON.put(ButtonType.PLAY, () -> new PlayButton(this.game, 13, 10));
        this.BUTTON.put(ButtonType.QUIT, () -> new QuitButton(this.game, 13, 15));
    }

    public Optional<ButtonHandler> getButton(final ButtonType type){
        if(!this.CACHED_BUTTON.containsKey(type)){
            Optional.ofNullable(this.BUTTON.get(type))
                    .ifPresent(supplier -> this.CACHED_BUTTON.put(type, supplier.get()));
        }

        return Optional.ofNullable(this.CACHED_BUTTON.get(type));
    }

    public List<ButtonHandler> getButton(){
        final List<ButtonHandler> buttons = new ArrayList<>();

        this.BUTTON.forEach((type, supplier) -> {
            if(!this.CACHED_BUTTON.containsKey(type)){
                this.CACHED_BUTTON.put(type, supplier.get());
            }

            buttons.add(this.CACHED_BUTTON.get(type));
        });

        return buttons;
    }
}
