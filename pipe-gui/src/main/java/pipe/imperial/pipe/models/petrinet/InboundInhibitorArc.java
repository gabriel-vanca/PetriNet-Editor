package pipe.imperial.pipe.models.petrinet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import uk.ac.imperial.pipe.models.petrinet.ArcType;
import uk.ac.imperial.pipe.models.petrinet.InboundArc;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.models.petrinet.Transition;
import pipe.imperial.state.State;

public class InboundInhibitorArc extends InboundArc {
   public InboundInhibitorArc(uk.ac.imperial.pipe.models.petrinet.Place source, Transition target) {
      super(source, target, new HashMap(), ArcType.INHIBITOR);
   }

   public final boolean canFire(PetriNet petriNet, State state) {
      Map tokens = state.getTokens(((Place)this.getSource()).getId());
      Iterator i$ = tokens.values().iterator();

      Integer tokenCount;
      do {
         if (!i$.hasNext()) {
            return true;
         }

         tokenCount = (Integer)i$.next();
      } while(tokenCount.intValue() == 0);

      return false;
   }
}
