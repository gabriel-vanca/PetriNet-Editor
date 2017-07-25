package pipe.core.petrinet.unfold;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import pipe.core.exceptions.PetriNetComponentException;
import pipe.core.exceptions.PetriNetComponentNotFoundException;
import uk.ac.imperial.pipe.models.petrinet.Arc;
import uk.ac.imperial.pipe.models.petrinet.ArcType;
import uk.ac.imperial.pipe.models.petrinet.ColoredToken;
import uk.ac.imperial.pipe.models.petrinet.InboundInhibitorArc;
import uk.ac.imperial.pipe.models.petrinet.InboundNormalArc;
import uk.ac.imperial.pipe.models.petrinet.OutboundNormalArc;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.models.petrinet.Token;
import uk.ac.imperial.pipe.models.petrinet.Transition;
import uk.ac.imperial.pipe.visitor.PlaceCloner;
import uk.ac.imperial.pipe.visitor.TransitionCloner;

public final class Expander {
   private static final Logger LOGGER = Logger.getLogger(Expander.class.getName());
   private final PetriNet petriNet;
   private final Token unfoldToken;
   private final Map newPlaces = new HashMap();
   private final Map newTransitions = new HashMap();
   private final Map newArcs = new HashMap();

   public Expander(PetriNet petriNet) {
      super();
      this.petriNet = petriNet;
      this.unfoldToken = this.getCopiedToken();
   }

   private Token getCopiedToken() {
      return new ColoredToken(this.getToken());
   }

   private Token getToken() {
      if (this.petriNet.containsDefaultToken()) {
         return this.getDefaultToken();
      } else {
         Token blackToken = this.getBlackToken();
         return blackToken != null ? blackToken : this.getFirstToken();
      }
   }

   private Token getDefaultToken() {
      try {
         return (Token)this.petriNet.getComponent("Default", Token.class);
      } catch (PetriNetComponentNotFoundException var2) {
         return null;
      }
   }

   private Token getBlackToken() {
      Iterator i$ = this.petriNet.getTokens().iterator();

      Token token;
      do {
         if (!i$.hasNext()) {
            return null;
         }

         token = (Token)i$.next();
      } while(!token.getColor().equals(Color.BLACK));

      return token;
   }

   private Token getFirstToken() {
      return (Token)this.petriNet.getTokens().iterator().next();
   }

   public PetriNet unfold() {
      this.unfoldTransitions();
      return this.createPetriNet();
   }

   private void unfoldTransitions() {
      Iterator i$ = this.petriNet.getTransitions().iterator();

      while(i$.hasNext()) {
         Transition transition = (Transition)i$.next();
         TransitionCloner cloner = new TransitionCloner();

         try {
            transition.accept(cloner);
         } catch (PetriNetComponentException var5) {
            LOGGER.log(Level.SEVERE, var5.getMessage());
         }

         Transition newTransition = cloner.cloned;
         this.newTransitions.put(newTransition.getId(), newTransition);
         this.analyseOutboundArcs(newTransition, this.petriNet.outboundArcs(transition));
         this.analyseInboundArcs(newTransition, this.petriNet.inboundArcs(transition));
      }

   }

   private PetriNet createPetriNet() {
      PetriNet petriNet = new PetriNet();
      petriNet.addToken(this.unfoldToken);
      Iterator i$ = this.newPlaces.values().iterator();

      while(i$.hasNext()) {
         Place place = (Place)i$.next();
         petriNet.addPlace(place);
      }

      i$ = this.newTransitions.values().iterator();

      while(i$.hasNext()) {
         Transition transition = (Transition)i$.next();
         petriNet.addTransition(transition);
      }

      i$ = this.newArcs.values().iterator();

      while(i$.hasNext()) {
         Arc arc = (Arc)i$.next();

         try {
            petriNet.add(arc);
         } catch (PetriNetComponentException var5) {
            LOGGER.log(Level.SEVERE, var5.getMessage());
         }
      }

      return petriNet;
   }

