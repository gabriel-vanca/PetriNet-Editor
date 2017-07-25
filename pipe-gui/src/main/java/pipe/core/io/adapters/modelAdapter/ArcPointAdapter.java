package pipe.core.io.adapters.modelAdapter;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import uk.ac.imperial.pipe.io.adapters.model.AdaptedArcPoint;
import pipe.core.models.petrinet.ArcPoint;

public class ArcPointAdapter extends XmlAdapter {
   public ArcPointAdapter() {
      super();
   }

   public ArcPoint unmarshal(AdaptedArcPoint adaptedArcPoint) {
      Point2D point = new Double(adaptedArcPoint.getX(), adaptedArcPoint.getY());
      return new ArcPoint(point, adaptedArcPoint.isCurved());
   }

   public AdaptedArcPoint marshal(ArcPoint arcPoint) {
      AdaptedArcPoint adaptedArcPoint = new AdaptedArcPoint();
      adaptedArcPoint.setX((double)arcPoint.getX());
      adaptedArcPoint.setY((double)arcPoint.getY());
      adaptedArcPoint.setCurved(arcPoint.isCurved());
      return adaptedArcPoint;
   }
}
