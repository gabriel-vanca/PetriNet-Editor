package pipe.imperial.pipe.io.adapters.modelAdapter;

import java.util.Iterator;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import uk.ac.imperial.pipe.exceptions.PetriNetComponentException;
import uk.ac.imperial.pipe.io.adapters.model.AdaptedPetriNet;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.PetriNetComponent;

public class PetriNetAdapter extends XmlAdapter {
   public PetriNet unmarshal(AdaptedPetriNet v) throws PetriNetComponentException {
      PetriNet petriNet = new PetriNet();
      this.addToPetriNet(v.tokens, petriNet);
      this.addToPetriNet(v.annotations, petriNet);
      this.addToPetriNet(v.places, petriNet);
      this.addToPetriNet(v.rateParameters, petriNet);
      this.addToPetriNet(v.transitions, petriNet);
      this.addToPetriNet(v.arcs, petriNet);
      return petriNet;
   }

   public AdaptedPetriNet marshal(PetriNet v) {
      AdaptedPetriNet petriNet = new AdaptedPetriNet();
      petriNet.tokens = v.getTokens();
      petriNet.annotations = v.getAnnotations();
      petriNet.rateParameters = v.getRateParameters();
      petriNet.places = v.getPlaces();
      petriNet.transitions = v.getTransitions();
      petriNet.arcs = v.getArcs();
      return petriNet;
   }

   private void addToPetriNet(Iterable components, PetriNet petriNet) throws PetriNetComponentException {
      if (components != null) {
         Iterator i$ = components.iterator();

         while(i$.hasNext()) {
            PetriNetComponent component = (PetriNetComponent)i$.next();
            petriNet.add(component);
         }
      }

   }
}