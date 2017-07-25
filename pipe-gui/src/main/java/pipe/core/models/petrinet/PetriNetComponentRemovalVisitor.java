package pipe.core.models.petrinet;

import pipe.core.exceptions.InvalidRateException;
import pipe.core.exceptions.PetriNetComponentException;
import uk.ac.imperial.pipe.models.petrinet.Annotation;
import uk.ac.imperial.pipe.models.petrinet.AnnotationVisitor;
import uk.ac.imperial.pipe.models.petrinet.ArcVisitor;
import uk.ac.imperial.pipe.models.petrinet.FunctionalRateParameter;
import uk.ac.imperial.pipe.models.petrinet.InboundArc;
import uk.ac.imperial.pipe.models.petrinet.OutboundArc;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.models.petrinet.PlaceVisitor;
import uk.ac.imperial.pipe.models.petrinet.RateParameterVisitor;
import uk.ac.imperial.pipe.models.petrinet.Token;
import uk.ac.imperial.pipe.models.petrinet.TokenVisitor;
import uk.ac.imperial.pipe.models.petrinet.Transition;
import uk.ac.imperial.pipe.models.petrinet.TransitionVisitor;

public final class PetriNetComponentRemovalVisitor implements PlaceVisitor, TransitionVisitor, ArcVisitor, TokenVisitor, AnnotationVisitor, RateParameterVisitor {
   private final uk.ac.imperial.pipe.models.petrinet.PetriNet net;

   public PetriNetComponentRemovalVisitor(PetriNet net) {
      super();
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
