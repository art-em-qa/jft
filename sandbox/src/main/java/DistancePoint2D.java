public class DistancePoint2D {
    public static void main(String[] args) {
        Point p1 = new Point (1, 2);
        Point p2 = new Point (2,4);
        System.out.println((distance(p1,p2)));
    }
    public static double distance(Point p1, Point p2){
        return Math.sqrt((p2.x - p1.x)*(p2.x - p1.x) + (p2.y - p1.y)*(p2.y - p1.y));
    }
}
