package com.hoten.delaunay.examples;

import com.hoten.delaunay.voronoi.Center;
import com.hoten.delaunay.voronoi.VoronoiGraph;
import com.hoten.delaunay.voronoi.groundshapes.HeightAlgorithm;
import com.hoten.delaunay.voronoi.nodename.as3delaunay.Voronoi;
import javafx.scene.paint.Color;
import pl.sunflux.sandbox.domain.world.colors.BiomeEnum;

import java.util.Random;

public class GraphImpl extends VoronoiGraph {


    public GraphImpl(Voronoi v, int numLloydRelaxations, Random r, HeightAlgorithm algorithm) {
        super(v, numLloydRelaxations, r, algorithm);
        OCEAN = BiomeEnum.OCEAN.getColor();
        ;
        LAKE = BiomeEnum.LAKE.getColor();
        ;
        BEACH = BiomeEnum.BEACH.getColor();
        ;
        RIVER = BiomeEnum.RIVER.getColor();
        ;
    }

    @Override
    public Color getColor(BiomeEnum biome) {
        return biome.getColor();
    }

    @Override
    public BiomeEnum getBiome(Center p) {
        if (p.ocean) {
            return BiomeEnum.OCEAN;
        } else if (p.water) {
            if (p.elevation < 0.1) {
                return BiomeEnum.MARSH;
            }
            if (p.elevation > 0.8) {
                return BiomeEnum.ICE;
            }
            return BiomeEnum.LAKE;
        } else if (p.coast) {
            return BiomeEnum.BEACH;
        } else if (p.elevation > 0.8) {
            if (p.moisture > 0.50) {
                return BiomeEnum.SNOW;
            } else if (p.moisture > 0.33) {
                return BiomeEnum.TUNDRA;
            } else if (p.moisture > 0.16) {
                return BiomeEnum.BARE;
            } else {
                return BiomeEnum.SCORCHED;
            }
        } else if (p.elevation > 0.6) {
            if (p.moisture > 0.66) {
                return BiomeEnum.TAIGA;
            } else if (p.moisture > 0.33) {
                return BiomeEnum.SHRUBLAND;
            } else {
                return BiomeEnum.TEMPERATE_DESERT;
            }
        } else if (p.elevation > 0.3) {
            if (p.moisture > 0.83) {
                return BiomeEnum.TEMPERATE_RAIN_FOREST;
            } else if (p.moisture > 0.50) {
                return BiomeEnum.TEMPERATE_DECIDUOUS_FOREST;
            } else if (p.moisture > 0.16) {
                return BiomeEnum.GRASSLAND;
            } else {
                return BiomeEnum.TEMPERATE_DESERT;
            }
        } else {
            if (p.moisture > 0.66) {
                return BiomeEnum.TROPICAL_RAIN_FOREST;
            } else if (p.moisture > 0.33) {
                return BiomeEnum.TROPICAL_SEASONAL_FOREST;
            } else if (p.moisture > 0.16) {
                return BiomeEnum.GRASSLAND;
            } else {
                return BiomeEnum.SUBTROPICAL_DESERT;
            }
        }
    }
}
