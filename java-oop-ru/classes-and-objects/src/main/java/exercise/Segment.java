package exercise;

// BEGIN
class Segment {
    private Point start;
    private Point end;

    Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getBeginPoint() {
        return start;
    }

    public Point getMidPoint() {
        int midX = (this.start.getX() + this.end.getX()) / 2;
        int midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }
}
// END
