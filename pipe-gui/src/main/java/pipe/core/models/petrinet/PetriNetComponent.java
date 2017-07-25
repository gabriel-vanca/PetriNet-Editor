package pipe.core.models.petrinet;

import java.beans.PropertyChangeListener;
import pipe.core.exceptions.PetriNetComponentException;
import pipe.core.visitor.component.PetriNetComponentVisitor;

public interface PetriNetComponent {
   String ID_CHANGE_MESSAGE = "id";
   String NAME_CHANGE_MESSAGE = "name";

   boolean isSelectable();

   boolean isDraggable();

   void accept(PetriNetComponentVisitor var1) throws PetriNetComponentException;

   String getId();

   void setId(String var1);

   void addPropertyChangeListener(PropertyChangeListener var1);

   void removePropertyChangeListener(PropertyChangeListener var1);
}
