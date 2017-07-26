package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.ArcPoint;
import uk.ac.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface ArcPointVisitor extends PetriNetComponentVisitor {
   void visit(ArcPoint var1);
}
