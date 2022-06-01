import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
   
   private Point[] pts;
   private LineSegment[] lineSegs;
   public FastCollinearPoints(Point[] points) {
       // finds all line segments containing 4 or more points
       if (points == null) throw new IllegalArgumentException();
       pts = new Point[points.length];
     
       for (int i = 0; i < points.length-1; i++) {
           if (points[i] == null) throw new IllegalArgumentException();
           pts[i] = points[i];
           for (int j = i+1; j < points.length; j++) {
               if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
           }
       }
       
       ArrayList<LineSegment> lst = new ArrayList<LineSegment>();
       for (int i = 0; i < pts.length; i++) {
           Arrays.sort(points, pts[i].slopeOrder());
           double slopeToPoint = Double.NEGATIVE_INFINITY;
           Point minP = points[0];
           Point maxP = points[0];
           int count = 0;
           for (int j = 1; j < pts.length; j++) {
               double slope = points[0].slopeTo(points[j]);
               if (slope != slopeToPoint || j == pts.length-1) {
                   slopeToPoint = slope;
                   if (count > 2) {
                       LineSegment l = new LineSegment(minP, maxP);
                       for (int k = 0; k < lst.size(); k++) {
                           if (lst.get(k).toString().equals(l.toString())) break;
                           if (k == lst.size() - 1) lst.add(l);
                       }
                   }
                   count = 0;
                   minP = points[0];
                   maxP = points[0];
               } else {
                   count++;
                   if (points[j].compareTo(minP) < 0) minP = points[j];
                   if (points[j].compareTo(maxP) > 0) maxP = points[j];
               }
               
           }
       }
       lineSegs = new LineSegment[lst.size()];
       for (int i = 0; i < lineSegs.length; i++) {
           lineSegs[i] = lst.get(i);
       }
   }
   public int numberOfSegments() {
       // the number of line segments
       return lineSegs.length;
   }
   public LineSegment[] segments() {
       // the line segments
       return lineSegs;
   }
}