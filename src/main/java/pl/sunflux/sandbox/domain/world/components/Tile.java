package pl.sunflux.sandbox.domain.world.components;


import com.hoten.delaunay.voronoi.Center;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import pl.sunflux.sandbox.state.WorldMap;

import java.util.HashSet;
import java.util.Set;

public class Tile {

    final public Center center;
    final public Shape graphic;
    final public Shape[] graphics;
    public Paint currentColor;
    final public Set<Tile> neighbours = new HashSet<>();

    private Tile(Center center, Shape[] shapes) {
        this.center = center;
        this.graphic = shapes[0];
        this.graphics = shapes;
    }

    public static Tile getInstance(Center center, Shape[] shapes) {
        Tile tile = new Tile(
                center,
                shapes
        );

        WorldMap.addTile(tile);

        return tile;
    }

    public void setColor(Paint paint) {
        for (Shape shape : graphics) {
            shape.setFill(paint);
        }

        currentColor = paint;
    }


}
