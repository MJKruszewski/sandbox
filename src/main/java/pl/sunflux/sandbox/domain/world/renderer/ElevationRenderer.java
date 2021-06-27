package pl.sunflux.sandbox.domain.world.renderer;

import pl.sunflux.sandbox.domain.world.colors.ElevationEnum;
import pl.sunflux.sandbox.domain.world.components.Tile;

import java.util.Collection;

public class ElevationRenderer implements RendererInterface {

    @Override
    public void apply(Collection<Tile> tiles) {

        tiles.forEach(tile -> {
            if (tile.center.ocean) {
                tile.setColor(ElevationEnum.OCEAN.getColor());

                return;
            }

            if (tile.center.elevation < 0.2) {
                tile.setColor(ElevationEnum.LOW.getColor());
            } else if (tile.center.elevation < 0.4) {
                tile.setColor(ElevationEnum.MEDIUM_MIN.getColor());
            } else if (tile.center.elevation < 0.6) {
                tile.setColor(ElevationEnum.MEDIUM.getColor());
            } else if (tile.center.elevation < 0.7) {
                tile.setColor(ElevationEnum.MEDIUM_MAX.getColor());
            } else if (tile.center.elevation < 0.8) {
                tile.setColor(ElevationEnum.HIGH_MIN.getColor());
            } else {
                tile.setColor(ElevationEnum.HIGH_MAX.getColor());
            }
        });
    }
}
