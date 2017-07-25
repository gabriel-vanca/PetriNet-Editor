package pipe.core.io.adapters.valueAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public final class StringValueAdapter extends XmlAdapter {
   public StringValueAdapter() {
      super();
   }

   public String unmarshal(StringValueAdapter.AdaptedString adaptedString) {
      return adaptedString.value;
   }

   public StringValueAdapter.AdaptedString marshal(String s) {
      StringValueAdapter.AdaptedString adaptedString = new StringValueAdapter.AdaptedString();
      adaptedString.value = s;
      return adaptedString;
   }

   public static class AdaptedString {
      public String value;

      public AdaptedString() {
         super();
      }
   }
}
