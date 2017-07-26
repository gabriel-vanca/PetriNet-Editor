package pipe.gui.imperial.steadystate.metrics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import uk.ac.imperial.pipe.animation.AnimationLogic;
import uk.ac.imperial.pipe.animation.PetriNetAnimationLogic;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Transition;
import uk.ac.imperial.state.ClassifiedState;

public final class TransitionMetrics {
   public static Map getTransitionThroughput(Map stateSpace, Map steadyState, PetriNet petriNet) {
      AnimationLogic animationLogic = new PetriNetAnimationLogic(petriNet);
      Map throughputs = new HashMap();
      Iterator i$ = stateSpace.entrySet().iterator();

      while(i$.hasNext()) {
         Entry entry = (Entry)i$.next();
         int id = ((Integer)entry.getKey()).intValue();
         ClassifiedState state = (ClassifiedState)entry.getValue();
         Iterator i$ = animationLogic.getEnabledTransitions(state).iterator();

         while(i$.hasNext()) {
            Transition transition = (Transition)i$.next();
            String transitionId = transition.getId();
            double throughput = transition.getActualRate(petriNet, state).doubleValue() * ((Double)steadyState.get(id)).doubleValue();
            double previous = throughputs.containsKey(transitionId) ? ((Double)throughputs.get(transitionId)).doubleValue() : 0.0D;
            throughputs.put(transitionId, throughput + previous);
         }
      }

      return throughputs;
   }
}
