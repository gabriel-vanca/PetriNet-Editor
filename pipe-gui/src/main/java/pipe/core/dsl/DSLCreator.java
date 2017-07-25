package pipe.core.dsl;

import java.util.Map;
import pipe.core.models.petrinet.PetriNetComponent;

public interface DSLCreator {
   PetriNetComponent create(Map var1, Map var2, Map var3, Map var4);
}
