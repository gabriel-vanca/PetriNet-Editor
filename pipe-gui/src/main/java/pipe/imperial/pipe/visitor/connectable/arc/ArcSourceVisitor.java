package pipe.imperial.pipe.visitor.connectable.arc;

import pipe.imperial.pipe.models.petrinet.Connectable;
import pipe.imperial.pipe.models.petrinet.PlaceVisitor;
import pipe.imperial.pipe.models.petrinet.TransitionVisitor;

public interface ArcSourceVisitor extends PlaceVisitor, TransitionVisitor {
   boolean canStart(Connectable var1);
}
