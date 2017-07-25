package pipe.core.models.petrinet;

import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.DiscretePlace;

public interface DiscretePlaceVisitor extends PetriNetComponentVisitor {
   void visit(DiscretePlace var1);
}
