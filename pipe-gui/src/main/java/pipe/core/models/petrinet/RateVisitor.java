package pipe.core.models.petrinet;

import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.Rate;

public interface RateVisitor extends PetriNetComponentVisitor {
   void visit(Rate var1);
}
