package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.Rate;
import uk.ac.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface RateVisitor extends PetriNetComponentVisitor {
   void visit(Rate var1);
}
