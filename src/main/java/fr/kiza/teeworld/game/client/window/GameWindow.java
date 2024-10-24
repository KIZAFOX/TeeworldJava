package fr.kiza.teeworld.game.client.window;

import fr.kiza.teeworld.game.client.window.ui.button.handler.ButtonHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {

    protected final Game game;

    /**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public GameWindow(final Game game) throws HeadlessException {
        this.game = game;

        final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

        //this.setExtendedState(MAXIMIZED_BOTH);
        //this.setUndecorated(true);

        this.setTitle("StickmanGame - v1.0");
        this.add(this.game.getGamePanel());
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //graphicsDevice.setFullScreenWindow(this);

        this.setVisible(true);

        this.addWindowFocusListener(new WindowFocusListener() {

            /**
             * Invoked when the Window is set to be the focused Window, which means
             * that the Window, or one of its subcomponents, will receive keyboard
             * events.
             *
             * @param e the event to be processed
             */
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            /**
             * Invoked when the Window is no longer the focused Window, which means
             * that keyboard events will no longer be delivered to the Window or any of
             * its subcomponents.
             *
             * @param e the event to be processed
             */
            @Override
            public void windowLostFocus(WindowEvent e) {
                game.getButtonBuilder().getButton().forEach(ButtonHandler::reset);
            }
        });
    }
}
