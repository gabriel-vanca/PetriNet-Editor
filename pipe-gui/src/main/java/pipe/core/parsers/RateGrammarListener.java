package pipe.core.parsers;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import uk.ac.imperial.pipe.parsers.RateGrammarParser;

public interface RateGrammarListener extends ParseTreeListener {
   void enterProgram(uk.ac.imperial.pipe.parsers.RateGrammarParser.ProgramContext var1);

   void exitProgram(uk.ac.imperial.pipe.parsers.RateGrammarParser.ProgramContext var1);

   void enterAddOrSubtract(uk.ac.imperial.pipe.parsers.RateGrammarParser.AddOrSubtractContext var1);

   void exitAddOrSubtract(uk.ac.imperial.pipe.parsers.RateGrammarParser.AddOrSubtractContext var1);

   void enterMultOrDiv(uk.ac.imperial.pipe.parsers.RateGrammarParser.MultOrDivContext var1);

   void exitMultOrDiv(uk.ac.imperial.pipe.parsers.RateGrammarParser.MultOrDivContext var1);

   void enterFloor(uk.ac.imperial.pipe.parsers.RateGrammarParser.FloorContext var1);

   void exitFloor(uk.ac.imperial.pipe.parsers.RateGrammarParser.FloorContext var1);

   void enterInteger(uk.ac.imperial.pipe.parsers.RateGrammarParser.IntegerContext var1);

   void exitInteger(uk.ac.imperial.pipe.parsers.RateGrammarParser.IntegerContext var1);

   void enterPlaceTokens(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceTokensContext var1);

   void exitPlaceTokens(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceTokensContext var1);

   void enterCeil(uk.ac.imperial.pipe.parsers.RateGrammarParser.CeilContext var1);

   void exitCeil(uk.ac.imperial.pipe.parsers.RateGrammarParser.CeilContext var1);

   void enterPlaceColorTokens(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceColorTokensContext var1);

   void exitPlaceColorTokens(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceColorTokensContext var1);

   void enterParenExpression(uk.ac.imperial.pipe.parsers.RateGrammarParser.ParenExpressionContext var1);

   void exitParenExpression(uk.ac.imperial.pipe.parsers.RateGrammarParser.ParenExpressionContext var1);

   void enterDouble(uk.ac.imperial.pipe.parsers.RateGrammarParser.DoubleContext var1);

   void exitDouble(uk.ac.imperial.pipe.parsers.RateGrammarParser.DoubleContext var1);

   void enterPlaceCapacity(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceCapacityContext var1);

   void exitPlaceCapacity(uk.ac.imperial.pipe.parsers.RateGrammarParser.PlaceCapacityContext var1);

   void enterCapacity(uk.ac.imperial.pipe.parsers.RateGrammarParser.CapacityContext var1);

   void exitCapacity(uk.ac.imperial.pipe.parsers.RateGrammarParser.CapacityContext var1);

   void enterToken_number(uk.ac.imperial.pipe.parsers.RateGrammarParser.Token_numberContext var1);

   void exitToken_number(uk.ac.imperial.pipe.parsers.RateGrammarParser.Token_numberContext var1);

   void enterToken_color_number(uk.ac.imperial.pipe.parsers.RateGrammarParser.Token_color_numberContext var1);

   void exitToken_color_number(RateGrammarParser.Token_color_numberContext var1);
}
