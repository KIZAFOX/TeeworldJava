package fr.kiza.teeworld.game.object.camera;

import fr.kiza.teeworld.game.object.GameObject;

import static fr.kiza.teeworld.game.client.window.GamePanel.*;

public class Camera {

    protected float x, y;

    public Camera(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public void update(final GameObject player){
        final float targetX = -player.getX() + (float) WIDTH / 2;
        final float targetY = -player.getY() + (float) HEIGHT / 2;

        final float smooth = .05F;

        this.x += (targetX - this.x) * smooth;
        this.y += (targetY - this.y) * smooth;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
