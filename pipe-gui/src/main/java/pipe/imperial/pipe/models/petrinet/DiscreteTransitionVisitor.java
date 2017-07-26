package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.models.petrinet.DiscreteTransition;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface DiscreteTransitionVisitor extends PetriNetComponentVisitor {
   void visit(DiscreteTransition var1);
}
