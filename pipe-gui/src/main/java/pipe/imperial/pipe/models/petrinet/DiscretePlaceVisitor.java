package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.DiscretePlace;
import uk.ac.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface DiscretePlaceVisitor extends PetriNetComponentVisitor {
   void visit(DiscretePlace var1);
}
