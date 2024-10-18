package fr.kiza.teeworld.main;

import fr.kiza.teeworld.game.client.window.Game;

import javax.swing.*;

/**
 * Main clas of the project
 *
 */
public final class App{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}
