package pipe.core.models.petrinet;

import pipe.core.visitor.component.PetriNetComponentVisitor;

public interface ArcVisitor extends PetriNetComponentVisitor {
   void visit(InboundArc var1);

   void visit(OutboundArc var1);
}
