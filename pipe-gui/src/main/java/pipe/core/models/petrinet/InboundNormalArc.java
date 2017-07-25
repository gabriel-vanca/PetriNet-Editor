package pipe.core.models.petrinet;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import pipe.core.parsers.FunctionalResults;
import pipe.core.parsers.FunctionalWeightParser;
import pipe.core.parsers.PetriNetWeightParser;
import pipe.core.parsers.StateEvalVisitor;
import uk.ac.imperial.pipe.models.petrinet.ArcType;
import uk.ac.imperial.pipe.models.petrinet.InboundArc;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.models.petrinet.Transition;
import uk.ac.imperial.state.State;

public class InboundNormalArc extends InboundArc {
   public InboundNormalArc(uk.ac.imperial.pipe.models.petrinet.Place source, Transition target, Map tokenWeights) {
      super(source, target, tokenWeights, ArcType.NORMAL);
   }

   public final boolean canFire(PetriNet petriNet, State state) {
      uk.ac.imperial.pipe.models.petrinet.Place place = (Place)this.getSource();
      Map tokenCounts = state.getTokens(place.getId());
      if (this.allTokenCountsAreZero(tokenCounts)) {
         return false;
      } else {
         Map tokenWeights = this.getTokenWeights();
         StateEvalVisitor stateEvalVisitor = new StateEvalVisitor(petriNet, state);
         FunctionalWeightParser functionalWeightParser = new PetriNetWeightParser(stateEvalVisitor, petriNet);
         Iterator i$ = tokenWeights.entrySet().iterator();

         double tokenWeight;
         int currentCount;
         do {
            if (!i$.hasNext()) {
               return true;
            }

            Entry entry = (Entry)i$.next();
            FunctionalResults results = functionalWeightParser.evaluateExpression((String)entry.getValue());
            if (results.hasErrors()) {
               throw new RuntimeException("Errors evaluating arc weight against Petri net. Needs handling in code");
            }

            tokenWeight = ((Double)results.getResult()).doubleValue();
            String tokenId = (String)entry.getKey();
            currentCount = ((Integer)tokenCounts.get(tokenId)).intValue();
         } while((double)currentCount >= tokenWeight);

         return false;
      }
   }

   private boolean allTokenCountsAreZero(Map tokenCounts) {
      boolean allCountsAreZero = true;
      Iterator i$ = tokenCounts.values().iterator();

      while(i$.hasNext()) {
         Integer count = (Integer)i$.next();
         if (count.intValue() > 0) {
            allCountsAreZero = false;
         }
      }

      return allCountsAreZero;
   }
}
