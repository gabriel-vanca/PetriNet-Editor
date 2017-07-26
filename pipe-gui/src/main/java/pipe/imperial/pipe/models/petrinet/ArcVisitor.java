package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface ArcVisitor extends PetriNetComponentVisitor {
   void visit(InboundArc var1);

   void visit(OutboundArc var1);
}
