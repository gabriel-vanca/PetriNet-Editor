package pipe.core.models.petrinet;

import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.AnnotationImpl;

public interface AnnotationImplVisitor extends PetriNetComponentVisitor {
   void visit(AnnotationImpl var1);
}
