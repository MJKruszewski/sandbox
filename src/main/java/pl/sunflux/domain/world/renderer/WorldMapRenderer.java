package pl.sunflux.domain.world.renderer;

import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Polygon;
import pl.sunflux.domain.world.GeneratorOptions;
import pl.sunflux.domain.world.Tile;
import pl.sunflux.domain.world.WorldMap;
import tinfour.common.IQuadEdge;
import tinfour.common.Vertex;
import tinfour.voronoi.BoundedVoronoiDiagram;

import java.awt.geom.*;
import java.util.List;

public class WorldMapRenderer {
    private AffineTransform af;

    public WorldMapRenderer(GeneratorOptions generatorOptions, BoundedVoronoiDiagram diagram) {
        Rectangle2D sb = diagram.getSampleBounds();
        double w = sb.getWidth();
        double h = sb.getHeight();
        double x = sb.getX();
        double y = sb.getY();

        Rectangle2D bounds = new Rectangle2D.Double(
                x - w * 0.1,
                y - h * 0.1,
                w * 1.2,
                h * 1.2
        );

        double x0 = bounds.getMinX();
        double y0 = bounds.getMinY();
        double x1 = bounds.getMaxX();
        double y1 = bounds.getMaxY();

        this.af = this.initTransform(generatorOptions.width, generatorOptions.height, generatorOptions.padding, x0, x1, y0, y1);
    }


    public void generatePolygon(Tile tile) {
        Polygon polygon = new Polygon();
        Path2D path = new Path2D.Double();
        Point2D p0 = new Point2D.Double();

        List<IQuadEdge> qList = tile.getPolygon().getEdges();

        IQuadEdge q0 = qList.get(0);
        p0.setLocation(q0.getA().getX(), q0.getA().getY());
        af.transform(p0, p0);
        path.moveTo(p0.getX(), p0.getY());

        for (IQuadEdge q : qList) {
            p0.setLocation(q.getB().getX(), q.getB().getY());
            af.transform(p0, p0);

            polygon.getPoints().addAll(p0.getX(), p0.getY());
        }

        path.closePath();
        tile.setGraphic(polygon);
    }

    private void drawLines(Canvas canvas, WorldMap worldMap) {
        Point2D p0 = new Point2D.Double();
        Point2D p1 = new Point2D.Double();
        Line2D l2d = new Line2D.Double();

        boolean[] drawn = new boolean[worldMap.getDiagram().getMaximumEdgeAllocationIndex()];
        for (Tile tile : worldMap.getTiles()) {
            for (IQuadEdge e : tile.getPolygon().getEdges()) {
                if (!drawn[e.getIndex()]) {
                    drawn[e.getIndex()] = true;
                    drawn[e.getIndex() ^ 0x01] = true;
                    Vertex A = e.getA();
                    Vertex B = e.getB();

                    p0.setLocation(A.getX(), A.getY());
                    p1.setLocation(B.getX(), B.getY());
                    af.transform(p0, p0);
                    af.transform(p1, p1);
                    l2d.setLine(p0, p1);

                    canvas.getGraphicsContext2D().strokeLine(l2d.getX1(), l2d.getY1(), l2d.getX2(), l2d.getY2());
                }
            }
        }

    }

    /**
     * Initializes an affine transform for Cartesian coordinates from a TIN to
     * pixel coordinates for rendering an image.
     *
     * @param width  the width of the output image
     * @param height the height of the output image
     * @param pad    spacing around edge of image
     * @param x0     the x coordinate of the left side of the data area
     * @param x1     the x coordinate of the right side of the data area
     * @param y0     the y coordinate of the lower side of the data area
     * @param y1     the y coordinate of the upper side of the data area
     * @return if successful, a valid instance.
     */
    private AffineTransform initTransform(
            int width,
            int height,
            int pad,
            double x0, double x1, double y0, double y1) {
        // The goal is to create an AffineTransform that will map coordinates
        // from the source data to image (pixel) coordinates).  We wish to
        // render the rectangle defined by the source coordinates on an
        // image with as large a scale as possible.  However, the shapes of
        // the image and the data rectangles may be different (one may be
        // wide and one may be narrow).  So we need to use the aspect ratios
        // of the two rectangles to determine whether to fit it to the
        // vertical axis or to fit it to the horizontal.

        double rImage = (double) (width - pad) / (double) (height - pad);
        double rData = (x1 - x0) / (y1 - y0);
        double rAspect = rImage / rData;

        double uPerPixel; // units of distance per pixel
        if (rAspect >= 1) {
            // the shape of the image is fatter than that of the
            // data, the limiting factor is the vertical extent
            uPerPixel = (y1 - y0) / (height - pad);
        } else { // r<1
            // the shape of the image is skinnier than that of the
            // data, the limiting factor is the horizontal extent
            uPerPixel = (x1 - x0) / (width - pad);
        }
        double scale = 1.0 / uPerPixel;

        double xCenter = (x0 + x1) / 2.0;
        double yCenter = (y0 + y1) / 2.0;
        double xOffset = (width - pad) / 2 - scale * xCenter + pad / 2;
        double yOffset = (height - pad) / 2 + scale * yCenter + pad / 2;

        AffineTransform transform;
        transform = new AffineTransform(scale, 0, 0, -scale, xOffset, yOffset);

        try {
            transform.createInverse();
        } catch (NoninvertibleTransformException ex) {
            throw new IllegalArgumentException(
                    "Input elements result in a degenerate transform: " + ex.getMessage(),
                    ex);
        }
        return transform;
    }
}
