package pipe.core.dsl;

import java.util.Map;
import pipe.core.models.petrinet.Arc;
import pipe.core.models.petrinet.InboundInhibitorArc;
import pipe.core.models.petrinet.Place;
import pipe.core.models.petrinet.Transition;
import uk.ac.imperial.pipe.dsl.DSLCreator;

public final class AnInhibitorArc implements DSLCreator {
   private String source;
   private String target;

   private AnInhibitorArc() {
      super();
   }

   public static AnInhibitorArc withSource(String source) {
      AnInhibitorArc anInhibitorArc = new AnInhibitorArc();
      anInhibitorArc.source = source;
      return anInhibitorArc;
   }

   public AnInhibitorArc andTarget(String target) {
      this.target = target;
      return this;
   }

   public Arc create(Map tokens, Map places, Map transitions, Map rateParameters) {
      return new InboundInhibitorArc((Place)places.get(this.source), (Transition)transitions.get(this.target));
   }
}
