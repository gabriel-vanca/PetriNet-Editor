package pipe.core.models.petrinet;

import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.DiscreteTransition;

public interface DiscreteTransitionVisitor extends PetriNetComponentVisitor {
   void visit(DiscreteTransition var1);
}
