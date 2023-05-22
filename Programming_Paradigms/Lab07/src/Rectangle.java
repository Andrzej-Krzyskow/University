public class Rectangle implements ShapeInterface {

    private final double edgeA, edgeB;

    public Rectangle(double edgeA, double edgeB) {
        this.edgeA = edgeA;
        this.edgeB = edgeB;
    }

    @Override
    public double getPerimeter() {
        return 2*edgeA+2*edgeB;
    }

    @Override
    public double getArea() {
        return edgeA*edgeB;
    }

    @Override
    public String getShape() {
        return "Rectangle";
    }

    public double getEdgeA() {
        return edgeA;
    }

    public double getEdgeB() {
        return edgeB;
    }
}
