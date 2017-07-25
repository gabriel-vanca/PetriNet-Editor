package pipe.core.io.adapters.model;

import uk.ac.imperial.pipe.io.adapters.model.NameDetails;
import uk.ac.imperial.pipe.io.adapters.model.PositionGraphics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AdaptedConnectable {
   @XmlElement
   private uk.ac.imperial.pipe.io.adapters.model.PositionGraphics graphics;
   @XmlAttribute
   private String id;
   @XmlElement(
      name = "name"
   )
   private uk.ac.imperial.pipe.io.adapters.model.NameDetails name = new uk.ac.imperial.pipe.io.adapters.model.NameDetails();

   public AdaptedConnectable() {
      super();
   }

   public final String getId() {
      return this.id;
   }

   public final void setId(String id) {
      this.id = id;
   }

   public final uk.ac.imperial.pipe.io.adapters.model.PositionGraphics getGraphics() {
      return this.graphics;
   }

   public final void setGraphics(PositionGraphics graphics) {
      this.graphics = graphics;
   }

   public final uk.ac.imperial.pipe.io.adapters.model.NameDetails getName() {
      return this.name;
   }

   public final void setNameDetails(NameDetails name) {
      this.name = name;
   }
}
