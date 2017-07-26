package pipe.imperial.pipe.visitor.connectable.arc;

import java.util.logging.Level;
import java.util.logging.Logger;
import pipe.imperial.pipe.exceptions.PetriNetComponentException;
import pipe.imperial.pipe.models.petrinet.Connectable;
import pipe.imperial.pipe.models.petrinet.Place;
import pipe.imperial.pipe.models.petrinet.Transition;
import pipe.imperial.pipe.visitor.connectable.arc.ArcSourceVisitor;

public final class NormalArcSourceVisitor implements ArcSourceVisitor {
   private static final Logger LOGGER = Logger.getLogger(NormalArcSourceVisitor.class.getName());
   private boolean canCreate = false;

   public void visit(Place place) {
      this.canCreate = true;
   }

   public void visit(Transition transition) {
      this.canCreate = true;
   }

   public boolean canStart(Connectable connectable) {
      try {
         connectable.accept(this);
      } catch (PetriNetComponentException var3) {
         LOGGER.log(Level.SEVERE, var3.getMessage());
         return false;
      }

      return this.canCreate;
   }
}
