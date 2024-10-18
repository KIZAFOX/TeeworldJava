package fr.kiza.teeworld.game.client.inputs.keyboard;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.handler.ActionListener;
import fr.kiza.teeworld.game.client.window.ui.handler.GuiHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;
import java.util.function.BiConsumer;

public class KeyboardInputs implements KeyListener {

    protected final Game game;

    public KeyboardInputs(final Game game) {
        this.game = game;
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyTyped(final KeyEvent event) {
        this.handleEvent(event, ActionListener::keyTyped);
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyPressed(final KeyEvent event) {
        this.handleEvent(event, ActionListener::keyPressed);
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyReleased(final KeyEvent event) {
        this.handleEvent(event, ActionListener::keyReleased);
    }

    private void handleEvent(final KeyEvent event, final BiConsumer<ActionListener, KeyEvent> action){
        final Optional<GuiHandler> optional = this.game.getUiBuilder().getGui(GameState.getCurrentState());

        optional
                .filter(ActionListener.class::isInstance)
                .map(ActionListener.class::cast)
                .ifPresentOrElse(
                        listener -> action.accept(listener, event),
                        () -> this.game.log("Warning: No ActionListener found for GameState: " + GameState.getCurrentState())
                );
    }
}