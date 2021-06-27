package pl.sunflux.sandbox.domain.world;


import com.hoten.delaunay.examples.GraphImpl;
import com.hoten.delaunay.voronoi.groundshapes.Blob;
import com.hoten.delaunay.voronoi.groundshapes.HeightAlgorithm;
import com.hoten.delaunay.voronoi.groundshapes.Perlin;
import com.hoten.delaunay.voronoi.groundshapes.Radial;
import com.hoten.delaunay.voronoi.nodename.as3delaunay.Voronoi;
import javafx.application.Platform;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import pl.sunflux.sandbox.Main;
import pl.sunflux.sandbox.domain.game.WorldMap;
import pl.sunflux.sandbox.domain.world.GeneratorOptions.AlgorithmType;
import pl.sunflux.sandbox.domain.world.components.Tile;

import java.util.Random;


/**
 * Provides an example main method showing how to plot a graphic
 * of a Voronoi Diagram from a set of randomly generated vertices
 */
public class WorldMapGenerator {

    public void generate(GeneratorOptions generatorOptions) {
        WorldMap.removeAll();

        Random r = new Random(generatorOptions.seed);
        HeightAlgorithm algorithm = getAlgorithmImplementation(r, generatorOptions.algorithmType);

        Voronoi v = new Voronoi(generatorOptions.sitesAmount, generatorOptions.graphBounds, generatorOptions.graphBounds, r, null);
        GraphImpl graph = new GraphImpl(v, generatorOptions.lloydRelaxations, r, algorithm);

        Main.logger.debug("centers: " + graph.centers.size());

        graph.centers.parallelStream().forEach(center -> {
            Polygon[] polygons = graph.createPolygons(center);

            Tile.getInstance(center, polygons);
            WorldMap.addPolygons(polygons);
        });

        graph.edges.parallelStream().forEach(edge -> {
            if (edge.river <= 0) {
                return;
            }
            Line line = new Line(edge.v0.loc.x, edge.v0.loc.y, edge.v1.loc.x, edge.v1.loc.y);
            line.setStroke(javafx.scene.paint.Color.BLUE);
            line.prefWidth(1 + (int) Math.sqrt(edge.river * 2));

            WorldMap.addPolygons(line);
        });

        Main.logger.debug("shapes: " + WorldMap.mapShapes.size());
        WorldMap.diagram = graph;

        Platform.runLater(() -> {
            WorldMap.tiles.parallelStream().forEach(tile -> {
                tile.center.neighbors.forEach(center -> {
                    tile.neighbours.add(WorldMap.getTile(center));
                });
            });
        });
    }


    /**
     * Currently there are only 1 algorithm. You can choose one of algorithms exactly or random from this list:
     * <ol start = "0">
     *     <li>random</li>
     *     <li>radial</li>
     *     <li>blob</li>
     *     <li>perlin</li>
     * </ol>
     */
    private static HeightAlgorithm getAlgorithmImplementation(Random r, AlgorithmType algorithmType) {
        switch (algorithmType) {
            case RADIAL:
                return new Radial(1.07,
                        r.nextInt(5) + 1,
                        r.nextDouble() * 2 * Math.PI,
                        r.nextDouble() * 2 * Math.PI,
                        r.nextDouble() * .5 + .2);
            case BLOB:
                return new Blob();
            case PERLIN:
                return new Perlin(r, 7, 256, 256);
            default:
                throw new RuntimeException("Method \"getAlgorithmImplementation()\" is broken. " +
                        "Check implementations map and switch statement. Their values and cases must match.");
        }
    }

}
