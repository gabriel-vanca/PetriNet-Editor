package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.exceptions.PetriNetComponentException;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface PlaceVisitor extends PetriNetComponentVisitor {
   void visit(Place var1) throws PetriNetComponentException;
}
