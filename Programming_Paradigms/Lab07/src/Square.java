public class Square extends Rectangle {

    public Square(double edgeA) {
        super(edgeA, edgeA);
    }

    @Override
    public String getShape() {
        return "Square";
    }
}
