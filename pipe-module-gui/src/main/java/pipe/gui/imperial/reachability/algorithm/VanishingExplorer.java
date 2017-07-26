package pipe.gui.imperial.reachability.algorithm;

import java.util.Collection;
import uk.ac.imperial.pipe.exceptions.InvalidRateException;
import uk.ac.imperial.state.ClassifiedState;

public interface VanishingExplorer {
   Collection explore(ClassifiedState var1, double var2) throws TimelessTrapException, InvalidRateException;
}
