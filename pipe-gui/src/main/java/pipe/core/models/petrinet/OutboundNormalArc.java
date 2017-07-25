package pipe.core.models.petrinet;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import pipe.core.parsers.FunctionalResults;
import pipe.core.parsers.FunctionalWeightParser;
import pipe.core.parsers.PetriNetWeightParser;
import pipe.core.parsers.StateEvalVisitor;
import uk.ac.imperial.pipe.models.petrinet.AbstractArc;
import uk.ac.imperial.pipe.models.petrinet.ArcType;
import uk.ac.imperial.pipe.models.petrinet.InboundArc;
import uk.ac.imperial.pipe.models.petrinet.OutboundArc;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.models.petrinet.Transition;
import uk.ac.imperial.state.State;

public class OutboundNormalArc extends OutboundArc {
   public OutboundNormalArc(uk.ac.imperial.pipe.models.petrinet.Transition source, uk.ac.imperial.pipe.models.petrinet.Place target, Map tokenWeights) {
      super(source, target, tokenWeights, ArcType.NORMAL);
   }

   public final boolean canFire(uk.ac.imperial.pipe.models.petrinet.PetriNet petriNet, State state) {
      uk.ac.imperial.pipe.models.petrinet.Place place = (uk.ac.imperial.pipe.models.petrinet.Place)this.getTarget();
      if (!place.hasCapacityRestriction()) {
         return true;
      } else {
         int totalTokensIn = this.getTokenCounts(petriNet, state, this);
         int totalTokensOut = this.getNumberOfTokensLeavingPlace(state, petriNet);
         int tokensInPlace = this.getTokensInPlace(state);
         return tokensInPlace + totalTokensIn - totalTokensOut <= place.getCapacity();
      }
   }

   private int getNumberOfTokensLeavingPlace(State state, uk.ac.imperial.pipe.models.petrinet.PetriNet petriNet) {
      uk.ac.imperial.pipe.models.petrinet.Place place = (uk.ac.imperial.pipe.models.petrinet.Place)this.getTarget();
      int count = 0;
      Iterator i$ = petriNet.outboundArcs(place).iterator();

      while(i$.hasNext()) {
         uk.ac.imperial.pipe.models.petrinet.InboundArc arc = (InboundArc)i$.next();
         if (((uk.ac.imperial.pipe.models.petrinet.Place)arc.getSource()).equals(this.getTarget()) && ((Transition)arc.getTarget()).equals(this.getSource())) {
            count += this.getTokenCounts(petriNet, state, arc);
         }
      }

      return count;
   }

   private int getTokenCounts(PetriNet petriNet, State state, AbstractArc arc) {
      StateEvalVisitor stateEvalVisitor = new StateEvalVisitor(petriNet, state);
      FunctionalWeightParser functionalWeightParser = new PetriNetWeightParser(stateEvalVisitor, petriNet);
      int count = 0;

      double weight;
      for(Iterator i$ = arc.tokenWeights.entrySet().iterator(); i$.hasNext(); count = (int)((double)count + weight)) {
         Entry entry = (Entry)i$.next();
         FunctionalResults result = functionalWeightParser.evaluateExpression((String)entry.getValue());
         if (result.hasErrors()) {
            throw new RuntimeException("Cannot parse outbound arc weight");
         }

         weight = ((Double)result.getResult()).doubleValue();
      }

      return count;
   }

   private int getTokensInPlace(State state) {
      uk.ac.imperial.pipe.models.petrinet.Place place = (Place)this.getTarget();
      int count = 0;

      Integer value;
      for(Iterator i$ = state.getTokens(place.getId()).values().iterator(); i$.hasNext(); count += value.intValue()) {
         value = (Integer)i$.next();
      }

      return count;
   }
}
