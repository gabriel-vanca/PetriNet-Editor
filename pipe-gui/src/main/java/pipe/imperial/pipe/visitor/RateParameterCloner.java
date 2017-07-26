package pipe.imperial.pipe.visitor;

import pipe.imperial.pipe.models.petrinet.FunctionalRateParameter;
import pipe.imperial.pipe.models.petrinet.FunctionalRateParameterVisitor;
import pipe.imperial.pipe.models.petrinet.RateParameter;

public final class RateParameterCloner implements FunctionalRateParameterVisitor {
   public RateParameter cloned;

   public void visit(FunctionalRateParameter rateParameter) {
      this.cloned = new FunctionalRateParameter(rateParameter);
   }
}
