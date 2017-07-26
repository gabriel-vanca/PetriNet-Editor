package pipe.imperial.pipe.models.petrinet;

import pipe.imperial.pipe.exceptions.InvalidRateException;
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

public final class PetriNetComponentAddVisitor implements PlaceVisitor, ArcVisitor, TransitionVisitor, TokenVisitor, AnnotationVisitor, RateParameterVisitor {
   private final uk.ac.imperial.pipe.models.petrinet.PetriNet petriNet;

   public PetriNetComponentAddVisitor(PetriNet petriNet) {
      this.petriNet = petriNet;
   }

   public void visit(Place place) {
      this.petriNet.addPlace(place);
   }

   public void visit(Transition transition) {
      this.petriNet.addTransition(transition);
   }

   public void visit(Token token) {
      this.petriNet.addToken(token);
   }

   public void visit(Annotation annotation) {
      this.petriNet.addAnnotation(annotation);
   }

   public void visit(FunctionalRateParameter rate) throws InvalidRateException {
      this.petriNet.addRateParameter(rate);
   }

   public void visit(InboundArc inboundArc) {
      this.petriNet.addArc(inboundArc);
   }

   public void visit(OutboundArc outboundArc) {
      this.petriNet.addArc(outboundArc);
   }
}
