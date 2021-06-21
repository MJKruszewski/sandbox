package pl.sunflux.domain.world.generator;

import pl.sunflux.domain.world.GeneratorOptions;
import pl.sunflux.domain.world.Tile;

import java.util.HashSet;

public interface WorldElementGenerator {

    public void generate(HashSet<Tile> tiles, GeneratorOptions generatorOptions);
}
