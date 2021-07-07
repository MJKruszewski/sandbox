package pl.sunflux.sandbox.ui.components;

import javafx.beans.property.SimpleStringProperty;
import pl.sunflux.sandbox.domain.world.components.Tile;

public class TileInfo {
    public SimpleStringProperty title;
    public SimpleStringProperty value;

    TileInfo(String title, String value) {
        this.title = new SimpleStringProperty(title);
        this.value = new SimpleStringProperty(value);
    }

    public static TileInfo[] getInstance(Tile tile) {
        return new TileInfo[]{
                new TileInfo("index", String.valueOf(tile.center.index)),
                new TileInfo("biome", tile.center.biome.toString()),
                new TileInfo("ocean", String.valueOf(tile.center.ocean)),
                new TileInfo("area", String.valueOf(tile.center.area)),
                new TileInfo("moisture", String.valueOf(tile.center.moisture)),
                new TileInfo("elevation", String.valueOf(tile.center.elevation))
        };
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }
}
