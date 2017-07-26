package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.FunctionalRateParameter;
import uk.ac.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface FunctionalRateParameterVisitor extends PetriNetComponentVisitor {
   void visit(FunctionalRateParameter var1);
}
