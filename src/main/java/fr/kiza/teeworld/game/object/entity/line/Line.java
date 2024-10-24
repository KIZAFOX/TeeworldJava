package fr.kiza.teeworld.game.object.entity.line;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.object.GameObject;
import fr.kiza.teeworld.game.object.ObjectType;

import java.awt.*;

public class Line extends GameObject {

    private Point start, end;

    private final int fixedLength;

    public Line(Game game, Point start, int length) {
        super(game, ObjectType.LINE, 0, 0);
        this.start = start;
        this.end = new Point(start.x + length, start.y);
        this.fixedLength = length;
    }
    @Override
    public void render(final Graphics2D graphics){
        graphics.setColor(Color.RED);
        graphics.drawLine(start.x, start.y, end.x, end.y);  // C'est correct
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, this.width, this.height);
    }

    public void updateEnd(final Point mousePosition, final Point playerPosition) {
        this.start = playerPosition;

        // Calculez l'angle entre le point de départ et la position de la souris
        double angle = Math.atan2(mousePosition.y - start.y, mousePosition.x - start.x);

        // Calculez la distance réelle entre le joueur et la souris
        double distance = start.distance(mousePosition);

        double lengthToUse = Math.min(fixedLength, distance);

        int newX = (int) (start.x + lengthToUse * Math.cos(angle));
        int newY = (int) (start.y + lengthToUse * Math.sin(angle));

        this.end = new Point(newX, newY);

        System.out.println("New End (Using Length): " + this.end);
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}
