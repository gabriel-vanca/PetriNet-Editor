package pipe.core.models.petrinet;

import pipe.core.exceptions.InvalidRateException;
import pipe.core.visitor.component.PetriNetComponentVisitor;

public interface RateParameterVisitor extends PetriNetComponentVisitor {
   void visit(FunctionalRateParameter var1) throws InvalidRateException;
}
