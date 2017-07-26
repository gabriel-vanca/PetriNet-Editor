package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.models.petrinet.AnnotationImpl;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface AnnotationImplVisitor extends PetriNetComponentVisitor {
   void visit(AnnotationImpl var1);
}
