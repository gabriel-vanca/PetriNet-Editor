package pipe.imperial.pipe.io.adapters.modelAdapter;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public final class PointAdapter extends XmlAdapter {
   public Point2D unmarshal(PointAdapter.AdaptedPoint adaptedPoint) {
      return new Double(adaptedPoint.x, adaptedPoint.y);
   }

   public PointAdapter.AdaptedPoint marshal(Point2D point2D) {
      PointAdapter.AdaptedPoint adaptedPoint = new PointAdapter.AdaptedPoint();
      adaptedPoint.x = point2D.getX();
      adaptedPoint.y = point2D.getY();
      return adaptedPoint;
   }

   public static class AdaptedPoint {
      @XmlAttribute
      public double x;
      @XmlAttribute
      public double y;
   }
}
