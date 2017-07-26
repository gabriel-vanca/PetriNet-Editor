package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.models.petrinet.DiscretePlace;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface DiscretePlaceVisitor extends PetriNetComponentVisitor {
   void visit(DiscretePlace var1);
}
