package pl.sunflux.domain.world.renderer;

import javafx.scene.paint.Color;
import pl.sunflux.domain.world.Tile;

public class WorldTileColor {
    public static Color getLandColor(Tile tile) {
        switch (tile.getType()) {
            case WATER:
                return Color.BLUE;
            case LAND:
                return Color.BROWN;
            default:
                return Color.BLUE;
        }
    }

    public static Color getElevation(Tile tile) {
        if (tile.getElevation() == 0) {
            return Color.BLUE;
        }

        return Color.rgb(tile.getElevation() * 25, 255, tile.getElevation() * 25);
    }
}

