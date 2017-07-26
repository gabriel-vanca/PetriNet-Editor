package pipe.imperial.pipe.visitor;

import uk.ac.imperial.pipe.models.petrinet.DiscreteTransition;
import uk.ac.imperial.pipe.models.petrinet.DiscreteTransitionVisitor;
import uk.ac.imperial.pipe.models.petrinet.Transition;

public final class TransitionCloner implements DiscreteTransitionVisitor {
   public Transition cloned;

   public void visit(DiscreteTransition transition) {
      this.cloned = new DiscreteTransition(transition);
   }
}
