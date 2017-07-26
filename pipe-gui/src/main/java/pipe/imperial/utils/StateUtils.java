package pipe.imperial.utils;

import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import uk.ac.imperial.state.ClassifiedState;
import uk.ac.imperial.state.HashedClassifiedState;
import uk.ac.imperial.state.HashedState;
import uk.ac.imperial.state.State;

public final class StateUtils {
   public static State stateFromJson(String jsonState) throws IOException {
      ObjectMapper mapper = new ObjectMapper();
      Map map = (Map)mapper.readValue(jsonState, new TypeReference() {
      });
      return new HashedState(map);
   }

   public static ClassifiedState vanishingStateFromJson(String jsonState) throws IOException {
      State state = stateFromJson(jsonState);
      return HashedClassifiedState.vanishingState(state);
   }

   public static ClassifiedState tangibleStateFromJson(String jsonState) throws IOException {
      State state = stateFromJson(jsonState);
      return HashedClassifiedState.tangibleState(state);
   }

   public static Funnel getFunnel(final Collection placeOrdering) {
      return new Funnel() {
         public void funnel(State from, PrimitiveSink into) {
            Iterator i$ = placeOrdering.iterator();

            while(i$.hasNext()) {
               String place = (String)i$.next();
               into.putBytes(place.getBytes());
               Iterator i$x = from.getTokens(place).entrySet().iterator();

               while(i$x.hasNext()) {
                  Entry entry = (Entry)i$x.next();
                  into.putBytes(((String)entry.getKey()).getBytes());
                  into.putInt(((Integer)entry.getValue()).intValue());
               }
            }

         }
      };
   }

   public static HashFunction getPrimaryHash() {
      return Hashing.adler32();
   }

   public static HashFunction getSecondaryHash() {
      return Hashing.murmur3_128();
   }

   public static HashCode hashCodeForState(State state, HashFunction hf) {
      List placeOrdering = getOrdering(state);
      Funnel funnel = getFunnel(placeOrdering);
      return hf.newHasher().putObject(state, funnel).hash();
   }

   private static List getOrdering(State state) {
      List placeOrdering = new ArrayList(state.getPlaces());
      Collections.sort(placeOrdering);
      return placeOrdering;
   }
}
