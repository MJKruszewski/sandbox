package pl.sunflux.sandbox.domain.world.renderer;

import pl.sunflux.sandbox.domain.world.components.Tile;
import pl.sunflux.sandbox.state.WorldMap;

import java.util.Collection;

public class BiomeRenderer implements RendererInterface {

    @Override
    public void apply(Collection<Tile> tiles) {
        tiles.forEach(tile -> tile.setColor(WorldMap.diagram.getColor(tile.center.biome)));
    }
}
