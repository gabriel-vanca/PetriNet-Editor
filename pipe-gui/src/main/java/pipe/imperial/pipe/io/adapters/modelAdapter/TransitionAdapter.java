package pipe.imperial.pipe.io.adapters.modelAdapter;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import uk.ac.imperial.pipe.io.adapters.model.AdaptedTransition;
import uk.ac.imperial.pipe.io.adapters.model.NameDetails;
import uk.ac.imperial.pipe.io.adapters.utils.ConnectableUtils;
import uk.ac.imperial.pipe.models.petrinet.DiscreteTransition;
import uk.ac.imperial.pipe.models.petrinet.FunctionalRateParameter;
import uk.ac.imperial.pipe.models.petrinet.NormalRate;
import uk.ac.imperial.pipe.models.petrinet.Rate;
import uk.ac.imperial.pipe.models.petrinet.Transition;

public final class TransitionAdapter extends XmlAdapter {
   private final Map transitions;
   private final Map rateParameters;

   public TransitionAdapter() {
      this.transitions = new HashMap();
      this.rateParameters = new HashMap();
   }

   public TransitionAdapter(Map transitions, Map rateParameters) {
      this.transitions = transitions;
      this.rateParameters = rateParameters;
   }

   public Transition unmarshal(AdaptedTransition adaptedTransition) {
      NameDetails nameDetails = adaptedTransition.getName();
      Transition transition = new DiscreteTransition(adaptedTransition.getId(), nameDetails.getName());
      ConnectableUtils.setConntactableNameOffset(transition, adaptedTransition);
      ConnectableUtils.setConnectablePosition(transition, adaptedTransition);
      transition.setAngle(adaptedTransition.getAngle());
      transition.setPriority(adaptedTransition.getPriority());
      AdaptedTransition.ToolSpecific toolSpecific = adaptedTransition.getToolSpecific();
      Object rate;
      if (toolSpecific == null) {
         rate = new NormalRate(adaptedTransition.getRate());
      } else {
         rate = (Rate)this.rateParameters.get(toolSpecific.getRateDefinition());
      }

      transition.setRate((Rate)rate);
      transition.setTimed(adaptedTransition.getTimed().booleanValue());
      transition.setInfiniteServer(adaptedTransition.getInfiniteServer().booleanValue());
      this.transitions.put(transition.getId(), transition);
      return transition;
   }

   public AdaptedTransition marshal(Transition transition) {
      AdaptedTransition adaptedTransition = new AdaptedTransition();
      ConnectableUtils.setAdaptedName(transition, adaptedTransition);
      adaptedTransition.setId(transition.getId());
      ConnectableUtils.setPosition(transition, adaptedTransition);
      adaptedTransition.setPriority(transition.getPriority());
      adaptedTransition.setAngle(transition.getAngle());
      adaptedTransition.setRate(transition.getRateExpr());
      adaptedTransition.setInfiniteServer(transition.isInfiniteServer());
      adaptedTransition.setTimed(transition.isTimed());
      Rate rate = transition.getRate();
      if (rate instanceof FunctionalRateParameter) {
         FunctionalRateParameter rateParameter = (FunctionalRateParameter)rate;
         AdaptedTransition.ToolSpecific toolSpecific = new AdaptedTransition.ToolSpecific();
         toolSpecific.setRateDefinition(rateParameter.getId());
         adaptedTransition.setToolSpecific(toolSpecific);
      }

      return adaptedTransition;
   }
}