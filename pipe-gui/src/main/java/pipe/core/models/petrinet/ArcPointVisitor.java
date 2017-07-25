package pipe.core.models.petrinet;

import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.ArcPoint;

public interface ArcPointVisitor extends PetriNetComponentVisitor {
   void visit(ArcPoint var1);
}
