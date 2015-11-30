package q2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Triangle {

    double triHeight;

    /** A 2D array that holds the current X and Y coordinates
     * for each of the triangle's three points.  */
    private int[][] coords;

    private Point tempOrigin;
    
    public Triangle(Point point) {
        triHeight = 0;
        tempOrigin = new Point(200, 200);
        coords = new int[3][2];

        //Set the origin point of the triangle.
        coords[0][0] = tempOrigin.x;
        coords[0][1] = tempOrigin.y;

        coords[1][0] = tempOrigin.x;
        coords[1][1] = tempOrigin.y;

        coords[2][0] = tempOrigin.x;
        coords[2][1] = tempOrigin.y;
    }
    
    public void draw(Graphics page) {
        System.out.println("Triangle draw called!");
        page.setColor(Color.BLACK);
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
        triHeight = Math.pow(tempOrigin.x - newPoint.x, 2);
        triHeight += Math.pow(tempOrigin.y - newPoint.y, 2);
        triHeight = Math.sqrt(triHeight);

        //Find side length of triangle using height.
        double sideLength = triHeight * 2;
        sideLength /= Math.sqrt(3);

        coords[1][0] = (int) (tempOrigin.x + (sideLength / 2));
        coords[2][0] = (int) (tempOrigin.x - (sideLength / 2));

        System.out.println("Triangle height: " + triHeight);
    }

    public void rotate(Point newPoint) {
        //Used to store the amount that the new point is offset by.
        double offsetX = 0;
        double offsetY = 0;

        //The length of the line between the old point and the new point.
        double offsetLength = 0;

        offsetX = newPoint.x - coords[0][0];
        offsetY = newPoint.y - coords[0][1];

        offsetLength = Math.pow(offsetX, 2);
        offsetLength += Math.pow(offsetY, 2);
        offsetLength = Math.sqrt(offsetLength);

        //Find the new height of the triangle;
        double newTriHeight = Math.pow((offsetX - coords[0][0]) + tempOrigin.x, 2);
        newTriHeight += Math.pow((offsetY - coords[0][1]) + tempOrigin.y, 2);
        newTriHeight = Math.sqrt(newTriHeight);

        /* Find angle between old point and new point around the origin
           using the cos law. */
        double rotAngle = Math.pow(offsetLength, 2);
        rotAngle -= Math.pow(triHeight, 2);
        rotAngle -= Math.pow(newTriHeight, 2);
        rotAngle /= (-2) * triHeight * newTriHeight;
        rotAngle = Math.acos(rotAngle);

        System.out.println("New tri height: " + newTriHeight);
        System.out.println("Rot around origin: " + Math.toDegrees(rotAngle));

        //Initialize and declare rotation matrix using angle found.
        double[][] rotMatrix = new double[2][2];
        rotMatrix[0][0] = Math.cos(rotAngle);
        rotMatrix[0][1] = -(Math.sin(rotAngle));
        rotMatrix[1][0] = Math.sin(rotAngle);
        rotMatrix[1][1] = Math.cos(rotAngle);

        /* Multiply remaining the two vertices with rotation matrix
        (The first vertex is excluded because it's position is already
        tied to the mouse position.) */

        //First vertex.
        double newXCoord = rotMatrix[0][0] * coords[1][0];
        newXCoord += (rotMatrix[0][1] * coords[1][1]);

        double newYCoord = rotMatrix[1][0] * coords[1][0];
        newYCoord += (rotMatrix[1][1] * coords[1][1]);

        coords[1][0] = (int) newXCoord;
        coords[1][1] = (int) newYCoord;

        //Second vertex.
        newXCoord = rotMatrix[0][0] * coords[2][0];
        newXCoord += (rotMatrix[0][1] * coords[2][1]);

        newYCoord = rotMatrix[1][0] * coords[2][0];
        newYCoord += (rotMatrix[1][1] * coords[2][1]);

        coords[2][0] = (int) newXCoord;
        coords[2][1] = (int) newYCoord;
    }
}
