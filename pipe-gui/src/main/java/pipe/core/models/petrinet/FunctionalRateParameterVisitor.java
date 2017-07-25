package pipe.core.models.petrinet;

import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.FunctionalRateParameter;

public interface FunctionalRateParameterVisitor extends PetriNetComponentVisitor {
   void visit(FunctionalRateParameter var1);
}
