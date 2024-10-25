package fr.kiza.teeworld.game.object.entity.line;

import fr.kiza.teeworld.game.client.window.Game;
import fr.kiza.teeworld.game.object.GameObject;
import fr.kiza.teeworld.game.object.ObjectType;

import java.awt.*;

public class Line extends GameObject {

    private Point start, end;
    private double angle;

    private final int fixedLength;

    public Line(Game game, Point start, int length) {
        super(game, ObjectType.LINE, 100, 100);
        this.start = start;
        this.end = new Point(start.x, start.y);
        this.fixedLength = length;
        this.angle = 0;
    }

    @Override
    public void render(final Graphics2D graphics) {
        final int endX = (int) (start.x + fixedLength * Math.cos(angle)), endY = (int) (start.y + fixedLength * Math.sin(angle));

        graphics.setColor(Color.RED);
        graphics.drawLine(start.x, start.y, endX, endY);
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, this.width, this.height);
    }

    public void updateDirection(final Point mousePosition) {
        final double dx = mousePosition.x - start.x, dy = mousePosition.y - start.y;
        this.angle = Math.atan2(dy, dx);
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
