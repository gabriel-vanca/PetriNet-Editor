package pipe.gui.imperial.pipe.visitor;

import pipe.gui.imperial.pipe.models.petrinet.DiscretePlace;
import pipe.gui.imperial.pipe.models.petrinet.DiscretePlaceVisitor;
import pipe.gui.imperial.pipe.models.petrinet.Place;

public final class PlaceCloner implements DiscretePlaceVisitor {
   public Place cloned = null;

   public void visit(DiscretePlace discretePlace) {
      this.cloned = new DiscretePlace(discretePlace);
   }
}
