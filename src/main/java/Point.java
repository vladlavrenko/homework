public class Point {

    double x;
    double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    double distance(Point a, Point b) {
       return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }
}