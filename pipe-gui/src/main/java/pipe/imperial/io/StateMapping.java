package pipe.imperial.io;

import pipe.imperial.state.ClassifiedState;

public class StateMapping {
   public final ClassifiedState state;
   public final int id;

   public StateMapping(ClassifiedState state, int id) {
      this.state = state;
      this.id = id;
   }
}
