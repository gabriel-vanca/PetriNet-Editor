package pipe.imperial.pipe.visitor;

import pipe.imperial.pipe.models.petrinet.Annotation;
import pipe.imperial.pipe.models.petrinet.AnnotationImpl;
import pipe.imperial.pipe.models.petrinet.AnnotationImplVisitor;

public class AnnotationCloner implements AnnotationImplVisitor {
   public Annotation cloned;

   public void visit(AnnotationImpl annotation) {
      this.cloned = new AnnotationImpl(annotation);
   }
}
