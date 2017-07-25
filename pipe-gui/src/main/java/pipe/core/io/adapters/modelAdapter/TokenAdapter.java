package pipe.core.io.adapters.modelAdapter;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import uk.ac.imperial.pipe.io.adapters.model.AdaptedToken;
import pipe.core.models.petrinet.ColoredToken;
import pipe.core.models.petrinet.Token;

public final class TokenAdapter extends XmlAdapter {
   private final Map tokens;

   public TokenAdapter() {
      super();
      this.tokens = new HashMap();
   }

   public TokenAdapter(Map tokens) {
      super();
      this.tokens = tokens;
   }

   public Token unmarshal(AdaptedToken adaptedToken) {
      Color color = new Color(adaptedToken.getRed(), adaptedToken.getGreen(), adaptedToken.getBlue());
      Token token = new ColoredToken(adaptedToken.getId(), color);
      this.tokens.put(token.getId(), token);
      return token;
   }

   public AdaptedToken marshal(Token token) {
      AdaptedToken adapted = new AdaptedToken();
      adapted.setId(token.getId());
      Color color = token.getColor();
      adapted.setRed(color.getRed());
      adapted.setGreen(color.getGreen());
      adapted.setBlue(color.getBlue());
      return adapted;
   }
}
