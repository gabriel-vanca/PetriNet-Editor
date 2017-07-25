package pipe.core.io.adapters.valueAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanValueAdapter extends XmlAdapter {
   public BooleanValueAdapter() {
      super();
   }

   public Boolean unmarshal(BooleanValueAdapter.AdaptedBoolean adaptedBoolean) {
      return adaptedBoolean.value;
   }

   public BooleanValueAdapter.AdaptedBoolean marshal(Boolean aBoolean) {
      BooleanValueAdapter.AdaptedBoolean adapted = new BooleanValueAdapter.AdaptedBoolean();
      adapted.value = aBoolean.booleanValue();
      return adapted;
   }

   public static class AdaptedBoolean {
      public boolean value;

      public AdaptedBoolean() {
         super();
      }
   }
}
