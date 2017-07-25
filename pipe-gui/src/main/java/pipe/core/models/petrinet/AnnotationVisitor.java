package pipe.core.models.petrinet;

import pipe.core.visitor.component.PetriNetComponentVisitor;
import uk.ac.imperial.pipe.models.petrinet.Annotation;

public interface AnnotationVisitor extends PetriNetComponentVisitor {
   void visit(Annotation var1);
}