   public void analyseOutboundArcs(Transition newTransition, Iterable arcs) {
      Iterator i$ = arcs.iterator();

      while(i$.hasNext()) {
         Arc arc = (Arc)i$.next();
         Place place = (Place)arc.getTarget();
         Expander.Data data = this.getPlaceData(arc, place);
         Place newPlace = this.getNewPlace(place, newTransition.getX(), newTransition.getY(), data.placeTokenCount, data.name);
         this.createArc(newTransition, newPlace, data.arcWeight, arc.getType());
      }

   }

   public void analyseInboundArcs(Transition newTransition, Iterable arcs) {
      Iterator i$ = arcs.iterator();

      while(i$.hasNext()) {
         Arc arc = (Arc)i$.next();
         Place place = (Place)arc.getSource();
         Expander.Data data = this.getPlaceData(arc, place);
         Place newPlace = this.getNewPlace(place, newTransition.getX(), newTransition.getY(), data.placeTokenCount, data.name);
         this.createArc(newPlace, newTransition, data.arcWeight, arc.getType());
      }

   }

   private Expander.Data getPlaceData(Arc arc, Place place) {
      StringBuilder newNameBuilder = new StringBuilder(place.getName());
      int placeTokenCount = 0;
      int arcWeight = 0;
      Iterator i$ = (new TreeMap(arc.getTokenWeights())).entrySet().iterator();

      while(i$.hasNext()) {
         Entry entry = (Entry)i$.next();
         String token = (String)entry.getKey();
         String weight = (String)entry.getValue();
         arcWeight = Integer.valueOf(weight).intValue();
         if (arcWeight > 0) {
            newNameBuilder.append("_").append(token);
            placeTokenCount = place.getTokenCount(token);
         }
      }

      return new Expander.Data(placeTokenCount, arcWeight, newNameBuilder.toString());
   }

   private Place getNewPlace(Place original, int newX, int newY, int tokenCount, String id) {
      if (this.newPlaces.containsKey(id)) {
         return (Place)this.newPlaces.get(id);
      } else {
         PlaceCloner cloner = new PlaceCloner();

         try {
            original.accept(cloner);
         } catch (PetriNetComponentException var9) {
            LOGGER.log(Level.SEVERE, var9.getMessage());
         }

         Place place = cloner.cloned;
         Map newTokenCounts = new HashMap();
         if (tokenCount > 0) {
            newTokenCounts.put(this.unfoldToken.getId(), tokenCount);
         }

         place.setTokenCounts(newTokenCounts);
         place.setName(id);
         place.setId(id);
         place.setX(newX);
         place.setY(newY);
         this.newPlaces.put(place.getId(), place);
         return place;
      }
   }

   private void createArc(Transition source, Place target, int arcWeight, ArcType type) {
      Arc newArc = new OutboundNormalArc(source, target, this.getNewArcWeight(arcWeight));
      this.newArcs.put(newArc.getId(), newArc);
   }

   private void createArc(Place source, Transition target, int arcWeight, ArcType type) {
      Object newArc;
      switch(type) {
      case INHIBITOR:
         newArc = new InboundInhibitorArc(source, target);
         break;
      default:
         newArc = new InboundNormalArc(source, target, this.getNewArcWeight(arcWeight));
      }

      this.newArcs.put(((Arc)newArc).getId(), newArc);
   }

   private Map getNewArcWeight(int arcWeight) {
      Map arcWeights = new HashMap();
      arcWeights.put(this.unfoldToken.getId(), Integer.toString(arcWeight));
      return arcWeights;
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$uk$ac$imperial$pipe$models$petrinet$ArcType = new int[ArcType.values().length];

      static {
         try {
            $SwitchMap$uk$ac$imperial$pipe$models$petrinet$ArcType[ArcType.INHIBITOR.ordinal()] = 1;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }

   private static class Data {
      public final int placeTokenCount;
      public final int arcWeight;
      public final String name;

      public Data(int placeTokenCount, int arcWeight, String name) {
         super();
         this.placeTokenCount = placeTokenCount;
         this.arcWeight = arcWeight;
         this.name = name;
      }
   }
}
