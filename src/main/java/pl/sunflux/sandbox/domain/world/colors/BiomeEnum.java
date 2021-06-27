package pl.sunflux.sandbox.domain.world.colors;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public enum BiomeEnum {

    OCEAN("0x44447a"),
    LAKE("0x336699"),
    RIVER("0x225588"),

    BEACH("0xa09077"),
    SNOW("0xffffff"),
    TUNDRA("0xbbbbaa"),
    BARE("0x888888"),
    SCORCHED("0x555555"),
    TAIGA("0x99aa77"),

    SHURBLAND("0x889977"),
    TEMPERATE_DESERT("0xc9d29b"),
    TEMPERATE_RAIN_FOREST("0x448855"),
    TEMPERATE_DECIDUOUS_FOREST("0x679459"),
    GRASSLAND("0x88aa55"),
    SUBTROPICAL_DESERT("0xd2b98b"),
    SHRUBLAND("0x889977"),

    ICE("0x99ffff"),
    MARSH("0x2f6666"),
    TROPICAL_RAIN_FOREST("0x337755"),
    TROPICAL_SEASONAL_FOREST("0x559944"),
    COAST("0x33335a"),
    LAKESHORE("0x225588"),
    ;


    private static Map<String, Color> colorMap = new HashMap<>();
    private String colorCode;

    BiomeEnum(String color) {
        this.colorCode = color;
    }

    public Color getColor() {
        if (!colorMap.containsKey(this.colorCode)) {
            colorMap.put(this.colorCode, Color.web(this.colorCode));
        }

        return colorMap.get(this.colorCode);
    }
}
