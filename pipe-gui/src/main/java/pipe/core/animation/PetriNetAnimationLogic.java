package pipe.core.animation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import uk.ac.imperial.pipe.animation.AnimationLogic;
import uk.ac.imperial.pipe.models.petrinet.Arc;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.models.petrinet.Transition;
import uk.ac.imperial.pipe.parsers.FunctionalResults;
import uk.ac.imperial.pipe.parsers.PetriNetWeightParser;
import uk.ac.imperial.pipe.parsers.StateEvalVisitor;
import uk.ac.imperial.state.HashedStateBuilder;
import uk.ac.imperial.state.State;

public final class PetriNetAnimationLogic implements AnimationLogic {
   private final PetriNet petriNet;
   public Map cachedEnabledTransitions = new ConcurrentHashMap();

   public PetriNetAnimationLogic(PetriNet petriNet) {
      super();
      this.petriNet = petriNet;
   }

   public Set getEnabledTransitions(State state) {
      if (this.cachedEnabledTransitions.containsKey(state)) {
         return (Set)this.cachedEnabledTransitions.get(state);
      } else {
         Set enabledTransitions = this.findEnabledTransitions(state);
         boolean hasImmediate = this.areAnyTransitionsImmediate(enabledTransitions);
         int maxPriority = hasImmediate ? this.getMaxPriority(enabledTransitions) : 0;
         if (hasImmediate) {
            this.removeTimedTransitions(enabledTransitions);
         }

         this.removePrioritiesLessThan(maxPriority, enabledTransitions);
         this.cachedEnabledTransitions.put(state, enabledTransitions);
         return enabledTransitions;
      }
   }

   public Map getSuccessors(State state) {
      Collection enabled = this.getEnabledTransitions(state);
      Map successors = new HashMap();

      Transition transition;
      State successor;
      for(Iterator i$ = enabled.iterator(); i$.hasNext(); ((Collection)successors.get(successor)).add(transition)) {
         transition = (Transition)i$.next();
         successor = this.getFiredState(state, transition);
         if (!successors.containsKey(successor)) {
            successors.put(successor, new LinkedList());
         }
      }

      return successors;
   }

   public State getFiredState(State state, Transition transition) {
      HashedStateBuilder builder = new HashedStateBuilder();
      Iterator i$ = state.getPlaces().iterator();

      while(i$.hasNext()) {
         String placeId = (String)i$.next();
         builder.placeWithTokens(placeId, state.getTokens(placeId));
      }

      Set enabled = this.getEnabledTransitions(state);
      if (enabled.contains(transition)) {
         Iterator i$ = this.petriNet.inboundArcs(transition).iterator();

         int currentCount;
         while(i$.hasNext()) {
            Arc arc = (Arc)i$.next();
            String placeId = ((Place)arc.getSource()).getId();
            Map arcWeights = arc.getTokenWeights();
            Iterator i$ = state.getTokens(placeId).entrySet().iterator();

            while(i$.hasNext()) {
               Entry entry = (Entry)i$.next();
               String tokenId = (String)entry.getKey();
               if (arcWeights.containsKey(tokenId)) {
                  int currentCount = ((Integer)entry.getValue()).intValue();
                  currentCount = (int)this.getArcWeight(state, (String)arcWeights.get(tokenId));
                  builder.placeWithToken(placeId, tokenId, this.subtractWeight(currentCount, currentCount));
               }
            }
         }

         State temporaryState = builder.build();
         Iterator i$ = this.petriNet.outboundArcs(transition).iterator();

         while(i$.hasNext()) {
            Arc arc = (Arc)i$.next();
            String placeId = ((Place)arc.getTarget()).getId();
            Map arcWeights = arc.getTokenWeights();
            Iterator i$ = arcWeights.entrySet().iterator();

            while(i$.hasNext()) {
               Entry entry = (Entry)i$.next();
               String tokenId = (String)entry.getKey();
               currentCount = ((Integer)temporaryState.getTokens(placeId).get(tokenId)).intValue();
               int arcWeight = (int)this.getArcWeight(state, (String)entry.getValue());
               builder.placeWithToken(placeId, tokenId, this.addWeight(currentCount, arcWeight));
            }
         }
      }

      return builder.build();
   }

   public double getArcWeight(State state, String weight) {
      StateEvalVisitor evalVisitor = new StateEvalVisitor(this.petriNet, state);
      PetriNetWeightParser parser = new PetriNetWeightParser(evalVisitor, this.petriNet);
      FunctionalResults result = parser.evaluateExpression(weight);
      if (result.hasErrors()) {
         throw new RuntimeException("Could not parse arc weight");
      } else {
         return ((Double)result.getResult()).doubleValue();
      }
   }

   public void clear() {
      this.cachedEnabledTransitions.clear();
   }

   private int subtractWeight(int currentWeight, int arcWeight) {
      return currentWeight == Integer.MAX_VALUE ? currentWeight : currentWeight - arcWeight;
   }

   private int addWeight(int currentWeight, int arcWeight) {
      return currentWeight == Integer.MAX_VALUE ? currentWeight : currentWeight + arcWeight;
   }

   private Set findEnabledTransitions(State state) {
      Set enabledTransitions = new HashSet();
      Iterator i$ = this.petriNet.getTransitions().iterator();

      while(i$.hasNext()) {
         Transition transition = (Transition)i$.next();
         if (this.isEnabled(transition, state)) {
            enabledTransitions.add(transition);
         }
      }

      return enabledTransitions;
   }

   private boolean isEnabled(Transition transition, State state) {
      Iterator i$ = this.petriNet.inboundArcs(transition).iterator();

      Arc arc;
      do {
         if (!i$.hasNext()) {
            i$ = this.petriNet.outboundArcs(transition).iterator();

            do {
               if (!i$.hasNext()) {
                  return true;
               }

               arc = (Arc)i$.next();
            } while(arc.canFire(this.petriNet, state));

            return false;
         }

         arc = (Arc)i$.next();
      } while(arc.canFire(this.petriNet, state));

      return false;
   }

   private boolean areAnyTransitionsImmediate(Iterable transitions) {
      Iterator i$ = transitions.iterator();

      Transition transition;
      do {
         if (!i$.hasNext()) {
            return false;
         }

         transition = (Transition)i$.next();
      } while(transition.isTimed());

      return true;
   }

   private int getMaxPriority(Iterable transitions) {
      int maxPriority = 0;
      Iterator i$ = transitions.iterator();

      while(i$.hasNext()) {
         Transition transition = (Transition)i$.next();
         if (!transition.isTimed()) {
            maxPriority = Math.max(maxPriority, transition.getPriority());
         }
      }

      return maxPriority;
   }

   private void removePrioritiesLessThan(int priority, Iterable transitions) {
      Iterator transitionIterator = transitions.iterator();

      while(transitionIterator.hasNext()) {
         Transition transition = (Transition)transitionIterator.next();
         if (!transition.isTimed() && transition.getPriority() < priority) {
            transitionIterator.remove();
         }
      }

   }

   private void removeTimedTransitions(Iterable transitions) {
      Iterator transitionIterator = transitions.iterator();

      while(transitionIterator.hasNext()) {
         Transition transition = (Transition)transitionIterator.next();
         if (transition.isTimed()) {
            transitionIterator.remove();
         }
      }

   }
}
