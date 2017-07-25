package pipe.core.animation;

import java.util.Iterator;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Place;
import uk.ac.imperial.pipe.models.petrinet.Token;
import uk.ac.imperial.state.HashedStateBuilder;
import uk.ac.imperial.state.State;

public final class AnimationUtils {
   private AnimationUtils() {
      super();
   }

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
