package pipe.gui.imperial.pipe.animation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import pipe.gui.imperial.pipe.animation.AnimationLogic;
import pipe.gui.imperial.pipe.animation.AnimationUtils;
import pipe.gui.imperial.pipe.animation.Animator;
import pipe.gui.imperial.pipe.models.petrinet.Arc;
import pipe.gui.imperial.pipe.models.petrinet.PetriNet;
import pipe.gui.imperial.pipe.models.petrinet.Place;
import pipe.gui.imperial.pipe.models.petrinet.Transition;
import pipe.gui.imperial.state.State;

public final class PetriNetAnimator implements Animator {
   private static final String ERROR_NO_TRANSITIONS_TO_FIRE = "Error - no transitions to fire!";
   private final PetriNet petriNet;
   private final AnimationLogic animationLogic;
   private Map savedStateTokens = new HashMap();

   public PetriNetAnimator(PetriNet petriNet) {
      this.petriNet = petriNet;
      this.animationLogic = new PetriNetAnimationLogic(petriNet);
      this.saveState();
   }

   public void saveState() {
      this.savedStateTokens.clear();
      Iterator i$ = this.petriNet.getPlaces().iterator();

      while(i$.hasNext()) {
         Place place = (Place)i$.next();
         this.savedStateTokens.put(place.getId(), new HashMap(place.getTokenCounts()));
      }

   }

   public void reset() {
      Iterator i$ = this.petriNet.getPlaces().iterator();

      while(i$.hasNext()) {
         Place place = (Place)i$.next();
         Map originalTokens = (Map)this.savedStateTokens.get(place.getId());
         place.setTokenCounts(originalTokens);
      }

   }

   public Transition getRandomEnabledTransition() {
      Set enabledTransitions = this.getEnabledTransitions();
      if (enabledTransitions.isEmpty()) {
         throw new RuntimeException("Error - no transitions to fire!");
      } else {
         Transition[] enabledTransitionsArray = (Transition[])enabledTransitions.toArray(new Transition[0]);
         int index = (new Random()).nextInt(enabledTransitions.size());
         return enabledTransitionsArray[index];
      }
   }

   public Set getEnabledTransitions() {
      return this.animationLogic.getEnabledTransitions(pipe.gui.imperial.pipe.animation.AnimationUtils.getState(this.petriNet));
   }

   public void fireTransition(Transition transition) {
      State newState = this.animationLogic.getFiredState(pipe.gui.imperial.pipe.animation.AnimationUtils.getState(this.petriNet), transition);
      Iterator i$ = this.petriNet.getPlaces().iterator();

      while(i$.hasNext()) {
         Place place = (Place)i$.next();
         place.setTokenCounts(newState.getTokens(place.getId()));
      }

   }

   public void fireTransitionBackwards(Transition transition) {
      State state = AnimationUtils.getState(this.petriNet);
      Iterator i$ = this.petriNet.inboundArcs(transition).iterator();

      Arc arc;
      Place place;
      Iterator i$;
      Entry entry;
      String tokenId;
      String functionalWeight;
      double weight;
      int oldCount;
      int newCount;
      while(i$.hasNext()) {
         arc = (Arc)i$.next();
         place = (Place)arc.getSource();
         i$ = arc.getTokenWeights().entrySet().iterator();

         while(i$.hasNext()) {
            entry = (Entry)i$.next();
            tokenId = (String)entry.getKey();
            functionalWeight = (String)entry.getValue();
            weight = this.animationLogic.getArcWeight(state, functionalWeight);
            oldCount = place.getTokenCount(tokenId);
            newCount = oldCount + (int)weight;
            place.setTokenCount(tokenId, newCount);
         }
      }

      i$ = this.petriNet.outboundArcs(transition).iterator();

      while(i$.hasNext()) {
         arc = (Arc)i$.next();
         place = (Place)arc.getTarget();
         i$ = arc.getTokenWeights().entrySet().iterator();

         while(i$.hasNext()) {
            entry = (Entry)i$.next();
            tokenId = (String)entry.getKey();
            functionalWeight = (String)entry.getValue();
            weight = this.animationLogic.getArcWeight(state, functionalWeight);
            oldCount = place.getTokenCount(tokenId);
            newCount = oldCount - (int)weight;
            place.setTokenCount(tokenId, newCount);
         }
      }

   }
}