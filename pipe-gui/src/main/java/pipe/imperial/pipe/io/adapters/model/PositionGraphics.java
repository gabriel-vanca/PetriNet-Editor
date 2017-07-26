package pipe.imperial.pipe.io.adapters.model;

import uk.ac.imperial.pipe.io.adapters.model.Point;

import javax.xml.bind.annotation.XmlElement;

public class PositionGraphics {
   @XmlElement(
      name = "position"
   )
   public Point point;
}
