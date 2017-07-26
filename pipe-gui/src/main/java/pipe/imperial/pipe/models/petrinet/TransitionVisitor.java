package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.Transition;
import uk.ac.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface TransitionVisitor extends PetriNetComponentVisitor {
   void visit(Transition var1);
}
