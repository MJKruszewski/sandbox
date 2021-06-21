package pl.sunflux.domain.world;

import javafx.scene.shape.Polygon;
import tinfour.common.Vertex;
import tinfour.voronoi.ThiessenPolygon;

import java.util.Set;

public class Tile {
    private Vertex center;
    private ThiessenPolygon polygon;
    private Polygon graphic;

    private Set<Tile> neighbours;
    private TileType type = TileType.WATER;

    private int elevation = 0;

    public Tile(Vertex center, ThiessenPolygon polygon) {
        this.center = center;
        this.polygon = polygon;
    }

    public void setNeighbours(Set<Tile> neighbours) {
        this.neighbours = neighbours;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public Vertex getCenter() {
        return center;
    }

    public ThiessenPolygon getPolygon() {
        return polygon;
    }

    public Set<Tile> getNeighbours() {
        return neighbours;
    }

    public TileType getType() {
        return type;
    }

    public Polygon getGraphic() {
        return graphic;
    }

    public void setGraphic(Polygon graphic) {
        this.graphic = graphic;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    //    public int getLengthToWater() {
//        neighbours.stream().forEach(tile -> {
//
//        });
//    }
}
