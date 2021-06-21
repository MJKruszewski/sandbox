package pl.sunflux.domain.world.generator;

import pl.sunflux.domain.world.GeneratorOptions;
import pl.sunflux.domain.world.Tile;
import pl.sunflux.domain.world.TileType;

import java.util.HashSet;
import java.util.Random;

public class ElevationGenerator implements WorldElementGenerator {
    @Override
    public void generate(HashSet<Tile> tiles, GeneratorOptions generatorOptions) {
        Random random = new Random();

        tiles.stream().filter(tile -> tile.getType() != TileType.WATER).forEach(tile -> {
            int elevation = random.nextInt(10);

            if (elevation == 0) {
                elevation = 1;
            }

            tile.setElevation(elevation);
        });
    }
}
