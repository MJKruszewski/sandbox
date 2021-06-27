package pl.sunflux.sandbox.domain.game;

import com.hoten.delaunay.voronoi.Center;
import com.hoten.delaunay.voronoi.VoronoiGraph;
import javafx.scene.shape.Shape;
import pl.sunflux.sandbox.domain.world.GeneratorOptions;
import pl.sunflux.sandbox.domain.world.components.Tile;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WorldMap {

    public static GeneratorOptions generatorOptions;
    public static VoronoiGraph diagram;

    public final static ConcurrentLinkedQueue<Shape> mapShapes = new ConcurrentLinkedQueue<>();
    public final static ConcurrentLinkedQueue<Tile> tiles = new ConcurrentLinkedQueue<>();
    private final static Map<Center, Tile> centerTileMap = new ConcurrentHashMap<>(8000);
    private final static Map<Tile, Center> tileCenterMap = new ConcurrentHashMap<>(8000);
    private final static Map<Shape, Tile> shapeTileMap = new ConcurrentHashMap<>(8000);

    public static void addTile(Tile tile) {
        tiles.add(tile);
        centerTileMap.put(tile.center, tile);
        tileCenterMap.put(tile, tile.center);
        shapeTileMap.put(tile.graphic, tile);
    }

    public static void removeAll() {
        tiles.clear();
        centerTileMap.clear();
        tileCenterMap.clear();
        mapShapes.clear();
    }

    public static void addPolygons(Shape[] shapes) {
        for (Shape shape : shapes) {
            WorldMap.mapShapes.add(shape);
        }
    }

    public static void addPolygons(Shape shape) {
        WorldMap.mapShapes.add(shape);
    }

    public static Tile getTile(Center center) {
        return centerTileMap.get(center);
    }

    public static Tile getTile(Shape shape) {
        return shapeTileMap.get(shape);
    }
}
