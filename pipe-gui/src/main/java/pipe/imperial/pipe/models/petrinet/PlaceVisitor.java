package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.exceptions.PetriNetComponentException;
import pipe.imperial.pipe.models.petrinet.Place;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface PlaceVisitor extends PetriNetComponentVisitor {
   void visit(Place var1) throws PetriNetComponentException;
}
