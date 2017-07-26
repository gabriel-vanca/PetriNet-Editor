package pipe.imperial.pipe.io.adapters.modelAdapter;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import uk.ac.imperial.pipe.io.adapters.model.AdaptedRateParameter;
import uk.ac.imperial.pipe.models.petrinet.FunctionalRateParameter;
import uk.ac.imperial.pipe.models.petrinet.RateParameter;

public final class RateParameterAdapter extends XmlAdapter {
   private final Map rateParameters;

   public RateParameterAdapter() {
      this.rateParameters = new HashMap();
   }

   public RateParameterAdapter(Map rateParameters) {
      this.rateParameters = rateParameters;
   }

   public FunctionalRateParameter unmarshal(AdaptedRateParameter adaptedRateParameter) {
      FunctionalRateParameter rateParameter = new FunctionalRateParameter(adaptedRateParameter.getExpression(), adaptedRateParameter.getId(), adaptedRateParameter.getName());
      this.rateParameters.put(rateParameter.getId(), rateParameter);
      return rateParameter;
   }

   public AdaptedRateParameter marshal(RateParameter rateParameter) {
      AdaptedRateParameter adaptedRateParameter = new AdaptedRateParameter();
      adaptedRateParameter.setExpression(rateParameter.getExpression());
      adaptedRateParameter.setId(rateParameter.getId());
      adaptedRateParameter.setName(rateParameter.getId());
      return adaptedRateParameter;
   }
}
