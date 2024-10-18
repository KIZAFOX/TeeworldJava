package fr.kiza.teeworld.game.client.window;

import fr.kiza.teeworld.game.client.inputs.keyboard.KeyboardInputs;
import fr.kiza.teeworld.game.client.inputs.mouse.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    protected final Game game;

    public static int
            PIXEL = 32,
            WIDTH = 24 * PIXEL,
            HEIGHT = 14 * PIXEL;

    public GamePanel(final Game game){
        this.game = game;

        this.setFocusable(true);
        this.requestFocusInWindow();

        final Dimension dimension = new Dimension(WIDTH, HEIGHT);
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);

        this.addKeyListener(new KeyboardInputs(this.game));

        final MouseInputs mouseInputs = new MouseInputs(this.game);
        this.addMouseListener(mouseInputs);
        this.addMouseMotionListener(mouseInputs);

        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.game.render((Graphics2D) graphics);
    }
}
