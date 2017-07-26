package pipe.gui.imperial.pipe.animation;

import java.util.Iterator;
import pipe.gui.imperial.pipe.models.petrinet.PetriNet;
import pipe.gui.imperial.pipe.models.petrinet.Place;
import pipe.gui.imperial.pipe.models.petrinet.Token;
import pipe.gui.imperial.state.HashedStateBuilder;
import pipe.gui.imperial.state.State;

public final class AnimationUtils {
   public static State getState(PetriNet petriNet) {
      HashedStateBuilder builder = new HashedStateBuilder();
      Iterator i$ = petriNet.getPlaces().iterator();

      while(i$.hasNext()) {
         Place place = (Place)i$.next();
         Iterator i$ = petriNet.getTokens().iterator();

         while(i$.hasNext()) {
            Token token = (Token)i$.next();
            builder.placeWithToken(place.getId(), token.getId(), place.getTokenCount(token.getId()));
         }
      }

      return builder.build();
   }
}
