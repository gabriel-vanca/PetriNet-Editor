package pipe.imperial.pipe.visitor;

import uk.ac.imperial.pipe.models.petrinet.Annotation;
import uk.ac.imperial.pipe.models.petrinet.AnnotationImpl;
import uk.ac.imperial.pipe.models.petrinet.AnnotationImplVisitor;

public class AnnotationCloner implements AnnotationImplVisitor {
   public Annotation cloned;

   public void visit(AnnotationImpl annotation) {
      this.cloned = new AnnotationImpl(annotation);
   }
}
