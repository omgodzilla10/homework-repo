package shapes;

public class Cylinder extends Shape {

    private double height;
    private double radius;

    public Cylinder(double height, double radius) {
        super("Cylinder");
        this.height = height;
        this.radius = radius;
    }

    public double area() {
        return (2 * Math.PI * Math.pow(radius, 2)) + (height * Math.PI * radius * 2);
    }
}
