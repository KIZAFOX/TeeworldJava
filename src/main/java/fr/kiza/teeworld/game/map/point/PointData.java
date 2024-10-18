package fr.kiza.teeworld.game.map.point;

import java.util.ArrayList;
import java.util.List;

public record PointData(int x, int y) {

    public static final List<PointData> COORDINATES = new ArrayList<>();

    public PointData(final int x, final int y) {
        this.x = x;
        this.y = y;

        COORDINATES.add(this);
    }
}