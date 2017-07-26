package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.exceptions.PetriNetComponentException;
import pipe.imperial.pipe.models.petrinet.Token;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface TokenVisitor extends PetriNetComponentVisitor {
   void visit(Token var1) throws PetriNetComponentException;
}
