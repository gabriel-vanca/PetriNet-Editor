package pipe.core.parsers;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import uk.ac.imperial.pipe.parsers.RateGrammarParser;

public interface RateGrammarVisitor extends ParseTreeVisitor {
   Object visitProgram(uk.ac.imperial.pipe.parsers.RateGrammarParser.ProgramContext var1);

   Object visitAddOrSubtract(uk.ac.imperial.pipe.parsers.RateGrammarParser.AddOrSubtractContext var1);

   Object visitMultOrDiv(uk.ac.imperial.pipe.parsers.RateGrammarParser.MultOrDivContext var1);

   Object visitFloor(uk.ac.imperial.pipe.parsers.RateGrammarParser.FloorContext var1);

   Object visitInteger(uk.ac.imperial.pipe.parsers.RateGrammarParser.IntegerContext var1);

   Object visitPlaceTokens(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceTokensContext var1);

   Object visitCeil(uk.ac.imperial.pipe.parsers.RateGrammarParser.CeilContext var1);

   Object visitPlaceColorTokens(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceColorTokensContext var1);

   Object visitParenExpression(uk.ac.imperial.pipe.parsers.RateGrammarParser.ParenExpressionContext var1);

   Object visitDouble(uk.ac.imperial.pipe.parsers.RateGrammarParser.DoubleContext var1);

   Object visitPlaceCapacity(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceCapacityContext var1);

   Object visitCapacity(uk.ac.imperial.pipe.parsers.RateGrammarParser.CapacityContext var1);

   Object visitToken_number(uk.ac.imperial.pipe.parsers.RateGrammarParser.Token_numberContext var1);

   Object visitToken_color_number(RateGrammarParser.Token_color_numberContext var1);
}
