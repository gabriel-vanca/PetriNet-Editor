package pipe.imperial.pipe.visitor;

import pipe.imperial.pipe.models.petrinet.DiscretePlace;
import pipe.imperial.pipe.models.petrinet.DiscretePlaceVisitor;
import pipe.imperial.pipe.models.petrinet.Place;

public final class PlaceCloner implements DiscretePlaceVisitor {
   public Place cloned = null;

   public void visit(DiscretePlace discretePlace) {
      this.cloned = new DiscretePlace(discretePlace);
   }
}
