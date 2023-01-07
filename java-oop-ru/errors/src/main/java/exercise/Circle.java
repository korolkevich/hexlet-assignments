package exercise;

// BEGIN
class Circle {
    private Point midPoint;
    private int radius;

    public Circle(Point midPoint, int radius) {
        this.midPoint = midPoint;
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }
    public double getSquare() throws NegativeRadiusException {
        if (this.radius < 0) {
            throw new NegativeRadiusException("Negative radius" + this.radius);
        }
        return Math.PI * this.radius * this.radius;
    }
}
// END
