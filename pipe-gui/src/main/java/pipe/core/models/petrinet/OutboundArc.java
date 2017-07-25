package pipe.core.models.petrinet;

import java.util.Map;
import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.AbstractArc;
import uk.ac.imperial.pipe.models.petrinet.ArcType;
import uk.ac.imperial.pipe.models.petrinet.ArcVisitor;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.models.petrinet.Transition;

public abstract class OutboundArc extends AbstractArc {
   public OutboundArc(Transition source, Place target, Map tokenWeights, ArcType type) {
      super(source, target, tokenWeights, type);
   }

   public final void accept(PetriNetComponentVisitor visitor) {
      if (visitor instanceof uk.ac.imperial.pipe.models.petrinet.ArcVisitor) {
         ((ArcVisitor)visitor).visit(this);
      }

   }
}
