package q2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Triangle {
    /** Contains the current height of the triangle. */
    double triHeight;

    /** A 2D array that holds the current X and Y coordinates
     * for each of the triangle's three points.  */
    private int[][] coords;

    /** The origin point, where the mouse is first clicked. */
    Point origMousePoint;

    /** The constructor method. */
    public Triangle(Point point) {
        triHeight = 30;
        coords = new int[3][2];

        origMousePoint = point;

        //Set the origin point of the triangle.
        coords[0][0] = point.x;
        coords[0][1] = point.y;

        coords[1][0] = point.x;
        coords[1][1] = point.y;

        coords[2][0] = point.x;
        coords[2][1] = point.y;
    }
    
    public void draw(Graphics page) {
        page.setColor(Color.BLACK);

        //Draw the triangle.
        page.drawLine(coords[0][0], coords[0][1], coords[1][0],
                coords[1][1]);
        page.drawLine(coords[1][0], coords[1][1], coords[2][0],
                coords[2][1]);
        page.drawLine(coords[2][0], coords[2][1], coords[0][0],
                coords[0][1]);
    }
    
    public void scale(Point newPoint) {
        coords[0][0] = newPoint.x;
        coords[0][1] = newPoint.y;


        //Find distance between origin and mouse's point.
        triHeight = Math.pow(origMousePoint.x - newPoint.x, 2);
        triHeight += Math.pow(origMousePoint.y - newPoint.y, 2);
        triHeight = Math.sqrt(triHeight);

        //Find side length of triangle using height.
        double sideLength = triHeight * 2;
        sideLength /= Math.sqrt(3);

        coords[1][0] = (int) (origMousePoint.x + (sideLength / 2));
        coords[2][0] = (int) (origMousePoint.x - (sideLength / 2));


    }

    public void rotate(Point newPoint) {
        //Find offset from origin
        double offsetX = newPoint.x - origMousePoint.x;
        double offsetY = newPoint.y - origMousePoint.y;

        //Find angle between original mousepont and new point.
        double rotAngle = Math.atan2(offsetY, offsetX);

        //Apply the change in angle to the remaining two vertices.
        //Second vertex.
        double bAngle = rotAngle + (Math.toRadians(120));

        //Get the x and y coordinates of point B's new location.
        coords[1][0] = (int) (triHeight * Math.cos(bAngle));
        coords[1][1] = (int) (triHeight * Math.sin(bAngle));

        coords[1][0] += origMousePoint.x;
        coords[1][1] += origMousePoint.y;

        //Apply change to third vertex.
        double cAngle = rotAngle + (Math.toRadians(240));

        //Get the x and y coordinates of point C's new location.
        coords[2][0] = (int) (triHeight * Math.cos(cAngle));
        coords[2][1] = (int) (triHeight * Math.sin(cAngle));

        coords[2][0] += origMousePoint.x;
        coords[2][1] += origMousePoint.y;
    }
}
