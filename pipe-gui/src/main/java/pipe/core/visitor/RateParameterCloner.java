package pipe.core.visitor;

import uk.ac.imperial.pipe.models.petrinet.FunctionalRateParameter;
import uk.ac.imperial.pipe.models.petrinet.FunctionalRateParameterVisitor;
import uk.ac.imperial.pipe.models.petrinet.RateParameter;

public final class RateParameterCloner implements FunctionalRateParameterVisitor {
   public RateParameter cloned;

   public RateParameterCloner() {
      super();
   }

   public void visit(FunctionalRateParameter rateParameter) {
      this.cloned = new FunctionalRateParameter(rateParameter);
   }
}
