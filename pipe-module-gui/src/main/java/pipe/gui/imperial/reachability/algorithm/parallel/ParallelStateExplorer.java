package pipe.gui.imperial.reachability.algorithm.parallel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import pipe.gui.imperial.reachability.algorithm.ExplorerUtilities;
import pipe.gui.imperial.reachability.algorithm.StateRateRecord;
import pipe.gui.imperial.reachability.algorithm.TimelessTrapException;
import pipe.gui.imperial.reachability.algorithm.VanishingExplorer;
import uk.ac.imperial.pipe.exceptions.InvalidRateException;
import uk.ac.imperial.state.ClassifiedState;

public final class ParallelStateExplorer implements Callable {
   private final CountDownLatch latch;
   private final ClassifiedState state;
   private final ExplorerUtilities explorerUtilities;
   private final VanishingExplorer vanishingExplorer;

   public ParallelStateExplorer(CountDownLatch latch, ClassifiedState state, ExplorerUtilities explorerUtilities, VanishingExplorer vanishingExplorer) {
      this.latch = latch;
      this.state = state;
      this.explorerUtilities = explorerUtilities;
      this.vanishingExplorer = vanishingExplorer;
   }

   public Map call() throws TimelessTrapException, InvalidRateException {
      try {
         Map stateRates = new HashMap();
         Iterator i$ = this.explorerUtilities.getSuccessors(this.state).iterator();

         label54:
         while(true) {
            if (i$.hasNext()) {
               ClassifiedState successor = (ClassifiedState)i$.next();
               double rate = this.explorerUtilities.rate(this.state, successor);
               if (successor.isTangible()) {
                  this.registerStateRate(successor, rate, stateRates);
                  continue;
               }

               Collection explorableStates = this.vanishingExplorer.explore(successor, rate);
               Iterator i$ = explorableStates.iterator();

               while(true) {
                  if (!i$.hasNext()) {
                     continue label54;
                  }

                  StateRateRecord record = (StateRateRecord)i$.next();
                  this.registerStateRate(record.getState(), record.getRate(), stateRates);
               }
            }

            HashMap var12 = stateRates;
            return var12;
         }
      } finally {
         this.latch.countDown();
      }
   }

   private void registerStateRate(ClassifiedState successor, double rate, Map stateRates) {
      if (stateRates.containsKey(successor)) {
         double previousRate = ((Double)stateRates.get(successor)).doubleValue();
         stateRates.put(successor, previousRate + rate);
      } else {
         stateRates.put(successor, rate);
      }

   }
}
