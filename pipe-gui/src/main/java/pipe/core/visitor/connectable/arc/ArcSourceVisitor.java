package pipe.core.visitor.connectable.arc;

import uk.ac.imperial.pipe.models.petrinet.Connectable;
import uk.ac.imperial.pipe.models.petrinet.PlaceVisitor;
import uk.ac.imperial.pipe.models.petrinet.TransitionVisitor;

public interface ArcSourceVisitor extends PlaceVisitor, TransitionVisitor {
   boolean canStart(Connectable var1);
}
