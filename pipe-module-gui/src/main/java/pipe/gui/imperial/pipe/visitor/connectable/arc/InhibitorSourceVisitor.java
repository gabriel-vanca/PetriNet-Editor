package pipe.gui.imperial.pipe.visitor.connectable.arc;

import java.util.logging.Level;
import java.util.logging.Logger;
import pipe.gui.imperial.pipe.exceptions.PetriNetComponentException;
import pipe.gui.imperial.pipe.models.petrinet.Connectable;
import pipe.gui.imperial.pipe.models.petrinet.Place;
import pipe.gui.imperial.pipe.models.petrinet.Transition;
import pipe.gui.imperial.pipe.visitor.connectable.arc.ArcSourceVisitor;

public final class InhibitorSourceVisitor implements ArcSourceVisitor {
   private static final Logger LOGGER = Logger.getLogger(InhibitorSourceVisitor.class.getName());
   private boolean canCreate = false;

   public void visit(Place place) {
      this.canCreate = true;
   }

   public void visit(Transition transition) {
      this.canCreate = false;
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