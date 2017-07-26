package pipe.gui.imperial.pipe.naming;

import java.util.Iterator;
import pipe.gui.imperial.pipe.models.petrinet.PetriNet;
import pipe.gui.imperial.pipe.models.petrinet.Place;

public class PlaceNamer extends ComponentNamer {
   public PlaceNamer(PetriNet petriNet) {
      super(petriNet, "P", "newPlace", "deletePlace");
      this.initialisePlaceNames();
   }

   private void initialisePlaceNames() {
      Iterator i$ = this.petriNet.getPlaces().iterator();

      while(i$.hasNext()) {
         Place place = (Place)i$.next();
         place.addPropertyChangeListener(this.nameListener);
         this.names.add(place.getId());
      }

   }
}
