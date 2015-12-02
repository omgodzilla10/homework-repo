package shapes;

public class Sphere extends Shape {

    private double radius; //Radius in feet

    public Sphere(double r) {
        super("Sphere");
        radius = r;
    }

    public double area() {
        return 4 * Math.PI * radius * radius;
    }

    public String toString() {
        return super.toString() + " of radius " + radius;
    }
}