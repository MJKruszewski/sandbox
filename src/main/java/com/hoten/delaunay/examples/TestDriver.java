package com.hoten.delaunay.examples;

import com.hoten.delaunay.voronoi.VoronoiGraph;
import com.hoten.delaunay.voronoi.groundshapes.Blob;
import com.hoten.delaunay.voronoi.groundshapes.HeightAlgorithm;
import com.hoten.delaunay.voronoi.groundshapes.Perlin;
import com.hoten.delaunay.voronoi.groundshapes.Radial;
import com.hoten.delaunay.voronoi.nodename.as3delaunay.Voronoi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import pl.sunflux.sandbox.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * <h3>How to use:</h3>
 * Just change constants to customize graph, it's shape and image.
 */
public class TestDriver extends Application {

    /**
     * The side of the square in which the graph will be fitted.
     */
    private static final int GRAPH_BOUNDS = 2048;

    /**
     * Number of pieces for the graph.
     */
    private static final int SITES_AMOUNT = 8_000;

    /**
     * Each time a relaxation step is performed, the points are left in a slightly more even distribution:
     * closely spaced points move farther apart, and widely spaced points move closer together.
     */
    private static final int LLOYD_RELAXATIONS = 2;

    /**
     * Randomizing number. Use it with {@link #RANDOM_SEED} = false to get same image every time.
     */
    private static long SEED = 123L;

    /**
     * You can make it false if you want to check some changes in code or image/graph size.
     */
    private static final boolean RANDOM_SEED = true;

    /**
     * Random, radial, blob, etc. See {@link #getAlgorithmImplementation(Random, String)}
     */
    private static final String ALGORITHM = "perlin";


    @Override
    public void start(Stage primaryStage) throws Exception {
        if (RANDOM_SEED) SEED = System.nanoTime();

        printInfo();
        ArrayList<Shape> img = createVoronoiGraph(GRAPH_BOUNDS, SITES_AMOUNT, LLOYD_RELAXATIONS, SEED, ALGORITHM).createMap();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../../sample.fxml"));
        Region root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();


        AnchorPane anchorPane = (AnchorPane) scene.lookup("#mapPane");
        anchorPane.getChildren().addAll(img);
    }

    private static void printInfo() {
        System.out.println("Seed: " + SEED);
        System.out.println("Bounds: " + GRAPH_BOUNDS);
        System.out.println("Sites: " + SITES_AMOUNT);
        System.out.println("Shape: " + ALGORITHM);
        System.out.println("Relaxs: " + LLOYD_RELAXATIONS);
        System.out.println("=============================");
    }

    public static VoronoiGraph createVoronoiGraph(int bounds, int numSites, int numLloydRelaxations, long seed, String algorithmName) {
        final Random r = new Random(seed);
        HeightAlgorithm algorithm = getAlgorithmImplementation(r, algorithmName);

        //make the intial underlying voronoi structure
        final Voronoi v = new Voronoi(numSites, bounds, bounds, r, null);

        //assemble the voronoi strucutre into a usable graph object representing a map
        final GraphImpl graph = new GraphImpl(v, numLloydRelaxations, r, algorithm);

        return graph;
    }

    /**
     * Currently there are only 1 algorithm. You can choose one of algorithms exactly or random from this list:
     * <ol start = "0">
     *     <li>random</li>
     *     <li>radial</li>
     *     <li>blob</li>
     *     <li>perlin</li>
     * </ol>
     *
     * @param r    Randomizer.
     * @param name Name of the algorithm.
     * @return
     */
    private static HeightAlgorithm getAlgorithmImplementation(Random r, String name) {
        HashMap<String, Integer> implementations = new HashMap<>();
        implementations.put("random", 0);
        implementations.put("radial", 1);
        implementations.put("blob", 2);
        implementations.put("perlin", 3);
        int i = implementations.getOrDefault(name, 0);
        if (i == 0) i = 1 + r.nextInt(implementations.size() - 1);
        switch (i) {
            case 1:
                return new Radial(1.07,
                        r.nextInt(5) + 1,
                        r.nextDouble() * 2 * Math.PI,
                        r.nextDouble() * 2 * Math.PI,
                        r.nextDouble() * .5 + .2);
            case 2:
                return new Blob();
            case 3:
                return new Perlin(r, 7, 256, 256);
            default:
                throw new RuntimeException("Method \"getAlgorithmImplementation()\" is broken. " +
                        "Check implementations map and switch statement. Their values and cases must match.");
        }
    }

}
