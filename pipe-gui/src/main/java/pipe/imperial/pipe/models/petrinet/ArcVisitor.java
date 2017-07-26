package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface ArcVisitor extends PetriNetComponentVisitor {
   void visit(InboundArc var1);

   void visit(OutboundArc var1);
}
