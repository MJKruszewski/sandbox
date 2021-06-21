package pl.sunflux.domain.world;

import tinfour.voronoi.BoundedVoronoiDiagram;

import java.util.Set;

public class WorldMap {

    private GeneratorOptions generatorOptions;
    private BoundedVoronoiDiagram diagram;

    private Set<Tile> tiles;

    public WorldMap(GeneratorOptions generatorOptions, BoundedVoronoiDiagram diagram, Set<Tile> tiles) {
        this.generatorOptions = generatorOptions;
        this.diagram = diagram;
        this.tiles = tiles;
    }

    public int getWidth() {
        return generatorOptions.width;
    }

    public int getHeight() {
        return generatorOptions.height;
    }

    public int getPadding() {
        return generatorOptions.padding;
    }

    public Set<Tile> getTiles() {
        return tiles;
    }

    public BoundedVoronoiDiagram getDiagram() {
        return diagram;
    }
}
