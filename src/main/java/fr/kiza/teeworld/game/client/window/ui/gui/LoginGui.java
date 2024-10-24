package fr.kiza.teeworld.game.client.window.ui.gui;

import fr.kiza.teeworld.game.client.status.GameState;
import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.ActionListener;
import fr.kiza.teeworld.game.client.window.ui.gui.handler.GuiHandler;
import fr.kiza.teeworld.mysql.dao.UserDAO;
import fr.kiza.teeworld.mysql.data.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class LoginGui extends GuiHandler implements ActionListener {

    private final JTextField usernameField;

    private String username = "", loadingMessage = "";
    private boolean showCursor, showSuccess = false, showError = false;
    private int loadingProgress = 0;

    public LoginGui(Game game) {
        super(game);

        this.usernameField = new JTextField(20);
        this.usernameField.setBounds(50, 50, 200, 30);

        new Timer(500, (event) -> {
            this.showCursor = !showCursor;
            this.update();
        }).start();
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.BLACK);

        graphics.setColor(Color.WHITE);
        graphics.drawString("Entrez votre pseudo :", 50, 40);

        graphics.setColor(Color.BLACK);
        graphics.fillRect(50, 50, 200, 30);

        graphics.setColor(Color.WHITE);
        graphics.drawString(this.username, 55, 70);

        if(this.showCursor){
            final int cursorX = 55 + graphics.getFontMetrics().stringWidth(this.username);
            graphics.drawString("|", cursorX, 70);
        }

        if(this.showSuccess){
            graphics.setColor(Color.GREEN);
            graphics.drawString(this.loadingMessage, 55, 100);

            graphics.setColor(Color.GRAY);
            graphics.fillRect(50, HEIGHT - 60, 250 * 2, 20);
            graphics.setColor(Color.GREEN);
            graphics.fillRect(50, HEIGHT - 60, loadingProgress * 2, 20);
        }

        if(this.showError){
            graphics.setColor(Color.RED);
            graphics.drawString("Pseudo incorrect ou déjà pris !", 55, 100);
        }
    }

    @Override
    public void update() {
        if(this.loadingProgress < 250){
            this.loadingProgress++;
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if(event.getX() >= 50 && event.getX() <= 250 && event.getY() >= 50 && event.getY() <= 80){
            this.usernameField.requestFocus();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

    @Override
    public void keyTyped(KeyEvent event) {
        final char c = event.getKeyChar();

        if(Character.isLetterOrDigit(c) || Character.isWhitespace(c)){
            this.username += c;
        }else if(c == KeyEvent.VK_BACK_SPACE){
            if(!this.username.isEmpty()){
                this.username = this.username.substring(0, this.username.length() - 1);
            }
        }

        if(c == KeyEvent.VK_ENTER){
            final UserDAO user = this.game.getDatabaseManager().getUserDAO();

            if(!this.username.trim().isEmpty()){
                this.showError = false;
                this.showSuccess = true;

                if(user.exists(this.username)){
                    user.load(this.username);
                    this.loadingMessage = "Le compte a été trouvé, chargement...";

                    UserSession.getInstance().setUsername(this.username);
                }else{
                    user.createAccount(this.username);
                    this.loadingMessage = "Création du compte ! Veuillez attendre.";

                    UserSession.getInstance().setUsername(this.username);
                }

                this.loadingProgress = 0;

                new Timer(20, (secondEvent) -> {
                    if(this.loadingProgress < 250){
                        this.loadingProgress += 2;
                    }else{
                        ((Timer) secondEvent.getSource()).stop();
                        this.showSuccess = false;
                        GameState.setCurrentState(GameState.PLAY);
                    }
                }).start();
            }else{
                this.showError = true;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
