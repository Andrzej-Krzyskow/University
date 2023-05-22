public class Circle implements ShapeInterface{

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String getShape() {
        return "Circle";
    }

    public double getRadius() {
        return radius;
    }
}
