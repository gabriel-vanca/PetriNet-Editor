package pipe.core.parsers;

import com.google.common.primitives.Doubles;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.parsers.FunctionalResults;
import uk.ac.imperial.pipe.parsers.GrammarUtils;
import uk.ac.imperial.pipe.parsers.RateGrammarParser;

public class PetriNetWeightParser implements FunctionalWeightParser {
   private final PetriNet petriNet;
   private final RateGrammarBaseVisitor evalVisitor;

   public PetriNetWeightParser(RateGrammarBaseVisitor evalVisitor, PetriNet petriNet) {
      super();
      this.evalVisitor = evalVisitor;
      this.petriNet = petriNet;
   }

   private Set getReferencedComponents(ParseTree parseTree) {
      ParseTreeWalker walker = new ParseTreeWalker();
      PetriNetWeightParser.ComponentListener listener = new PetriNetWeightParser.ComponentListener();
      walker.walk(listener, parseTree);
      return listener.getComponentIds();
   }

   private boolean allComponentsInPetriNet(Set components) {
      Iterator i$ = components.iterator();

      String id;
      do {
         if (!i$.hasNext()) {
            return true;
         }

         id = (String)i$.next();
      } while(this.petriNet.containsComponent(id));

      return false;
   }

   public uk.ac.imperial.pipe.parsers.FunctionalResults evaluateExpression(String expression) {
      Double maybeDouble = Doubles.tryParse(expression);
      if (maybeDouble != null) {
         return new uk.ac.imperial.pipe.parsers.FunctionalResults(maybeDouble, new HashSet());
      } else {
         RateGrammarErrorListener errorListener = new RateGrammarErrorListener();
         ParseTree parseTree = GrammarUtils.parse(expression, errorListener);
         List errors = new LinkedList();
         if (errorListener.hasErrors()) {
            errors.addAll(errorListener.getErrors());
         }

         Set components = this.getReferencedComponents(parseTree);
         if (!this.allComponentsInPetriNet(components)) {
            errors.add("Not all referenced components exist in the Petri net!");
         }

         if (!errors.isEmpty()) {
            return new uk.ac.imperial.pipe.parsers.FunctionalResults(-1.0D, errors, components);
         } else {
            Double result = (Double)this.evalVisitor.visit(parseTree);
            if (result.doubleValue() < 0.0D) {
               errors.add("Expression result cannot be less than zero!");
               return new uk.ac.imperial.pipe.parsers.FunctionalResults(-1.0D, errors, components);
            } else {
               return new FunctionalResults((Number)this.evalVisitor.visit(parseTree), components);
            }
         }
      }
   }

   static class ComponentListener extends RateGrammarBaseListener {
      private Set componentIds = new HashSet();

      ComponentListener() {
         super();
      }

      public void exitToken_number(@NotNull uk.ac.imperial.pipe.parsers.RateGrammarParser.Token_numberContext ctx) {
         this.componentIds.add(ctx.ID().getText());
      }

      public void exitToken_color_number(RateGrammarParser.Token_color_numberContext ctx) {
         this.componentIds.add(((TerminalNode)ctx.ID().get(0)).getText());
         this.componentIds.add(((TerminalNode)ctx.ID().get(1)).getText());
      }

      public Set getComponentIds() {
         return this.componentIds;
      }
   }
}
