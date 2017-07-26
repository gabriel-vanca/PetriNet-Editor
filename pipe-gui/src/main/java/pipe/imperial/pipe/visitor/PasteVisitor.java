package pipe.imperial.pipe.visitor;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.imperial.pipe.exceptions.PetriNetComponentException;
import uk.ac.imperial.pipe.models.petrinet.Arc;
import uk.ac.imperial.pipe.models.petrinet.ArcPoint;
import uk.ac.imperial.pipe.models.petrinet.ArcType;
import uk.ac.imperial.pipe.models.petrinet.ArcVisitor;
import uk.ac.imperial.pipe.models.petrinet.Connectable;
import uk.ac.imperial.pipe.models.petrinet.DiscretePlace;
import uk.ac.imperial.pipe.models.petrinet.DiscretePlaceVisitor;
import uk.ac.imperial.pipe.models.petrinet.InboundArc;
import uk.ac.imperial.pipe.models.petrinet.InboundInhibitorArc;
import uk.ac.imperial.pipe.models.petrinet.InboundNormalArc;
import uk.ac.imperial.pipe.models.petrinet.OutboundArc;
import uk.ac.imperial.pipe.models.petrinet.OutboundNormalArc;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.models.petrinet.Transition;
import uk.ac.imperial.pipe.models.petrinet.TransitionVisitor;
import uk.ac.imperial.pipe.naming.MultipleNamer;

public final class PasteVisitor implements TransitionVisitor, ArcVisitor, DiscretePlaceVisitor {
   private static final Logger LOGGER = Logger.getLogger(PasteVisitor.class.getName());
   private final MultipleNamer multipleNamer;
   private final PetriNet petriNet;
   private final Collection components;
   private final Map createdPlaces;
   private final Map createdTransitions;
   private final Collection createdComponents;
   private final int xOffset;
   private final int yOffset;

   public PasteVisitor(PetriNet petriNet, Collection components, MultipleNamer multipleNamer) {
      this(petriNet, components, multipleNamer, 0, 0);
   }

   public PasteVisitor(PetriNet petriNet, Collection components, MultipleNamer multipleNamer, int xOffset, int yOffset) {
      this.components = new HashSet();
      this.createdPlaces = new HashMap();
      this.createdTransitions = new HashMap();
      this.createdComponents = new LinkedList();
      this.petriNet = petriNet;
      this.multipleNamer = multipleNamer;
      this.components.addAll(components);
      this.xOffset = xOffset;
      this.yOffset = yOffset;
   }

   public Collection getCreatedComponents() {
      return this.createdComponents;
   }

   public void visit(DiscretePlace place) {
      Place newPlace = new DiscretePlace(place);
      this.setId((Place)newPlace);
      this.setName((Place)newPlace);
      this.setOffset(newPlace);
      this.petriNet.addPlace(newPlace);
      this.createdPlaces.put(place.getId(), newPlace);
      this.createdComponents.add(newPlace);
   }

   private void setId(Place place) {
      place.setId(this.multipleNamer.getPlaceName());
   }

   private void setName(Place place) {
      place.setName(this.multipleNamer.getPlaceName());
   }

   private void setOffset(Connectable connectable) {
      connectable.setX(connectable.getX() + this.xOffset);
      connectable.setY(connectable.getY() + this.yOffset);
   }

   public void visit(Transition transition) {
      TransitionCloner cloner = new TransitionCloner();

      try {
         transition.accept(cloner);
      } catch (PetriNetComponentException var4) {
         LOGGER.log(Level.SEVERE, var4.getMessage());
      }

      Transition newTransition = cloner.cloned;
      this.setId(newTransition);
      this.setName(newTransition);
      this.setOffset(newTransition);
      this.petriNet.addTransition(newTransition);
      this.createdTransitions.put(transition.getId(), newTransition);
      this.createdComponents.add(newTransition);
   }

   private void setId(Transition transition) {
      transition.setId(this.multipleNamer.getTransitionName());
   }

   private void setName(Transition transition) {
      transition.setName(this.multipleNamer.getTransitionName());
   }

   public void visit(InboundArc inboundArc) {
      Place source = (Place)inboundArc.getSource();
      Transition target = (Transition)inboundArc.getTarget();
      if (this.components.contains(source)) {
         source = (Place)this.createdPlaces.get(source.getId());
      }

      if (this.components.contains(target)) {
         target = (Transition)this.createdTransitions.get(target.getId());
      }

      Object newArc;
      switch(inboundArc.getType()) {
      case INHIBITOR:
         newArc = new InboundInhibitorArc(source, target);
         break;
      default:
         newArc = new InboundNormalArc(source, target, inboundArc.getTokenWeights());
      }

      this.copyIntermediatePoints(inboundArc, (Arc)newArc);
      this.petriNet.addArc((InboundArc)newArc);
      this.createdComponents.add(newArc);
   }

   private void copyIntermediatePoints(Arc arc, Arc newArc) {
      List arcPoints = arc.getArcPoints();

      for(int i = 1; i < arcPoints.size() - 1; ++i) {
         ArcPoint newArcPoint = new ArcPoint((ArcPoint)arcPoints.get(i));
         newArc.addIntermediatePoint(newArcPoint);
      }

   }

   public void visit(OutboundArc outboundArc) {
      Transition source = (Transition)outboundArc.getSource();
      Place target = (Place)outboundArc.getTarget();
      if (this.components.contains(source)) {
         source = (Transition)this.createdTransitions.get(source.getId());
      }

      if (this.components.contains(target)) {
         target = (Place)this.createdPlaces.get(target.getId());
      }

      OutboundArc newArc = new OutboundNormalArc(source, target, outboundArc.getTokenWeights());
      this.copyIntermediatePoints(outboundArc, newArc);
      this.petriNet.addArc((OutboundArc)newArc);
      this.createdComponents.add(newArc);
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
}
