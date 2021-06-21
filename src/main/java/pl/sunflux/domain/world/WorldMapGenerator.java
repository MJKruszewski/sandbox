package pl.sunflux.domain.world;


import pl.sunflux.domain.world.generator.ElevationGenerator;
import pl.sunflux.domain.world.generator.LandMassGenerator;
import pl.sunflux.domain.world.renderer.WorldMapRenderer;
import tinfour.TestVertices;
import tinfour.common.IQuadEdge;
import tinfour.common.Vertex;
import tinfour.voronoi.BoundedVoronoiBuildOptions;
import tinfour.voronoi.BoundedVoronoiDiagram;
import tinfour.voronoi.ThiessenPolygon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Provides an example main method showing how to plot a graphic
 * of a Voronoi Diagram from a set of randomly generated vertices
 */
public class WorldMapGenerator {

    private LandMassGenerator landMassGenerator = new LandMassGenerator();
    private ElevationGenerator elevationGenerator = new ElevationGenerator();

    private List<Vertex> relax(BoundedVoronoiDiagram diagram) {
        List<Vertex> vList = new ArrayList<>(diagram.getVertices().size());
        int i = 0;

        for (ThiessenPolygon thiessenPolygon : diagram.getPolygons()) {
            double x = 0.;
            double y = 0.;

            for (IQuadEdge iQuadEdge : thiessenPolygon.getEdges()) {
                x += iQuadEdge.getA().x;
                y += iQuadEdge.getA().y;
            }

            x = x / thiessenPolygon.getEdges().size();
            y = y / thiessenPolygon.getEdges().size();

            vList.add(new Vertex(x, y, x * x + y * y - (x + y) - 0.5, i++));
        }

        return vList;
    }

    public WorldMap generate(GeneratorOptions generatorOptions) {
        List<Vertex> vList = TestVertices.makeRandomVertices(generatorOptions.nVertices, generatorOptions.seed);
        BoundedVoronoiBuildOptions options = new BoundedVoronoiBuildOptions();
        BoundedVoronoiDiagram diagram = new BoundedVoronoiDiagram(vList, options);

        int i = 0;

        while (i < generatorOptions.relaxation) {
            vList = relax(diagram);
            diagram = new BoundedVoronoiDiagram(vList, options);
            i++;
        }

        HashSet<Tile> tiles = prepareTiles(generatorOptions, diagram);

        landMassGenerator.generate(tiles, generatorOptions);
        elevationGenerator.generate(tiles, generatorOptions);

        return new WorldMap(generatorOptions, diagram, tiles);
    }

    private HashSet<Tile> prepareTiles(GeneratorOptions generatorOptions, BoundedVoronoiDiagram diagram) {
        HashSet<Tile> tiles = new HashSet<>(generatorOptions.nVertices);
        WorldMapRenderer worldMapRenderer = new WorldMapRenderer(generatorOptions, diagram);

        diagram.getPolygons().forEach(thiessenPolygon -> {
            Tile e = new Tile(thiessenPolygon.getVertex(), thiessenPolygon);
            worldMapRenderer.generatePolygon(e);
            tiles.add(e);
        });

        tiles.forEach(tile -> {
            Set<Tile> neighbours = new HashSet<>();

            tile.getPolygon().getEdges().forEach(iQuadEdge -> {
                tiles.forEach(nextTile -> {
                    if (tile.getCenter().getIndex() == nextTile.getCenter().getIndex()) {
                        return;
                    }

                    if (nextTile.getPolygon().isPointInPolygon(iQuadEdge.getA().x, iQuadEdge.getA().y)) {
                        neighbours.add(nextTile);
                    }
                });
            });

            tile.setNeighbours(neighbours);
        });

        return tiles;
    }
}
