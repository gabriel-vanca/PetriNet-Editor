package pipe.imperial.pipe.visitor;

import pipe.imperial.pipe.models.petrinet.AnnotationVisitor;
import pipe.imperial.pipe.models.petrinet.ArcVisitor;
import pipe.imperial.pipe.models.petrinet.PlaceVisitor;
import pipe.imperial.pipe.models.petrinet.RateVisitor;
import pipe.imperial.pipe.models.petrinet.TokenVisitor;
import pipe.imperial.pipe.models.petrinet.TransitionVisitor;

public interface AllComponentVisitor extends PlaceVisitor, TransitionVisitor, ArcVisitor, AnnotationVisitor, TokenVisitor, RateVisitor {
}
