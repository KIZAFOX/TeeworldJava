package fr.kiza.teeworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Unit test for simple App.
 */
public class AppTest extends JPanel {

    private int x = 100, y = 100;

    public AppTest() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                moveStickman(e);
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        final Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.setColor(Color.RED);
        this.render(graphics2D);
    }

    private void render(Graphics2D graphics) {
        graphics.fillOval(x, y, 40, 40);

        graphics.drawLine(x + 20,y + 40, x + 20, y + 120);

        graphics.drawLine(x + 20, y + 60, x - 15, y + 90);
        graphics.drawLine(x + 20, y + 60, x + 55, y + 90);

        graphics.drawLine(x + 20, y + 120, x - 10, y + 170);
        graphics.drawLine(x + 20, y + 120, x + 50, y + 170);
    }

    private void moveStickman(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_Z) {
            y -= 10;
        }

        if(key == KeyEvent.VK_S) {
            y += 10;
        }

        if(key == KeyEvent.VK_Q) {
            x -= 10;
        }

        if(key == KeyEvent.VK_D) {
            x += 10;
        }

        repaint();
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Stickman Game");
        final AppTest app = new AppTest();

        frame.add(app);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
    }
}
