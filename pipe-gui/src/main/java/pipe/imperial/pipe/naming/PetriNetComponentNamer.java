package pipe.imperial.pipe.naming;

import pipe.imperial.pipe.models.petrinet.PetriNet;
import pipe.imperial.pipe.naming.MultipleNamer;
import pipe.imperial.pipe.naming.PlaceNamer;
import pipe.imperial.pipe.naming.TransitionNamer;
import pipe.imperial.pipe.naming.UniqueNamer;

public final class PetriNetComponentNamer implements MultipleNamer {
   private final uk.ac.imperial.pipe.naming.UniqueNamer placeNamer;
   private final UniqueNamer transitionNamer;

   public PetriNetComponentNamer(PetriNet net) {
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
