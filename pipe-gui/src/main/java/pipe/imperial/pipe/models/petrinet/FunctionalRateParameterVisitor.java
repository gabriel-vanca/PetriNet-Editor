package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.models.petrinet.FunctionalRateParameter;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface FunctionalRateParameterVisitor extends PetriNetComponentVisitor {
   void visit(FunctionalRateParameter var1);
}
