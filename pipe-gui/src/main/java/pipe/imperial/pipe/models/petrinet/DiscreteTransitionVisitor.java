package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.DiscreteTransition;
import uk.ac.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface DiscreteTransitionVisitor extends PetriNetComponentVisitor {
   void visit(DiscreteTransition var1);
}
