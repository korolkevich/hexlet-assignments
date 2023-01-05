package exercise;

// BEGIN
class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    Flat(double area, double balconyArea, int floor){
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getArea() {
        return this.area + this.balconyArea;
    };

    public int compareTo(Home another) {
        double currentArea = this.area;
        double anotherArea = another.getArea();

        if (currentArea > anotherArea) {
            return 1;
        } else if (currentArea < anotherArea) {
            return -1;
        }
        return 0;
    };

    public String toString() {
        double totalArea = this.area + this.balconyArea;
        return "Квартира площадью " + totalArea + " метров на " + this.floor + " этаже";
    }
}
// END
