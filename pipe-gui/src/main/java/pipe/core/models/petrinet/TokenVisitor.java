package pipe.core.models.petrinet;

import pipe.core.exceptions.PetriNetComponentException;
import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.Token;

public interface TokenVisitor extends PetriNetComponentVisitor {
   void visit(Token var1) throws PetriNetComponentException;
}
