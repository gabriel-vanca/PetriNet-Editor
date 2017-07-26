package pipe.gui.imperial.pipe.visitor;

import pipe.gui.imperial.pipe.models.petrinet.DiscreteTransition;
import pipe.gui.imperial.pipe.models.petrinet.DiscreteTransitionVisitor;
import pipe.gui.imperial.pipe.models.petrinet.Transition;

public final class TransitionCloner implements DiscreteTransitionVisitor {
   public Transition cloned;

   public void visit(DiscreteTransition transition) {
      this.cloned = new DiscreteTransition(transition);
   }
}
