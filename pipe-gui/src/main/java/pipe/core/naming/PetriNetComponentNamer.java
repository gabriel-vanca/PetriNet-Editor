package pipe.core.naming;

import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.naming.MultipleNamer;
import uk.ac.imperial.pipe.naming.PlaceNamer;
import uk.ac.imperial.pipe.naming.TransitionNamer;
import uk.ac.imperial.pipe.naming.UniqueNamer;

public final class PetriNetComponentNamer implements MultipleNamer {
   private final uk.ac.imperial.pipe.naming.UniqueNamer placeNamer;
   private final UniqueNamer transitionNamer;

   public PetriNetComponentNamer(PetriNet net) {
      super();
      this.placeNamer = new PlaceNamer(net);
      this.transitionNamer = new TransitionNamer(net);
   }

   public String getPlaceName() {
      return this.placeNamer.getName();
   }

   public String getTransitionName() {
      return this.transitionNamer.getName();
   }

   public String getArcName() {
      return "";
   }
}
