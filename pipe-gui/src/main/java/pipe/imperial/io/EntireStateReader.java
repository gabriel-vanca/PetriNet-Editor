package pipe.imperial.io;

import com.esotericsoftware.kryo.io.Input;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import pipe.imperial.state.Record;
import uk.ac.imperial.io.MultiStateReader;
import uk.ac.imperial.io.StateMapping;
import uk.ac.imperial.io.StateReader;

public final class EntireStateReader implements MultiStateReader {
   private final uk.ac.imperial.io.StateReader reader;

   public EntireStateReader(StateReader reader) {
      this.reader = reader;
   }

   public Collection readRecords(Input input) throws IOException {
      ArrayList results = new ArrayList();

      while(!input.eof()) {
         Record record = this.reader.readRecord(input);
         results.add(record);
      }

      return results;
   }

   public Map readStates(Input input) {
      HashMap mappings = new HashMap();

      while(!input.eof()) {
         StateMapping stateMapping = this.reader.readState(input);
         mappings.put(stateMapping.id, stateMapping.state);
      }

      return mappings;
   }
}
