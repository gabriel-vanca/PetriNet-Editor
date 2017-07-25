package pipe.core.models.petrinet.name;

import uk.ac.imperial.pipe.models.petrinet.name.NameVisitor;

public interface PetriNetName {
   String getName();

   void visit(NameVisitor var1);
}
