package fr.kiza.teeworld.game.client.inputs.mouse;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.ActionListener;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.GuiHandler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;
import java.util.function.BiConsumer;

public class MouseInputs extends MouseAdapter implements MouseListener, MouseMotionListener {

    protected final Game game;

    public MouseInputs(final Game game) {
        this.game = game;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseClicked(final MouseEvent event) {
        this.handleEvent(event, ActionListener::mouseClicked);
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mousePressed(final MouseEvent event) {
        this.handleEvent(event, ActionListener::mousePressed);
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseReleased(final MouseEvent event) {
        this.handleEvent(event, ActionListener::mouseReleased);
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseEntered(final MouseEvent event) {
        this.handleEvent(event, ActionListener::mouseEntered);
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseExited(final MouseEvent event) {
        this.handleEvent(event, ActionListener::mouseExited);
    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseDragged(final MouseEvent event) {
        this.handleEvent(event, ActionListener::mouseDragged);
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseMoved(final MouseEvent event) {
        this.handleEvent(event, ActionListener::mouseMoved);

        this.game.getGameWindow().setTitle("StickmanGame - x: " + event.getX() + ", y: " + event.getY() + " | FPS: " + this.game.getFPS().getFrames());
    }

    private void handleEvent(final MouseEvent event, final BiConsumer<ActionListener, MouseEvent> action){
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
