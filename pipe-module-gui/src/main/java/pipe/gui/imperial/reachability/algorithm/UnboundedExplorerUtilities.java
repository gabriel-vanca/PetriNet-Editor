package pipe.gui.imperial.reachability.algorithm;

import uk.ac.imperial.pipe.models.petrinet.PetriNet;

public final class UnboundedExplorerUtilities extends CachingExplorerUtilities {
   public UnboundedExplorerUtilities(PetriNet petriNet) {
      super(petriNet);
   }

   public boolean canExploreMore(int stateCount) {
      return true;
   }
}
