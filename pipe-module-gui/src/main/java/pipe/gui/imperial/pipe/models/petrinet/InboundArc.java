package pipe.gui.imperial.pipe.models.petrinet;

import java.util.Map;

import pipe.gui.imperial.pipe.models.petrinet.ArcType;
import pipe.gui.imperial.pipe.models.petrinet.ArcVisitor;
import pipe.gui.imperial.pipe.models.petrinet.Place;
import pipe.gui.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public abstract class InboundArc extends AbstractArc {
   public InboundArc(Place source, Transition target, Map tokenWeights, ArcType type) {
      super(source, target, tokenWeights, type);
   }

   public final void accept(PetriNetComponentVisitor visitor) {
      if (visitor instanceof pipe.gui.imperial.pipe.models.petrinet.ArcVisitor) {
         ((ArcVisitor)visitor).visit(this);
      }

   }
}
