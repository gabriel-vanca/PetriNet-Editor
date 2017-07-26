package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.models.petrinet.ArcPoint;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface ArcPointVisitor extends PetriNetComponentVisitor {
   void visit(ArcPoint var1);
}
