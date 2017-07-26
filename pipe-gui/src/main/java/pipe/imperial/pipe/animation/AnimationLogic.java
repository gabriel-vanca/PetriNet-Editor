package pipe.imperial.pipe.animation;

import java.util.Map;
import java.util.Set;
import pipe.imperial.pipe.models.petrinet.Transition;
import pipe.imperial.state.State;

public interface AnimationLogic {
   Set getEnabledTransitions(State var1);

   Map getSuccessors(State var1);

   State getFiredState(State var1, Transition var2);

   double getArcWeight(State var1, String var2);

   void clear();
}
