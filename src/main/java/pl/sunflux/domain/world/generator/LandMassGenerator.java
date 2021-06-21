package pl.sunflux.domain.world.generator;

import pl.sunflux.domain.world.GeneratorOptions;
import pl.sunflux.domain.world.Tile;
import pl.sunflux.domain.world.TileType;
import pl.sunflux.utils.GameRandom;

import java.util.HashSet;
import java.util.Random;

public class LandMassGenerator implements WorldElementGenerator {

    private static int landMassCounter = 0;

    @Override
    public void generate(HashSet<Tile> tiles, GeneratorOptions generatorOptions) {
        Tile starter = GameRandom.getRandomElement(tiles);
        starter.setType(TileType.LAND);

        if (starter.getNeighbours().size() <= 0) {
            return;
        }

        landMassCounter = 1;
        rec(starter, generatorOptions);
    }

    private static void rec(Tile root, GeneratorOptions generatorOptions) {
        int totalItems = new Random().nextInt(root.getNeighbours().size());

        if (root.getNeighbours().size() <= 0 || totalItems <= 0) {
            return;
        }

        for (Tile tile : GameRandom.getRandomElement(root.getNeighbours(), totalItems)) {
            if (generatorOptions.maxLand < (landMassCounter / (double) generatorOptions.nVertices)) {
                continue;
            }

            if (tile.getType() == TileType.LAND) {
                rec(tile, generatorOptions);
                continue;
            }

            tile.setType(TileType.LAND);
            landMassCounter++;

            if (tile.getNeighbours().size() <= 0) {
                continue;
            }

            rec(tile, generatorOptions);
        }
    }

}
