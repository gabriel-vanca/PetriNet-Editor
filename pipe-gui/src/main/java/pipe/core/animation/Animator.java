package pipe.core.animation;

import java.util.Set;
import uk.ac.imperial.pipe.models.petrinet.Transition;

public interface Animator {
   void saveState();

   void reset();

   Transition getRandomEnabledTransition();

   Set getEnabledTransitions();

   void fireTransition(Transition var1);

   void fireTransitionBackwards(Transition var1);
}
