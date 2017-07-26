package pipe.imperial.io;

import com.esotericsoftware.kryo.io.Output;
import java.util.Map;
import pipe.imperial.state.ClassifiedState;
import uk.ac.imperial.io.StateProcessor;
import uk.ac.imperial.io.StateWriter;

public final class StateIOProcessor implements StateProcessor {
   private final uk.ac.imperial.io.StateWriter writer;
   private final Output transitionOutput;
   private Output stateOutput;

   public StateIOProcessor(StateWriter writer, Output transitionOutput, Output stateOutput) {
      this.writer = writer;
      this.transitionOutput = transitionOutput;
      this.stateOutput = stateOutput;
   }

   public void processTransitions(int stateId, Map successorRates) {
      this.writer.writeTransitions(stateId, successorRates, this.transitionOutput);
   }

   public void processState(ClassifiedState state, int stateId) {
      this.writer.writeState(state, stateId, this.stateOutput);
   }
}
