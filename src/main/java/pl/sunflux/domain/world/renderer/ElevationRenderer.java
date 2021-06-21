package pl.sunflux.domain.world.renderer;

import pl.sunflux.domain.world.WorldMap;

public class ElevationRenderer implements RendererInterface {
    @Override
    public void apply(WorldMap worldMap) {
        worldMap.getTiles().forEach(tile -> {
            tile.getGraphic().setFill(WorldTileColor.getElevation(tile));
        });
    }
}
