package pipe.core.exceptions;

import uk.ac.imperial.pipe.exceptions.PetriNetComponentException;

public class PetriNetComponentNotFoundException extends PetriNetComponentException {
   public PetriNetComponentNotFoundException(String message) {
      super(message);
   }
}
