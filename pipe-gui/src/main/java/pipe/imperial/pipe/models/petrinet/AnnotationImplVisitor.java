package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.AnnotationImpl;
import uk.ac.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface AnnotationImplVisitor extends PetriNetComponentVisitor {
   void visit(AnnotationImpl var1);
}
