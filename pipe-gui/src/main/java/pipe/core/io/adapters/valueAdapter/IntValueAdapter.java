package pipe.core.io.adapters.valueAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public final class IntValueAdapter extends XmlAdapter {
   public IntValueAdapter() {
      super();
   }

   public Integer unmarshal(IntValueAdapter.IntAdapter intAdapter) {
      return intAdapter.value;
   }

   public IntValueAdapter.IntAdapter marshal(Integer integer) {
      IntValueAdapter.IntAdapter adapter = new IntValueAdapter.IntAdapter();
      adapter.value = integer.intValue();
      return adapter;
   }

   public static class IntAdapter {
      public int value;

      public IntAdapter() {
         super();
      }
   }
}
