package shapes;

abstract public class Shape {
    private String shapeName;

    public Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    public String toString() {
        return shapeName;
    }

    abstract double area();
}
