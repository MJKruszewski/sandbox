package pl.sunflux.sandbox.domain.world.colors;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public enum ElevationEnum {

    OCEAN("0000bf"),
    LOW("1ffdd8"),

    MEDIUM_MIN("76fa71"),
    MEDIUM("c8ff39"),
    MEDIUM_MAX("f2d609"),

    HIGH_MIN("ff7500"),
    HIGH_MAX("f60001");

    private static Map<String, Color> colorMap = new HashMap<String, Color>() {{
        put("0000bf", Color.web("0000bf"));
        put("1ffdd8", Color.web("1ffdd8"));
        put("76fa71", Color.web("76fa71"));
        put("c8ff39", Color.web("c8ff39"));
        put("f2d609", Color.web("f2d609"));
        put("ff7500", Color.web("ff7500"));
        put("f60001", Color.web("f60001"));
    }};
    private String colorCode;

    ElevationEnum(String color) {
        this.colorCode = color;
    }

    public Color getColor() {
        if (!colorMap.containsKey(this.colorCode)) {
            colorMap.put(this.colorCode, Color.web(this.colorCode));
        }

        return colorMap.get(this.colorCode);
    }
}