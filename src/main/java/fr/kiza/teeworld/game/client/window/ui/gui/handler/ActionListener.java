package fr.kiza.teeworld.game.client.window.ui.gui.handler;

import java.awt.event.*;

public interface ActionListener {

    void mouseClicked(final MouseEvent event);

    void mousePressed(final MouseEvent event);

    void mouseReleased(final MouseEvent event);

    void mouseEntered(final MouseEvent event);

    void mouseExited(final MouseEvent event);

    void mouseDragged(final MouseEvent event);

    void mouseMoved(final MouseEvent event);

    void keyTyped(final KeyEvent event);

    void keyPressed(final KeyEvent event);

    void keyReleased(final KeyEvent event);
}