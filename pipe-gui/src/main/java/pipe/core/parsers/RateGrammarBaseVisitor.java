package pipe.core.parsers;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import uk.ac.imperial.pipe.parsers.RateGrammarParser;
import uk.ac.imperial.pipe.parsers.RateGrammarVisitor;

public class RateGrammarBaseVisitor extends AbstractParseTreeVisitor implements RateGrammarVisitor {
   public RateGrammarBaseVisitor() {
      super();
   }

   public Object visitProgram(uk.ac.imperial.pipe.parsers.RateGrammarParser.ProgramContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitAddOrSubtract(uk.ac.imperial.pipe.parsers.RateGrammarParser.AddOrSubtractContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitMultOrDiv(uk.ac.imperial.pipe.parsers.RateGrammarParser.MultOrDivContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitFloor(uk.ac.imperial.pipe.parsers.RateGrammarParser.FloorContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitInteger(uk.ac.imperial.pipe.parsers.RateGrammarParser.IntegerContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPlaceTokens(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceTokensContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCeil(uk.ac.imperial.pipe.parsers.RateGrammarParser.CeilContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPlaceColorTokens(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceColorTokensContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitParenExpression(uk.ac.imperial.pipe.parsers.RateGrammarParser.ParenExpressionContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitDouble(uk.ac.imperial.pipe.parsers.RateGrammarParser.DoubleContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitPlaceCapacity(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceCapacityContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitCapacity(uk.ac.imperial.pipe.parsers.RateGrammarParser.CapacityContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitToken_number(uk.ac.imperial.pipe.parsers.RateGrammarParser.Token_numberContext ctx) {
      return this.visitChildren(ctx);
   }

   public Object visitToken_color_number(RateGrammarParser.Token_color_numberContext ctx) {
      return this.visitChildren(ctx);
   }
}
