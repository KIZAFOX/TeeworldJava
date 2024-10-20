package fr.kiza.teeworld.game.client.window;

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
            public void windowGainedFocus(WindowEvent e) { }

            public void windowLostFocus(WindowEvent e) { }
        });
    }
}
