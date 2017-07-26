package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.models.petrinet.Transition;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface TransitionVisitor extends PetriNetComponentVisitor {
   void visit(Transition var1);
}
