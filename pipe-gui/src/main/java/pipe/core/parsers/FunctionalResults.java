package pipe.core.parsers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class FunctionalResults {
   private final Set components;
   private List errors;
   private Number result;

   public FunctionalResults(Number result, Set components) {
      this(result, new LinkedList(), components);
   }

   public FunctionalResults(Number result, List errors, Set components) {
      super();
      this.result = result;
      this.errors = errors;
      this.components = components;
   }

   public boolean hasErrors() {
      return !this.errors.isEmpty();
   }

   public List getErrors() {
      return this.errors;
   }

   public Number getResult() {
      return this.result;
   }

   public Set getComponents() {
      return this.components;
   }

   public String getErrorString(String seperator) {
      StringBuilder builder = new StringBuilder();
      int i = 0;
      Iterator i$ = this.errors.iterator();

      while(i$.hasNext()) {
         String error = (String)i$.next();
         builder.append(error);
         ++i;
         if (i < this.errors.size()) {
            builder.append(seperator);
         }
      }

      return builder.toString();
   }
}
