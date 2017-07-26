package pipe.gui.imperial.pipe.models.petrinet;

import java.util.Map;

import pipe.gui.imperial.pipe.models.petrinet.AbstractArc;
import pipe.gui.imperial.pipe.models.petrinet.ArcType;
import pipe.gui.imperial.pipe.models.petrinet.ArcVisitor;
import pipe.gui.imperial.pipe.models.petrinet.Place;
import pipe.gui.imperial.pipe.models.petrinet.Transition;
import pipe.gui.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public abstract class OutboundArc extends AbstractArc {
   public OutboundArc(Transition source, Place target, Map tokenWeights, ArcType type) {
      super(source, target, tokenWeights, type);
   }

   public final void accept(PetriNetComponentVisitor visitor) {
      if (visitor instanceof pipe.gui.imperial.pipe.models.petrinet.ArcVisitor) {
         ((ArcVisitor)visitor).visit(this);
      }

   }
}
