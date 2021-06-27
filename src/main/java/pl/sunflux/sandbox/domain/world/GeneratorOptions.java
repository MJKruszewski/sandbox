package pl.sunflux.sandbox.domain.world;

public class GeneratorOptions {

    public int seed = 0;
    public AlgorithmType algorithmType = AlgorithmType.PERLIN;

    /**
     * The side of the square in which the graph will be fitted.
     */
    public int graphBounds = 2048;

    /**
     * Number of pieces for the graph.
     */
    public int sitesAmount = 8_000;

    /**
     * Each time a relaxation step is performed, the points are left in a slightly more even distribution:
     * closely spaced points move farther apart, and widely spaced points move closer together.
     */
    public int lloydRelaxations = 2;

    public enum AlgorithmType {
        PERLIN,
        BLOB,
        RADIAL,
        RANDOM
    }
}
