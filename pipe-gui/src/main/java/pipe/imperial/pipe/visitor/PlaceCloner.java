package pipe.imperial.pipe.visitor;

import uk.ac.imperial.pipe.models.petrinet.DiscretePlace;
import uk.ac.imperial.pipe.models.petrinet.DiscretePlaceVisitor;
import uk.ac.imperial.pipe.models.petrinet.Place;

public final class PlaceCloner implements DiscretePlaceVisitor {
   public Place cloned = null;

   public void visit(DiscretePlace discretePlace) {
      this.cloned = new DiscretePlace(discretePlace);
   }
}
