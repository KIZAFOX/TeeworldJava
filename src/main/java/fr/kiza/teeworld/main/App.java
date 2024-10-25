package fr.kiza.teeworld.main;

import fr.kiza.teeworld.game.client.window.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Main clas of the project
 *
 */
public final class App{
    public static void main(String[] args) {
        if(GraphicsEnvironment.isHeadless()){
            System.err.println("Environnement headless détecté. Impossible de créer une interface graphique.");
            System.exit(1);
        }else{
            SwingUtilities.invokeLater(Game::new);
        }
    }
}