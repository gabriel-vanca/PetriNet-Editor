package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.exceptions.InvalidRateException;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface RateParameterVisitor extends PetriNetComponentVisitor {
   void visit(FunctionalRateParameter var1) throws InvalidRateException;
}
