package pipe.core.models.petrinet;

import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.Transition;

public interface TransitionVisitor extends PetriNetComponentVisitor {
   void visit(Transition var1);
}
