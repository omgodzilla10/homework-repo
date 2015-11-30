package q2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Triangle {
    /** A 2D array that holds the current X and Y coordinates
     * for each of the triangle's three points.  */
    private int[][] coords;

    private Point tempOrigin;
    
    public Triangle(Point point) {
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
        double triHeight = Math.pow(tempOrigin.x - newPoint.x, 2);
        triHeight += Math.pow(tempOrigin.y - newPoint.y, 2);
        triHeight = Math.sqrt(triHeight);

        //Find side length of triangle using height.
        double sideLength = triHeight * 2;
        sideLength /= Math.sqrt(3);

        coords[1][0] = (int) (tempOrigin.x + (sideLength / 2));
        coords[2][0] = (int) (tempOrigin.x - (sideLength / 2));

        System.out.println("Triangle height: " + triHeight);
    }

    public void rotate(Point rotPoint) {

    }
}
