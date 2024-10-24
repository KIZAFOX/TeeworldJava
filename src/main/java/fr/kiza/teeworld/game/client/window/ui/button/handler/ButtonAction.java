package fr.kiza.teeworld.game.client.window.ui.button.handler;

import java.awt.event.MouseEvent;

public interface ButtonAction {

    void mousePressed(final MouseEvent event);

    void mouseReleased(final MouseEvent event);

    void mouseMoved(final MouseEvent event);

}
