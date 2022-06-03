import java.util.ArrayList;

public class BruteCollinearPoints {
   private LineSegment[] ls;
   public BruteCollinearPoints(Point[] points) {
       // finds all line segments containing 4 points
       if (points == null) throw new IllegalArgumentException();
       if (points[0] == null) throw new IllegalArgumentException();
       for (int i = 0; i < points.length; i++) {
           for (int j = i+1; j < points.length; j++) {
               if (i == 0 && points[j] == null) throw new IllegalArgumentException();
               if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
           }
       }
       ArrayList<LineSegment> lst = new ArrayList<LineSegment>();
       for (int i = 0; i < points.length; i++) {
           for (int j = i+1; j < points.length; j++) {
               for (int k = j+1; k < points.length; k++) {
                   for (int l = k+1; l < points.length; l++) {
                       double lSlope = points[i].slopeTo(points[l]);
                       if (points[i].slopeTo(points[j]) == lSlope && points[i].slopeTo(points[k]) == lSlope) {
                           Point minP = points[i], maxP = points[i];
                           Point[] otherPoints = new Point[] { points[j], points[k], points[l] };
                           for (int x = 0; x < otherPoints.length; x++) {
                               if (otherPoints[x].compareTo(minP) < 0) minP = otherPoints[x];
                               if (otherPoints[x].compareTo(maxP) > 0) maxP = otherPoints[x];
                           }
                           lst.add(new LineSegment(minP, maxP));
                       }
                   }
               }
           }
       }
       
       ls = new LineSegment[lst.size()];
       for (int i = 0; i < ls.length; i++) {
           ls[i] = lst.get(i);
       }
       
       
   }
   public int numberOfSegments() {
       // the number of line segments
       return ls.length;
   }
   public LineSegment[] segments() {
       // the line segments
       return ls.clone();
   }
}