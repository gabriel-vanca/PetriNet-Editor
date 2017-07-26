package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.models.petrinet.Annotation;
import pipe.imperial.pipe.visitor.component.PetriNetComponentVisitor;

public interface AnnotationVisitor extends PetriNetComponentVisitor {
   void visit(Annotation var1);
}
