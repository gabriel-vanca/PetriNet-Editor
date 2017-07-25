package pipe.core.models.petrinet;

import pipe.core.exceptions.InvalidRateException;
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

public final class PetriNetComponentAddVisitor implements PlaceVisitor, ArcVisitor, TransitionVisitor, TokenVisitor, AnnotationVisitor, RateParameterVisitor {
   private final uk.ac.imperial.pipe.models.petrinet.PetriNet petriNet;

   public PetriNetComponentAddVisitor(PetriNet petriNet) {
      super();
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
