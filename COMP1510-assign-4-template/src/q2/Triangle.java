package q2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * <p>
 * This class represents the Triangle object, and keeps
 * track of each of the coordinates for each vertex
 * of the triangle, as well as handles any scaling
 * or rotating of the triangle.
 * </p>
 *
 * @author Trevor Hoefsloot
 * @version 1.0
 */

public class Triangle {
    /** The number of vertices in the triangle. */
    static final int NUM_VERTS = 3;

    /** The number of coordinate points in each vertex. */
    static final int NUM_COORDS = 2;

    /** Used to raise values to the exponent two. */
    static final int SQUARE = 2;

    /** Contains the current height of the triangle. */
    private double triHeight;

    /** The length of each of the sides of the triangle. */
    private double sideLength;

    /** A 2D array that holds the current X and Y coordinates
     * for each of the triangle's three points.  */
    private int[][] coords;

    /** The origin point, where the mouse is first clicked. */
    private Point origMousePoint;

    /** The constructor method.
     * @param point the mouse's position.
     * */
    public Triangle(Point point) {
        triHeight = 0;
        sideLength = 0;

        /* Initialize the array that will hold each
        vertex's coordinate. */
        coords = new int[NUM_VERTS][NUM_COORDS];

        //Set the origin point.
        origMousePoint = point;

        //Set all vertices to the origin.
        for (int i = 0; i < NUM_VERTS; i++) {
            coords[i][0] = point.x;
            coords[i][1] = point.y;
        }
    }

    /**
     * This method is called when the triangle
     * needs to be redrawn.
     *
     * @param page the Graphics component.
     */
    public void draw(Graphics page) {
        page.setColor(Color.BLACK);

        //Draw lines between each vertex.
        for (int i = 0; i < NUM_VERTS; i++) {

            if (i != NUM_VERTS - 1) {
                page.drawLine(coords[i][0], coords[i][1],
                        coords[i + 1][0], coords[i + 1][1]);

            } else {
                //Loop back around the array, draw a line to first vertex.
                page.drawLine(coords[i][0], coords[i][1],
                        coords[0][0], coords[0][1]);
            }
        }
    }

    /**
     * This method is called when the mouse is
     * dragged, and is reponsible for calling
     * the appropriate methods for transforming
     * and rotating the triangle.
     *
     * @param newPoint the mouse's position.
     */
    public void transform(Point newPoint) {
        scale(newPoint);
        rotate(newPoint);
    }

    /**
     * This method is responsible for scaling the triangle.
     *
     * @param newPoint the mouse's position.
     */
    public void scale(Point newPoint) {
        /* Set the x and y coordinates of the first vertex to
        be located at the mouse point. */
        coords[0][0] = newPoint.x;
        coords[0][1] = newPoint.y;

        //Find the distance between origin and the mouse's point.
        triHeight = Math.pow(origMousePoint.x - newPoint.x, SQUARE);
        triHeight += Math.pow(origMousePoint.y - newPoint.y, SQUARE);
        triHeight = Math.sqrt(triHeight);

        //Find the side length of the triangle using height.
        sideLength = triHeight * SQUARE;
        sideLength /= Math.sqrt(NUM_VERTS);

        /* Set the new positions of each vertex (excluding the vertex
        being dragged by the mouse) after being scaled. */
        for (int i = 1; i > NUM_VERTS; i++) {
            coords[i][0] = (int) (origMousePoint.x + (sideLength / NUM_COORDS));
            coords[i][0] = (int) (origMousePoint.x - (sideLength / NUM_COORDS));
        }
    }

    /**
     * This method is responsible for rotating the triangle.
     *
     * @param newPoint the mouse's position.
     */
    public void rotate(Point newPoint) {
        //Find the offset from the origin.
        double offsetX = newPoint.x - origMousePoint.x;
        double offsetY = newPoint.y - origMousePoint.y;

        //Find angle between original mouse point and new point.
        double rotAngle = Math.atan2(offsetY, offsetX);

        /* Find the starting position of the two vertices, excluding
        the one being dragged by the mouse.. */
        double bAngle = rotAngle + (SQUARE * Math.PI / NUM_VERTS);
        double cAngle = rotAngle + (SQUARE * (SQUARE * Math.PI / NUM_VERTS));

        //Apply change in angle to each vertex.
        coords[1][0] = (int) (triHeight * Math.cos(bAngle));
        coords[1][1] = (int) (triHeight * Math.sin(bAngle));

        coords[2][0] = (int) (triHeight * Math.cos(cAngle));
        coords[2][1] = (int) (triHeight * Math.sin(cAngle));

        //Readjust each vertex according to the origin.
        coords[1][0] += origMousePoint.x;
        coords[1][1] += origMousePoint.y;

        coords[2][0] += origMousePoint.x;
        coords[2][1] += origMousePoint.y;
    }
}
