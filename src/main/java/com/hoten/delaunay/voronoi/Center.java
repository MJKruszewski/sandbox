package com.hoten.delaunay.voronoi;

import com.hoten.delaunay.geom.Point;
import pl.sunflux.sandbox.domain.world.colors.BiomeEnum;

import java.util.ArrayList;

/**
 * Center.java
 *
 * @author Connor
 */
public class Center {

    public int index;
    public Point loc;
    public ArrayList<Corner> corners = new ArrayList();//good
    public ArrayList<Center> neighbors = new ArrayList();//good
    public ArrayList<Edge> borders = new ArrayList();
    public boolean border, ocean, water, coast;
    public double elevation;
    public double moisture;
    public BiomeEnum biome;
    public double area;

    public Center() {
    }

    public Center(Point loc) {
        this.loc = loc;
    }
}
