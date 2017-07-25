package pipe.core.models.petrinet;

import pipe.core.exceptions.PetriNetComponentException;
import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.Place;

public interface PlaceVisitor extends PetriNetComponentVisitor {
   void visit(Place var1) throws PetriNetComponentException;
}
