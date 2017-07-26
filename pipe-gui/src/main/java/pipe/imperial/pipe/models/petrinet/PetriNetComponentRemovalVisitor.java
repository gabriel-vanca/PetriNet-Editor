package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.exceptions.InvalidRateException;
import pipe.imperial.pipe.exceptions.PetriNetComponentException;
import pipe.imperial.pipe.models.petrinet.Annotation;
import pipe.imperial.pipe.models.petrinet.AnnotationVisitor;
import pipe.imperial.pipe.models.petrinet.ArcVisitor;
import pipe.imperial.pipe.models.petrinet.FunctionalRateParameter;
import pipe.imperial.pipe.models.petrinet.InboundArc;
import pipe.imperial.pipe.models.petrinet.OutboundArc;
import pipe.imperial.pipe.models.petrinet.PetriNet;
import pipe.imperial.pipe.models.petrinet.Place;
import pipe.imperial.pipe.models.petrinet.PlaceVisitor;
import pipe.imperial.pipe.models.petrinet.RateParameterVisitor;
import pipe.imperial.pipe.models.petrinet.Token;
import pipe.imperial.pipe.models.petrinet.TokenVisitor;
import pipe.imperial.pipe.models.petrinet.Transition;
import pipe.imperial.pipe.models.petrinet.TransitionVisitor;

public final class PetriNetComponentRemovalVisitor implements PlaceVisitor, TransitionVisitor, ArcVisitor, TokenVisitor, AnnotationVisitor, RateParameterVisitor {
   private final uk.ac.imperial.pipe.models.petrinet.PetriNet net;

   public PetriNetComponentRemovalVisitor(PetriNet net) {
      this.net = net;
   }

   public void visit(Place place) throws PetriNetComponentException {
      this.net.removePlace(place);
   }

   public void visit(Transition transition) {
      this.net.removeTransition(transition);
   }

   public void visit(Token token) throws PetriNetComponentException {
      this.net.removeToken(token);
   }

   public void visit(Annotation annotation) {
      this.net.removeAnnotation(annotation);
   }

   public void visit(FunctionalRateParameter rate) throws InvalidRateException {
      this.net.removeRateParameter(rate);
   }

   public void visit(InboundArc inboundArc) {
      this.net.removeArc(inboundArc);
   }

   public void visit(OutboundArc outboundArc) {
      this.net.removeArc(outboundArc);
   }
}
