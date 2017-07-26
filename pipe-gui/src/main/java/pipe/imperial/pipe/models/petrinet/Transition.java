package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Rate;
import pipe.imperial.state.State;

public interface Transition extends Connectable {
   String PRIORITY_CHANGE_MESSAGE = "priority";
   String RATE_CHANGE_MESSAGE = "rate";
   String ANGLE_CHANGE_MESSAGE = "angle";
   String TIMED_CHANGE_MESSAGE = "timed";
   String INFINITE_SEVER_CHANGE_MESSAGE = "infiniteServer";
   String ENABLED_CHANGE_MESSAGE = "enabled";
   String DISABLED_CHANGE_MESSAGE = "disabled";
   int TRANSITION_HEIGHT = 30;
   int TRANSITION_WIDTH = 10;

   int getPriority();

   void setPriority(int var1);

   uk.ac.imperial.pipe.models.petrinet.Rate getRate();

   void setRate(Rate var1);

   Double getActualRate(PetriNet var1, State var2);

   String getRateExpr();

   boolean isInfiniteServer();

   void setInfiniteServer(boolean var1);

   int getAngle();

   void setAngle(int var1);

   boolean isTimed();

   void setTimed(boolean var1);

   void enable();

   void disable();

   boolean isEnabled();
}