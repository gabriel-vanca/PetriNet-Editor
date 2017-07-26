package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.Rate;

public interface RateParameter extends PetriNetComponent, Rate {
   String EXPRESSION_CHANGE_MESSAGE = "expression";

   void setExpression(String var1);
}
