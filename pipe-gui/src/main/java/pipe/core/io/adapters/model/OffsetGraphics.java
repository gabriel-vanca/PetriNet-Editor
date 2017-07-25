package pipe.core.io.adapters.model;

import uk.ac.imperial.pipe.io.adapters.model.Point;

import javax.xml.bind.annotation.XmlElement;

public class OffsetGraphics {
   @XmlElement(
      name = "offset"
   )
   public Point point;

   public OffsetGraphics() {
      super();
   }
}
