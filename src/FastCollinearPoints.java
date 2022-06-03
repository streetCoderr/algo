import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

public class FastCollinearPoints {
   
   private Point[] sortPts;
   private LineSegment[] lineSegs;
   public FastCollinearPoints(Point[] points) {
       // finds all line segments containing 4 or more points
       if (points == null) throw new IllegalArgumentException();
       sortPts = new Point[points.length];
     
       if (points[0] == null) throw new IllegalArgumentException();
       for (int i = 0; i < points.length; i++) {
           sortPts[i] = points[i];
           for (int j = i+1; j < points.length; j++) {
               if (i == 0 && points[j] == null) throw new IllegalArgumentException();
               if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
           }
       }
       
       Arrays.sort(sortPts, new NaturalOrder());
       ArrayList<LineSegment> lst = new ArrayList<LineSegment>();
       Point[] pts;
       for (int i = 0; i < points.length; i++) {
           pts = sortPts.clone();
           Arrays.sort(pts, sortPts[i].slopeOrder());
           
           double slopeToPoint = Double.NEGATIVE_INFINITY;
           Point minP = pts[0];
           Point maxP = pts[0];
           int count = 0;
           for (int j = 1; j < pts.length; j++) {
               double slope = sortPts[i].slopeTo(pts[j]);
               if (slope == slopeToPoint) {
                   count++;
                   if (j == pts.length - 1 && count > 2) {
                       maxP = maxPoint(pts[0], pts[j]);
                       if (pts[0] == minP) lst.add(new LineSegment(minP, maxP));
                   }
               } else {
                   maxP = maxPoint(pts[0], pts[j-1]);
                   if (count > 2) {
                       if (pts[0] == minP) lst.add(new LineSegment(minP, maxP));
                   }
                   count = 1;
                   slopeToPoint = slope;
                   minP = minPoint(pts[0], pts[j]);
               }
           }
       }
       lineSegs = new LineSegment[lst.size()];
       for (int i = 0; i < lineSegs.length; i++) {
           lineSegs[i] = lst.get(i);
       }
   }
   
   private Point maxPoint(Point i, Point j) {
       if (i.compareTo(j) > 0) return i;
       else return j;
   }

   private Point minPoint(Point i, Point j) {
       if (i.compareTo(j) < 0) return i;
       else return j;
   }
   
   private class NaturalOrder implements Comparator<Point> {
       public int compare(Point o1, Point o2) {
           return o1.compareTo(o2);
       }
   }
   public int numberOfSegments() {
       // the number of line segments
       return lineSegs.length;
   }
   public LineSegment[] segments() {
       // the line segments
       return lineSegs.clone();
   }
  
}