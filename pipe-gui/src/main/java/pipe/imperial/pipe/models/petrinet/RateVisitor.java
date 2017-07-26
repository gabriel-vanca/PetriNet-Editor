package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.models.petrinet.Rate;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface RateVisitor extends PetriNetComponentVisitor {
   void visit(Rate var1);
}
