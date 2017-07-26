package pipe.imperial.pipe.animation;

import java.util.Set;
import pipe.imperial.pipe.models.petrinet.Transition;

public interface Animator {
   void saveState();

   void reset();

   Transition getRandomEnabledTransition();

   Set getEnabledTransitions();

   void fireTransition(Transition var1);

   void fireTransitionBackwards(Transition var1);
}
