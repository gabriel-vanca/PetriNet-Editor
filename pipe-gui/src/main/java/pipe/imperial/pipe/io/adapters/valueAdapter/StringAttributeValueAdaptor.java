package pipe.imperial.pipe.io.adapters.valueAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public final class StringAttributeValueAdaptor extends XmlAdapter {
   public String unmarshal(StringAttributeValueAdaptor.AdaptedAttributeString adaptedString) {
      return adaptedString.value;
   }

   public StringAttributeValueAdaptor.AdaptedAttributeString marshal(String s) {
      StringAttributeValueAdaptor.AdaptedAttributeString adaptedString = new StringAttributeValueAdaptor.AdaptedAttributeString();
      adaptedString.value = s;
      return adaptedString;
   }

   public static class AdaptedAttributeString {
      @XmlAttribute
      public String value;
   }
}
